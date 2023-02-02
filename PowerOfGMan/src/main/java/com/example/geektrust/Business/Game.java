package com.example.geektrust.Business;
import com.example.geektrust.Util.*;
public class Game {
    private int power;
    private Grid grid;
    
    private Coordinate source;
    private Coordinate destination;


    //Setter methods
    private void setSourceCoordinates( int XCoordinate, int YCoordinate, String Direction )
    {
        this.getSource().setXCoordinate( XCoordinate );
        this.getSource().setYCoordinate( YCoordinate );
        this.getSource().setDirection( Direction );

    }

    private void setDestinationCoordinates( int XCoordinate, int YCoordinate )
    {
        this.getDestination().setXCoordinate( XCoordinate );
        this.getDestination().setYCoordinate( YCoordinate );
    }

    private void setSource( Coordinate source )
    {
        this.source = source;
    }

    private void setDestination( Coordinate destination )
    {
        this.destination = destination;
    }

    public void setPower( int power )
    {
        this.power = power;
    }

    public void setGrid( Grid grid )
    {
        this.grid = grid ;
    }


    //Getter methods
    public int getPower()
    {
        return power;
    }

    public Grid getGrid()
    {
        return grid;
    }

    public Coordinate getSource()
    {
        return source;
    }

    public Coordinate getDestination()
    {
        return destination;
    }


    //Utility methods
    public void ParseInputsAndExecuteGame( String input )
    {
        String[] cmd = input.split(" ");
        switch ( cmd[0] )
        {
            case Constant.SOURCE:
                setSource( new Coordinate());
                setSourceCoordinates(   Integer.parseInt(cmd[1]), 
                                        Integer.parseInt(cmd[2]), 
                                        cmd[3] );
                break;
            case Constant.DESTINATION:
                setDestination(new Coordinate());
                setDestinationCoordinates(  Integer.parseInt(cmd[1]), 
                                            Integer.parseInt(cmd[2]) );
                break;
            case Constant.PRINT_POWER:
                printRemainingPower( getFinalPower() );
        }
                                                
    }

    public int getFinalPower()
    {
        int power  = this.getGrid().calculatePowerRemaining(    this.getSource().getXCoordinate(), 
                                                                this.getSource().getYCoordinate(), 
                                                                this.getDestination().getXCoordinate(), 
                                                                this.getDestination().getYCoordinate(),
                                                                this.getSource().getDirection() );

        
        return this.getPower() - power;
                                                
    }

    public void printRemainingPower( int finalPowerRemaining )
    {
        System.out.println("POWER "+finalPowerRemaining);
    }

    
}
