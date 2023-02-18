package com.example.geektrust.Controller;


import com.example.geektrust.Model.Station;
import com.example.geektrust.Repository.MetroCardRepository;
import com.example.geektrust.Repository.StationRepository;
import com.example.geektrust.Repository.TravelRepository;
import com.example.geektrust.Util.Constants;

public class TravelController {
    public int calculateTravelCost( String MetroCardNumber, String PassengerType, String Station, Station stationType )
    {
        boolean returnDiscount = false;
        if( TravelRepository.PassengerMapForDiscount.containsKey( MetroCardNumber ) )
        {
            Integer flag = TravelRepository.PassengerMapForDiscount.get( MetroCardNumber );
            if(flag == 1 )
            {
                TravelRepository.PassengerMapForDiscount.put( MetroCardNumber, 0 );
                returnDiscount = true;
            }
        }
        else
        {
            TravelRepository.PassengerMapForDiscount.put( MetroCardNumber, 1 ); 
        }
        int traveltravelCostForPassenger = travelCostForPassengerType( PassengerType );
        if( returnDiscount )  
        {
            int discount = (int)(0.50 * traveltravelCostForPassenger);
            stationType.UpdateTotalDiscount(discount);
            return traveltravelCostForPassenger - discount;

        }    
        return travelCostForPassengerType( PassengerType );
    }

    public int travelCostForPassengerType( String PassengerType )
    {
        switch ( PassengerType )
        {
            case Constants.ADULT:
                return Constants.ADULT_COST;
            case Constants.SENIOR_CITIZEN:
                return Constants.SENIOR_CITIZEN_COST;
            case Constants.KID:
                return Constants.KIDS_COST;
        }
        return 0;
    }

    public void ExecuteCheckIn( String metroCardNumber, String PassengerType, String Station, 
                                Station stationType, StationRepository stationRepository ) 
    {
        MetroCardController metroCardController = new MetroCardController();
        int balance = MetroCardRepository.MetroCardMap.get( metroCardNumber );
        int travelCost = calculateTravelCost( metroCardNumber, PassengerType, Station, stationType );
        int total=travelCost;

        if( balance < travelCost )
        {
            balance = travelCost - balance;
            int serviceFee = (int)(.02 * balance );
            total = travelCost + serviceFee;
            metroCardController.updateMetroCardBalance(metroCardNumber,0);
        }
        else
            metroCardController.updateMetroCardBalance(metroCardNumber,balance - travelCost);


        stationType.UpdateTotalCollection(total);
        stationRepository.setPassengerTypeCount(PassengerType);      
    }          
}
