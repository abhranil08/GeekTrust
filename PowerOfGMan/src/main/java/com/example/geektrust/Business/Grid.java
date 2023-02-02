package com.example.geektrust.Business;

import com.example.geektrust.Util.*;
import com.example.geektrust.Power.DirectionChangePower;
import com.example.geektrust.Power.MovementPower;


public class Grid {
    public int calculatePowerRemaining( int sourceXCoordinate, int SourceYCoordinate, 
                                        int destinationXCoordinate, int destinationYCoordinate, 
                                        String direction)
    {
        DirectionChangePower directionChangePower = new DirectionChangePower();
        MovementPower movementPower = new MovementPower();

        int movementCost = ( movementPower.calculateMovementPower( sourceXCoordinate, SourceYCoordinate, 
                                                                        destinationXCoordinate, destinationYCoordinate, 
                                                                        direction 
                                                                 ) * Constant.MovePower );
        
        int directionChangeCost = ( directionChangePower.TotalTurns( direction, sourceXCoordinate, 
                                                                    SourceYCoordinate, destinationXCoordinate, 
                                                                    destinationYCoordinate 
                                                                    ) * Constant.TurnPower );
        int powerRemaining = ( movementCost + directionChangeCost );
        return powerRemaining;
    }
    
}
