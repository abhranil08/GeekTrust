package com.example.geektrust.Model;

public class MetroCard {
    String MetroCardNumber;

    public MetroCard( String MetroCardNumber )
    {
        setMetroCardNumber(MetroCardNumber);
    }

    public void setMetroCardNumber( String MetroCardNumber)
    {
        this.MetroCardNumber = MetroCardNumber;
    }

    public String getMetroCardNumber( )
    {
        return MetroCardNumber;
    }
    
   
}
