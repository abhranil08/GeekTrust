package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.geektrust.Controller.MetroCardController;
import com.example.geektrust.Controller.TravelController;
import com.example.geektrust.Model.AirportStation;
import com.example.geektrust.Model.CentralStation;
import com.example.geektrust.Model.MetroCard;
import com.example.geektrust.Model.Station;
import com.example.geektrust.Repository.MetroCardRepository;
import com.example.geektrust.Repository.StationRepository;
import com.example.geektrust.Repository.TravelRepository;
import com.example.geektrust.Util.Constants;
import com.example.geektrust.Util.Pair;


public class MainTest {
    static Station stationAirport;
    static Station stationCentral;
    static TravelController travelController;
    static MetroCardController metroCardController;
    static StationRepository airportPassengerSummary;
    static StationRepository centralPassengerSummary;
    TravelRepository travelRepository;
    MetroCardRepository metroCardRepository;


    @BeforeEach
    public void initClass()
    {   
        TravelRepository.clearPassengerMap();
        MetroCardRepository.clearMap();
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();
        airportPassengerSummary = new StationRepository();
        centralPassengerSummary = new StationRepository();
    }

    @Test
    public void TestFullRun()
    {
        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("600");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("50");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //4th Card
        metroCardNumber = "MC4";
        balance = Integer.parseInt("50");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //5th Card
        metroCardNumber = "MC5";
        balance = Integer.parseInt("200");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.KID, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC4", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC5", Constants.KID, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        
        travelController.getPassengerSummaryAndPrint( airportPassengerSummary, centralPassengerSummary,
                                            stationAirport, stationCentral, 
                                            travelController );

        assertEquals(403, stationAirport.getTotalCollection());
        assertEquals(300, stationCentral.getTotalCollection());         
    }

    @Test
    public void TestForRun2()
    {
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();

        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("400");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("100");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("50");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //4th Card
        metroCardNumber = "MC4";
        balance = Integer.parseInt("50");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.KID, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        
        assertEquals(252, stationAirport.getTotalCollection());
        assertEquals(457, stationCentral.getTotalCollection());
    }

    @Test
    public void TestFor2PassengerTypeWithDifferentStations()
    {
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();

        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("400");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("100");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.KID, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        
        assertEquals(100, stationAirport.getTotalCollection());
        assertEquals(50, stationCentral.getTotalCollection());
    }

    @Test
    public void TestForPassengerSummary()
    {
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();

        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("400");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("100");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("100");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //4th Card
        metroCardNumber = "MC4";
        balance = Integer.parseInt("500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC4", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.KID, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.KID, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        
        travelController.getPassengerSummaryAndPrint( airportPassengerSummary, centralPassengerSummary,
                                            stationAirport, stationCentral, 
                                            travelController );

        assertEquals(0, stationAirport.getTotalCollection());
        assertEquals(0, stationAirport.getTotalDiscount());

        assertEquals(400, stationCentral.getTotalCollection());
        assertEquals(0, stationCentral.getTotalDiscount());
    }

    @Test
    public void TestForPassengerSummary2()
    {
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();

        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("300");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("300");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //4rd Card
        metroCardNumber = "MC4";
        balance = Integer.parseInt("400");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC4", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        
        
        assertEquals(600, stationAirport.getTotalCollection());
        assertEquals(0, stationCentral.getTotalCollection());

        travelController.getPassengerSummaryAndPrint( airportPassengerSummary, centralPassengerSummary,
                                            stationAirport, stationCentral, 
                                            travelController );
    }

    @Test
    public void TestForTotalDiscount()
    {
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();

        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        
        assertEquals(200, stationAirport.getTotalCollection());
        assertEquals(0, stationAirport.getTotalDiscount());

        assertEquals(50, stationCentral.getTotalCollection());
        assertEquals(50, stationCentral.getTotalDiscount());
    }

    @Test
    @DisplayName("Testing charges based on passenger type")
    public void TestTravelCostForPassengerType()
    {
        TravelController travelController = new TravelController();
        int adultFare = travelController.travelCostForPassengerType( Constants.ADULT );
        int seniorCitizenFare = travelController.travelCostForPassengerType( Constants.SENIOR_CITIZEN );
        int kidFare = travelController.travelCostForPassengerType( Constants.KID );

        assertEquals(200, adultFare);
        assertEquals(100, seniorCitizenFare);
        assertEquals(50, kidFare);

    }

    @Test
    @DisplayName("Testing travel cost calculation with and without discount")
    public void TestCalculateTravelCost()
    {
        Station stationAirport = new AirportStation();
        TravelController travelController = new TravelController();
        
        MetroCardController metroCardController = new MetroCardController();
        
        int balance = 200;
        String metroCardNumber = "MC1";
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);
        
        balance = 100;
        metroCardNumber = "MC2";
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);
        
