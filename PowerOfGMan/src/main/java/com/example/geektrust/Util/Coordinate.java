package com.example.geektrust.Util;


public class Coordinate {
    private int XCoordinate;
    private int YCoordinate;
    private String Direction;

    //Setter methods
    public void setXCoordinate( int Coordinate )
    {
        this.XCoordinate = Coordinate;
    }
    public void setYCoordinate( int Coordinate )
    {
        this.YCoordinate = Coordinate;
    }
    public void setDirection( String Direction )
    {
        this.Direction = Direction;
    }

    //Getter methods
    public int getXCoordinate()
    {
        return XCoordinate;
    }

    public int getYCoordinate()
    {
        return YCoordinate;
    }

    public String getDirection()
    {
        return Direction;
    }


    
}
