package com.example.geektrust;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import com.example.geektrust.Business.Game;
import com.example.geektrust.Business.Grid;
import com.example.geektrust.Util.Constant;


public class Main {
    /**
     * @param args
     */
    public static void main(String[] args ) {
        Game game = new Game();

        game.setGrid(new Grid());
        game.setPower(Constant.POWER);

        try{
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); 
            while (sc.hasNextLine()) 
            {
                String input = sc.nextLine();
                game.ParseInputsAndExecuteGame( input );
            }
            sc.close();
        }
        catch(IOException e) {
        }
        }
}
