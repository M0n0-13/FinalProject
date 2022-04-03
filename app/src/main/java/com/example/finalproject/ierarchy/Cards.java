package com.example.finalproject.ierarchy;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.Random;

public class Cards {
    Random rand=new Random();
    int cardID;
    String name;
    String description;
    String type;
    short phases;
    double hp;
    short NowPhase;
    Bitmap cardArt;
    double strength;

    Bitmap mellieBit;
    Bitmap wizardBit;
    Bitmap barricadeBit;
    Bitmap magicBit;


    double usualTimeChange;
    /*type - type of soldier
    * phases and NowPhase must be odd
    */

    public Cards(String name, String description, int cardID,String type, short phases,
                 double hp, short nowPhase, Bitmap cardArt, double strength,
                 double usualTimeChange) {
        this.name = name;
        this.description = description;
        this.cardID = cardID;
        this.type = type;
        this.phases = phases;
        this.hp = hp;
        NowPhase = nowPhase;
        this.cardArt = cardArt;
        this.strength = strength;
        this.usualTimeChange = usualTimeChange;
    }

    public void conservate(Cards card){
        short phases  = card.phases;
        short NowPhase=card.NowPhase;
        boolean Buff=rand.nextBoolean();

        if(Buff==true){
            if(NowPhase!=phases){
                up(card);
            }else{
                down(card);
            }
        }else{
            if (NowPhase!=1){
                down(card);
            }else{
                up(card);
            }
        }



    }
    private void up(Cards card){
        card.NowPhase++;
        card.strength+=card.usualTimeChange;

    }
    private void down(Cards card){
        card.NowPhase--;
        card.strength-=card.usualTimeChange;

    }
}
