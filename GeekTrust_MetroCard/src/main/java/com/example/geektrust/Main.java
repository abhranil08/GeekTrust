package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.Controller.MetroCardController;
import com.example.geektrust.Controller.StationController;
import com.example.geektrust.Controller.TravelController;
import com.example.geektrust.Model.AirportStation;
import com.example.geektrust.Model.CentralStation;
import com.example.geektrust.Model.MetroCard;
import com.example.geektrust.Model.Station;
import com.example.geektrust.Repository.MetroCardRepository;
import com.example.geektrust.Repository.StationRepository;

public class Main {
    static Station stationAirport = new AirportStation();
    static Station stationCentral = new CentralStation();
    static TravelController travelController = new TravelController();
    static MetroCardController metroCardController = new MetroCardController();
    static MetroCardRepository metroCardRepository = new MetroCardRepository();
    static StationRepository airportPassengerSummary = new StationRepository();
    static StationRepository centralPassengerSummary = new StationRepository();
    static StationController stationController = new StationController();

    public static void parseInputAndExecute( String input )
    {
        String[] cmd = input.split(" ");
                switch ( cmd[0] )
                {
                case "BALANCE":
                    String metroCardNumber = cmd[1];
                    int balance = Integer.parseInt(cmd[2]);
                    MetroCard metroCard = new MetroCard(metroCardNumber);
                    metroCardController.updateMetroCardBalance(metroCard.getMetroCardNumber(), balance);
                    break;
                case "CHECK_IN":
                    String METROCARD_NUMBER = cmd[1];
                    String PASSENGER_TYPE = cmd[2];
                    String FROM_STATION = cmd[3];

                    if( FROM_STATION.equals("AIRPORT"))
                        travelController.ExecuteCheckIn( METROCARD_NUMBER, PASSENGER_TYPE, FROM_STATION, 
                                                        stationAirport, airportPassengerSummary );
                    else 
                        travelController.ExecuteCheckIn( METROCARD_NUMBER, PASSENGER_TYPE, FROM_STATION, stationCentral,
                                                        centralPassengerSummary  );
                    break;
                case "PRINT_SUMMARY":
                    stationController.getPassengerSummaryAndPrint( airportPassengerSummary, centralPassengerSummary,
                                                                stationAirport, stationCentral, 
                                                                travelController );
                    break;
                }
    }
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); 
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                parseInputAndExecute( input );
            }
            sc.close(); 
        } catch (IOException e) {
        }
    }
}
