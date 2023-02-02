package com.example.geektrust.Power;
import com.example.geektrust.Util.*;

public class DirectionChangePower {

    //Power Used when destination is at a higher point with respect to the source ( y-axis )
    private int TurnsForHigherDestination(  String direction, int sourceXCoordinate, int SourceYCoordinate,
                                            int destinationXCoordinate, int destinationYCoordinate )
    {
        int turns = Constant.TWO;
        if( ( destinationXCoordinate > sourceXCoordinate ) && ( direction.equals( Constant.NORTH ) || direction.equals( Constant.EAST ) ) )
            turns = Constant.ONE;
        else if( ( destinationXCoordinate < sourceXCoordinate ) && ( direction.equals( Constant.NORTH ) || direction.equals( Constant.WEST ) ) )
            turns = Constant.ONE;
        return turns;
    }
    //Power Used when destination is at a lower point with respect to the source ( y-axis )
    private int TurnsForLowerDestination(   String direction, int sourceXCoordinate, int SourceYCoordinate,
                                            int destinationXCoordinate, int destinationYCoordinate )
    {
        int turns = Constant.TWO;
        if(( destinationXCoordinate < sourceXCoordinate ) && ( direction.equals( Constant.SOUTH ) || direction.equals( Constant.WEST ) ) )
            turns = Constant.ONE;
        else if( ( destinationXCoordinate > sourceXCoordinate ) && ( direction.equals( Constant.SOUTH ) || direction.equals( Constant.EAST ) ) )
            turns = Constant.ONE;
        return turns;
    }
    //Power Used when destination is at a same level with respect to the source ( y-axis )
    private int TurnsWhenSameCoordinates(   String direction, int sourceXCoordinate, int SourceYCoordinate,
                                                int destinationXCoordinate, int destinationYCoordinate )
    {
        int turns = 0;
        if( destinationXCoordinate == sourceXCoordinate )
        {
            turns = TurnsWhenSameXCoordinates( direction, sourceXCoordinate, SourceYCoordinate, 
                                destinationXCoordinate, destinationYCoordinate );
        }
        else if( destinationYCoordinate == SourceYCoordinate )
        {
            turns = TurnsWhenSameYCoordinates( direction, sourceXCoordinate, SourceYCoordinate, 
                                destinationXCoordinate, destinationYCoordinate );
        }
        return turns;
    }

    private int TurnsWhenSameXCoordinates(  String direction, int sourceXCoordinate, int SourceYCoordinate,
                                                int destinationXCoordinate, int destinationYCoordinate )
    {
        int turns = 0;
        if( ( destinationYCoordinate != SourceYCoordinate ) && 
                ( direction.equals( Constant.EAST ) || direction.equals( Constant.WEST ) ) )
            turns = Constant.ONE;
        else
        {
            if( destinationYCoordinate > SourceYCoordinate )
                if( direction.equals( Constant.SOUTH ) )
                    turns = Constant.TWO;

            if( destinationYCoordinate < SourceYCoordinate )
                if( direction.equals( Constant.NORTH ) )
                    turns = Constant.TWO;
        }
        return turns;
    }

    private int TurnsWhenSameYCoordinates(   String direction, int sourceXCoordinate, int SourceYCoordinate,
                                                int destinationXCoordinate, int destinationYCoordinate )
    {
        int turns = 0;
        if( ( destinationXCoordinate != sourceXCoordinate ) && 
                ( direction.equals( Constant.SOUTH ) || direction.equals( Constant.NORTH ) ) )
            turns = Constant.ONE;
        else
        {
            if( destinationXCoordinate > sourceXCoordinate )
                if( direction.equals( Constant.WEST ) )
                    turns = Constant.TWO;

            if( destinationXCoordinate < sourceXCoordinate )
                if( direction.equals( Constant.EAST ) )
                    turns = Constant.TWO;
        }
        return turns;
    }

    public int TotalTurns(  String direction, int sourceXCoordinate, int SourceYCoordinate,
                            int destinationXCoordinate, int destinationYCoordinate )
    {
        int turns = 0;
        if( destinationYCoordinate == SourceYCoordinate || destinationXCoordinate == sourceXCoordinate )
        {
            turns = TurnsWhenSameCoordinates( direction, sourceXCoordinate, SourceYCoordinate, 
                                destinationXCoordinate, destinationYCoordinate );
        }
        else if( destinationYCoordinate > SourceYCoordinate )
        {
            turns = TurnsForHigherDestination( direction, sourceXCoordinate, SourceYCoordinate, 
                                destinationXCoordinate, destinationYCoordinate );
        }    
        else if( destinationYCoordinate < SourceYCoordinate )
        {
            turns = TurnsForLowerDestination( direction, sourceXCoordinate, SourceYCoordinate, 
                                destinationXCoordinate, destinationYCoordinate );           
        }
        return turns;
    }
    
}
