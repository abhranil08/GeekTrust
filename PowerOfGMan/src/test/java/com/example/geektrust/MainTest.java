package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.geektrust.Business.Game;
import com.example.geektrust.Business.Grid;
import com.example.geektrust.Util.Constant;


public class MainTest {
    @Test
    void testMain1()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 2 1 E" );
        game.ParseInputsAndExecuteGame( "DESTINATION 4 3" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 155;
        assertEquals( Expected, FinalPower );

    }

    @Test
    void testMain2()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 5 5 N" );
        game.ParseInputsAndExecuteGame( "DESTINATION 3 3" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 150;
        assertEquals( Expected, FinalPower );

    }

    @Test
    void testMain3()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 1 4 W" );
        game.ParseInputsAndExecuteGame( "DESTINATION 5 2" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 130;
        assertEquals( Expected, FinalPower );

    }

    @Test
    void testMain4()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 2 1 S" );
        game.ParseInputsAndExecuteGame( "DESTINATION 4 5" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 130;
        assertEquals( Expected, FinalPower );

    }

    @Test
    void testMain5()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 5 5 E" );
        game.ParseInputsAndExecuteGame( "DESTINATION 1 2" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 120;
        assertEquals( Expected, FinalPower );

    }

    @Test
    void testMain6()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 1 1 S" );
        game.ParseInputsAndExecuteGame( "DESTINATION 1 2" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 180;
        assertEquals( Expected, FinalPower );

    }

    @Test
    void testMain7()
    {
        Game game = new Game();
        
        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        game.ParseInputsAndExecuteGame( "SOURCE 3 1 W" );
        game.ParseInputsAndExecuteGame( "DESTINATION 5 1" );
        game.ParseInputsAndExecuteGame( "PRINT_POWER" );
        int FinalPower = game.getFinalPower();
        int Expected = 170;
        assertEquals( Expected, FinalPower );
    }
}