        balance = 50;
        metroCardNumber = "MC3";
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        int travelCostAdult = travelController.calculateTravelCost( "MC1", Constants.ADULT, Constants.AIRPORT, stationAirport );
        int travelCostSC = travelController.calculateTravelCost( "MC2", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport );
        int travelCostDiscount = travelController.calculateTravelCost( "MC1", Constants.ADULT, Constants.AIRPORT, stationAirport );
        
        assertEquals(200, travelCostAdult);
        assertEquals(100, travelCostSC);
        assertEquals(100, travelCostDiscount);
    }

    @Test
    public void TestForFullRun3()
    {
        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("400");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("100");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("50");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //4th Card
        metroCardNumber = "MC4";
        balance = Integer.parseInt("50");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        /*
         *  CHECK_IN MC1 SENIOR_CITIZEN AIRPORT
            CHECK_IN MC2 KID AIRPORT
            CHECK_IN MC3 ADULT CENTRAL
            CHECK_IN MC1 SENIOR_CITIZEN CENTRAL
            CHECK_IN MC3 ADULT AIRPORT
            CHECK_IN MC3 ADULT CENTRAL
         */

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.KID, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        
        assertEquals(457, stationCentral.getTotalCollection());
        assertEquals(50, stationCentral.getTotalDiscount());

        assertEquals(252, stationAirport.getTotalCollection()); 
        assertEquals(100, stationAirport.getTotalDiscount());         
    }

    @Test
    public void TestForFullRun4()
    {
        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("300");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("250");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("250");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        /*
         *  BALANCE MC1 300
            BALANCE MC2 350
            BALANCE MC3 250
            CHECK_IN MC3 KID CENTRAL
            CHECK_IN MC3 KID AIRPORT
            CHECK_IN MC1 ADULT CENTRAL
            CHECK_IN MC2 SENIOR_CITIZEN AIRPORT
            CHECK_IN MC2 SENIOR_CITIZEN CENTRAL
            CHECK_IN MC1 ADULT AIRPORT
            PRINT_SUMMARY
         */

        travelController.ExecuteCheckIn( "MC3", Constants.KID, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.KID, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.SENIOR_CITIZEN, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC1", Constants.ADULT, Constants.AIRPORT, stationAirport, airportPassengerSummary  );
        
        assertEquals(300, stationCentral.getTotalCollection());
        assertEquals(50, stationCentral.getTotalDiscount());

        assertEquals(225, stationAirport.getTotalCollection()); 
        assertEquals(125, stationAirport.getTotalDiscount());         
    }

    @Test
    public void TestForPassengerSummaryList()
    {
        StationRepository stationRepository = new StationRepository();
        stationRepository.setPassengerTypeCount(Constants.ADULT);
        stationRepository.setPassengerTypeCount(Constants.ADULT);
        stationRepository.setPassengerTypeCount(Constants.KID);

        ArrayList<Pair> passengerSummary = travelController.getPassengerSummary( stationRepository );
        assertEquals(2, passengerSummary.size());
    }

    @Test
    public void TestForNoAirportTravel()
    {
        travelController = new TravelController();
        metroCardController = new MetroCardController();
        stationAirport = new AirportStation();
        stationCentral = new CentralStation();

        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //3rd Card
        metroCardNumber = "MC3";
        balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.KID,Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        travelController.ExecuteCheckIn( "MC3", Constants.ADULT, Constants.CENTRAL, stationCentral, centralPassengerSummary  );
        
        assertEquals(0, stationAirport.getTotalCollection());
        assertEquals(0, stationAirport.getTotalDiscount());

        assertEquals(350, stationCentral.getTotalCollection());
        assertEquals(0, stationCentral.getTotalDiscount());
    }

    @Test
    public void TestForNoCentralTravel()
    {
        //First Card
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        //2nd Card
        metroCardNumber = "MC2";
        balance = Integer.parseInt("1500");
        metroCardController.updateMetroCardBalance(metroCardNumber, balance);

        travelController.ExecuteCheckIn( "MC1", Constants.SENIOR_CITIZEN, Constants.CENTRAL, stationAirport, airportPassengerSummary  );
        travelController.ExecuteCheckIn( "MC2", Constants.KID,Constants.CENTRAL, stationAirport, airportPassengerSummary  );
        
        assertEquals(150, stationAirport.getTotalCollection());
        assertEquals(0, stationAirport.getTotalDiscount());

        assertEquals(0, stationCentral.getTotalCollection());
        assertEquals(0, stationCentral.getTotalDiscount());
    }

    @Test
    public void TestForUpdateMetroCardBalance()
    {
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("20");
        MetroCard metroCard = new MetroCard(metroCardNumber);
        metroCardController.updateMetroCardBalance(metroCard.getMetroCardNumber(), balance);

        assertEquals( 1, MetroCardRepository.MetroCardMap.size() );
    }

    @Test
    public void TestForCreateMetroCard()
    {
        String metroCardNumber = "MC1";
        int balance = Integer.parseInt("25");
        MetroCardController metroCardController = new MetroCardController();
        MetroCard metroCard = metroCardController.createMetroCard( metroCardNumber, balance );

        assertEquals( metroCardNumber, metroCard.getMetroCardNumber() );
    }

    @Test
    public void TestForMetroCard()
    {
        String metroCardNumber = "MC1";
        MetroCard metroCard = new MetroCard(metroCardNumber);
        assertEquals("MC1", metroCard.getMetroCardNumber());

        assertEquals( metroCardNumber, metroCard.getMetroCardNumber() );
    }

    @Test
    public void TestForMetroCardRepository()
    {
        HashMap<String,Integer> dummyHashMap = new HashMap<>();
        dummyHashMap.put("A", 1);
        dummyHashMap.put("B", 2);
        MetroCardRepository metroCardRepository = new MetroCardRepository();
        metroCardRepository.setMetroCardMap(dummyHashMap);

        assertEquals(2,MetroCardRepository.MetroCardMap.size());
        MetroCardRepository.clearMap();
        assertEquals(0,MetroCardRepository.MetroCardMap.size());
    }

    @Test
    public void TestForStationCardRepository()
    {
        StationRepository stationRepository = new StationRepository();

        stationRepository.setPassengerTypeCount("A");
        assertEquals(1,stationRepository.getPassengerTypeCount("A"));
        stationRepository.setPassengerTypeCount("A");
        assertEquals(2,stationRepository.getPassengerTypeCount("A"));
        stationRepository.setPassengerTypeCount("B");
        assertEquals(2,stationRepository.passengerTypeCount.size());
    }

    @Test
    public void TestForPairComparatorSort()
    {
        //First Case
        ArrayList<Pair> unsortedList = new ArrayList<Pair>();
        unsortedList.add(new Pair("A",2));
        unsortedList.add(new Pair("B",2));
        unsortedList.add(new Pair("C",3));
        
        Collections.sort( unsortedList, Pair.PairComparator );
        assertEquals( 3, unsortedList.size() );

        //Second Case
        unsortedList = new ArrayList<Pair>();
        unsortedList.add(new Pair("A",2));
        unsortedList.add(new Pair("B",2));
        unsortedList.add(new Pair("C",3));
        
        Collections.sort( unsortedList, Pair.PairComparator );
        assertEquals( "C", unsortedList.get(0).getKey() );
        assertEquals( "A", unsortedList.get(1).getKey() );
        assertEquals( "B", unsortedList.get(2).getKey() );
    }
}