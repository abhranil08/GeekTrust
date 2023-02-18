package com.example.geektrust.Repository;

import java.util.HashMap;

public class StationRepository {
    //Mapping : Passenger -> Number of passengers
    public HashMap<String, Integer> passengerTypeCount;

    public StationRepository( )
    {
        this.passengerTypeCount = new HashMap<>();
    }
    public int getPassengerTypeCount( String Passenger )
    {   
        if( passengerTypeCount.containsKey( Passenger ))
            return passengerTypeCount.get( Passenger );
        return 0;
    }

    public void setPassengerTypeCount( String Passenger )
    {
        passengerTypeCount.put( Passenger, getPassengerTypeCount(Passenger)+1);
    }

    public HashMap<String, Integer> getPassengerTypeCountMap()
    {
        return passengerTypeCount;
    }
    
}
