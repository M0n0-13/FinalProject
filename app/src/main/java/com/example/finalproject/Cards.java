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
    boolean your=true;

    int strength;
    int usualTimeChange;
    boolean wasMove;





    /*type - type of soldier
    * phases and NowPhase must be odd
    */
    public Cards(Cards cards){
        this.cardID = cards.cardID;
        this.name = cards.name;
        this.description = cards.description;
        this.type = cards.type;
        this.phases = cards.phases;
        this.hp = cards.hp;
        this.nowPhase = cards.nowPhase;
        this.your = cards.your;
        this.strength = cards.strength;
        this.usualTimeChange = cards.usualTimeChange;
    }
    public Cards(Cards cards, boolean your){
        this.cardID = cards.cardID;
        this.name = cards.name;
        this.description = cards.description;
        this.type = cards.type;
        this.phases = cards.phases;
        this.hp = cards.hp;
        this.nowPhase = cards.nowPhase;
        this.your = your;
        this.strength = cards.strength;
        this.usualTimeChange = cards.usualTimeChange;
    }
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
    public Cards( String name, String description,int cardID ,String type, int phases, int hp,int nowPhase,  int strength, int usualTimeChange,boolean your, boolean wasMove) {
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
        this.wasMove =wasMove;
    }

    public void conservate(){
        boolean buff=rand.nextBoolean();
        if(buff==true){
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
        strength+=usualTimeChange;}
    private void down(){
        nowPhase--;
        strength-=usualTimeChange;}
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
