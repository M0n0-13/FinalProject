package com.example.finalproject;

import java.util.Random;

public class Cards {
    Random rand=new Random();
    int cardID;
    String name;
    String description;
    String type;
    int phases;
    int hp;
    int nowPhase;
    boolean your;
    int strength;
    int usualTimeChange;


    /*type - type of soldier
    * phases and NowPhase must be odd
    */

    public Cards( String name, String description,int cardID ,String type, int phases, int hp,int nowPhase,  int strength, int usualTimeChange,boolean your) {
        this.cardID = cardID;
        this.name = name;
        this.description = description;
        this.type = type;
        this.phases = phases;
        this.hp = hp;
        this.nowPhase = nowPhase;
        this.your = your;
        this.strength = strength;
        this.usualTimeChange = usualTimeChange;
    }

    public void conservate(){


        boolean Buff=rand.nextBoolean();

        if(Buff==true){
            if(nowPhase!=phases){
                nowPhase++;
                up();
            }else{
                nowPhase--;
                down();
            }
        }else{
            if (nowPhase!=1){
                nowPhase--;
                down();
            }else{
                nowPhase++;
                up();
            }
        }



    }
    private void up(){
        nowPhase++;
        strength+=usualTimeChange;

    }
    private void down(){
        nowPhase--;
        strength-=usualTimeChange;

    }
    //base cards:
    /*Cards knight = new Cards("Knight","", 1, "mellie",5, 20003,3,
            700, 75,true);
    Cards wizard = new Cards("Wizard",
            "Usual wizard with stick!",2, "wizard",
            7,1500,4,360,60,true);
    Cards fireball = new Cards("Fire Sharp",
            "the most usual line from fire, what can make good carry ",3, "magic",
            1,1,1,2500,0,true);
    Cards barricade = new Cards("Barricade",
            "barricade can stop enemies",4, "barricade",
            1,3000,1,0,0,true);*/

}
