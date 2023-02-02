package com.example.geektrust.Power;

public class MovementPower {

    public int calculateMovementPower(  int sourceXCoordinate, int SourceYCoordinate, 
                                int destinationXCoordinate, int destinationYCoordinate, 
                                String direction) {
        int  xDifference = 0, yDifference = 0;

        if ( destinationXCoordinate != sourceXCoordinate){   
            xDifference = Math.abs( sourceXCoordinate - destinationXCoordinate);
                                   
        }
        
        if ( destinationYCoordinate != SourceYCoordinate){   
            yDifference = Math.abs( SourceYCoordinate - destinationYCoordinate);                      
        }                                                                
        int MovementPower = xDifference + yDifference;                           
        return MovementPower;
    }
    
}
