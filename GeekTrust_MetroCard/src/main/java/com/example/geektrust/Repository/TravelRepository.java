package com.example.geektrust.Repository;

import java.util.HashMap;

public class TravelRepository {
    // Mapping : PassengerMapping for discount
    public static HashMap<String,Integer> PassengerMapForDiscount = new HashMap<>();

    public void setPassengerMapForDiscount( HashMap<String,Integer> PassengerMapForDiscountConstruct )
    {
        PassengerMapForDiscount = PassengerMapForDiscountConstruct;
    }
    public HashMap<String,Integer> getPassengerMapForDiscount()
    {
        return PassengerMapForDiscount;
    }
    public static void clearPassengerMap()
    {
        PassengerMapForDiscount = new HashMap<>();
    }
    
}
