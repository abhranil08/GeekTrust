package com.example.geektrust.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.geektrust.Model.Station;
import com.example.geektrust.Repository.StationRepository;
import com.example.geektrust.Util.Pair;

public class StationController {

    public ArrayList<Pair> getPassengerSummary( StationRepository stationRepository ) 
    {
        HashMap<String,Integer> passengerSummary = stationRepository.getPassengerTypeCountMap();
        ArrayList<Pair> passengerSummarySorted = new ArrayList<Pair>();

        for( Map.Entry<String,Integer> mapElement : passengerSummary.entrySet())
        {
            passengerSummarySorted.add(new Pair(mapElement.getKey(), mapElement.getValue()));
        }
        
        Collections.sort( passengerSummarySorted, Pair.PairComparator );
        return passengerSummarySorted;
	}

    private void printSummaryList( ArrayList<Pair> passengerSummaryList )
    {
        for( Pair passengers : passengerSummaryList )
        {
            System.out.println(passengers.getKey() + " " + passengers.getValue(passengers.getKey()));
        }
    }

    public void getPassengerSummaryAndPrint(  StationRepository airportPassengerSummary, 
                                            StationRepository centralPassengerSummary,
                                            Station stationAirport, Station stationCentral, 
                                            TravelController travelController )
    {
        System.out.println( "TOTAL_COLLECTION CENTRAL " + stationCentral.getTotalCollection() + " " + stationCentral.getTotalDiscount() );
        System.out.println( "PASSENGER_TYPE_SUMMARY" );
        printSummaryList( getPassengerSummary( centralPassengerSummary ) );

        System.out.println( "TOTAL_COLLECTION AIRPORT " + stationAirport.getTotalCollection() + " " + stationAirport.getTotalDiscount() );
        System.out.println( "PASSENGER_TYPE_SUMMARY" );
        printSummaryList( getPassengerSummary( airportPassengerSummary ) );

    }
    
}
