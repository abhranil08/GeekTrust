package com.example.geektrust.Repository;

import java.util.HashMap;

public class MetroCardRepository {
    public static HashMap<String,Integer> MetroCardMap = new HashMap<>();

    //Mapping : MetroCardNumber -> Balance
    public void setMetroCardMap( HashMap<String,Integer> MetroCardMapConstruct )
    {
        MetroCardMap = MetroCardMapConstruct;
    }
    public HashMap<String,Integer> getMetroCardMap()
    {
        return MetroCardMap;
    }
    public static void clearMap()
    {
        MetroCardMap = new HashMap<>();
    }
    
}
