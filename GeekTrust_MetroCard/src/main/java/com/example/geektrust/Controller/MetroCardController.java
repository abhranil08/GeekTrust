package com.example.geektrust.Controller;

import com.example.geektrust.Model.MetroCard;
import com.example.geektrust.Repository.MetroCardRepository;

public class MetroCardController {
    
    public MetroCard createMetroCard( String MetroCardNumber, int Balance )
    {
        MetroCard metroCard = new MetroCard(MetroCardNumber);
        MetroCardRepository.MetroCardMap.putIfAbsent(metroCard.getMetroCardNumber(), Balance);
        return metroCard;
    }

    public void updateMetroCardBalance( String MetroCardNumber, int balance )
    {
        MetroCardRepository.MetroCardMap.put(MetroCardNumber, balance );
    }
    
}
