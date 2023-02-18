package com.example.geektrust.Model;

public abstract class Station {
    private int totalFareStation, totalDiscountStation ;
    
    public Station( int totalFareStation, int totalDiscountStation )
    {
        this.totalFareStation = totalFareStation;
        this.totalDiscountStation = totalDiscountStation;
    }
    public int getTotalCollection( )
    {
        return totalFareStation;
    }
    public void setTotalCollection( int totalFareStation )
    {
        this.totalFareStation = totalFareStation;
    }
    public int getTotalDiscount( )
    {
        return totalDiscountStation;
    }
    public void setTotalDiscount( int totalDiscountStation )
    {
        this.totalDiscountStation = totalDiscountStation;
    }
    public void UpdateTotalCollection( int totalFare )
    {
        int updatedValue = getTotalCollection() + totalFare;
        setTotalCollection( updatedValue );
    }
    public void UpdateTotalDiscount( int totalDiscount )
    {
        int updatedValue = getTotalDiscount() + totalDiscount;
        setTotalDiscount( updatedValue );
    } 

    public Station(){}

    
}
