package com.example.geektrust.Util;

import java.util.Comparator;

public class Pair {
    String key;
    int value;

    public Pair( String key, int value )
    {
        this.key = key;
        this.value = value;
    }

    public int getValue( String key )
    {
        return value;
    }
    public String getKey( )
    {
        return key;
    }

    public static Comparator<Pair> PairComparator = new Comparator<Pair>() 
    {
        @Override
        public int compare(Pair p1, Pair p2 ) 
        {
            if(p1.getValue(p1.getKey()) > p2.getValue(p2.getKey()) )
            {
                return -1;
            }
            else if( p1.getValue(p1.getKey()) == p2.getValue(p2.getKey()))    
            {
                return p1.getKey().compareTo(p2.getKey());
            }
            else
                return 1;
        }   
    };
}
