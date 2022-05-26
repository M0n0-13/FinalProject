package com.example.finalproject;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FightWithBotActivity extends AppCompatActivity {
    public int chosenColumn = -1;

    public static int botsHP = 4000;
    public static int playersHP = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        botsHP = 4000;
        playersHP = 4000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        TextView nameOfEnemy=findViewById(R.id.nameOfEnemy);
        TextView hpOfEnemy = findViewById(R.id.hpOfEnemy);
        TextView nameOfPlayer = findViewById(R.id.nameOfPlayer);
        TextView hpOfPlayer = findViewById(R.id.hpOfPlayer);
        Button stopMove = findViewById(R.id.transmitMove);
        Random random = new Random();
        nameOfEnemy.setText("Bot"+random.nextInt(100000));

        hpOfEnemy.setText("Hp: "+botsHP);
        /*String name, String description, int cardID,String type, short phases,
                 double hp, short nowPhase, Bitmap cardArt, double strength,
                 double usualTimeChange*/

        ImageView fightField[][]=new ImageView[3][4];





        fightField[0][0] = findViewById(R.id.fight_1s_1e);
        fightField[1][0] = findViewById(R.id.fight_2s_1e);
        fightField[2][0] = findViewById(R.id.fight_3s_1e);
        fightField[0][1] = findViewById(R.id.fight_1s_2e);
        fightField[1][1] = findViewById(R.id.fight_2s_2e);
        fightField[2][1] = findViewById(R.id.fight_3s_2e);
        fightField[0][2] = findViewById(R.id.fight_1s_3e);
        fightField[1][2] = findViewById(R.id.fight_2s_3e);
        fightField[2][2] = findViewById(R.id.fight_3s_3e);
        fightField[0][3] = findViewById(R.id.fight_1s_4e);
        fightField[1][3] = findViewById(R.id.fight_2s_4e);
        fightField[2][3] = findViewById(R.id.fight_3s_4e);

        Intent toEnd = new Intent(this,EndOfTheGameActivity.class);
        Cards cardsField[][]= new Cards[3][4];
        stopMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Unique mechanic of this game
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        if(cardsField[i][j]!=null)
                            cardsField[i][j].conservate();

                    }
                }








                //Clean from magic symbols
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (cardsField[i][j]==null){
                            fightField[i][j].setImageResource(R.drawable.without_draw);
                        }
                    }
                }

//TODO--------------------------------------------------------------------------------------------
                //fireball code
                for (int i = 0; i < 3; i++) {

                    if (cardsField[i][0]!=null&&cardsField[i][0].cardID==3||
                            cardsField[i][3]!=null&&cardsField[i][3].cardID==3){
                        for (int j = 0; j < 4; j++) {
                            if(cardsField[i][j]!=null){
                                cardsField[i][j].hp-= fireball.strength;

                                if(cardsField[i][j].hp<=0){
                                    fightField[i][j].setImageResource(R.drawable.magic);

                                    cardsField[i][j]=null;

                                }
                            }else{

                                fightField[i][j].setImageResource(R.drawable.magic);

                            }
                        }
                        cardsField[i][0]=null;
                    }

                }





                //give to unit moving
                for (int d = 0; d < 3; d++) {
                    for (int q = 0; q < 4; q++) {
                        if (cardsField[d][q]!=null&&(cardsField[d][q].cardID==1||
                                cardsField[d][q].cardID==2)){
                            cardsField[d][q].wasMove = false;
                        }

                    }
                }



                //Your Knight behaviour
                for (int i = 0; i<3; i++) {
                    for (int j = 0; j<3; j++) {
                        if(cardsField[i][j]!=null){
                            if (cardsField[i][j].cardID==1&&
                                    cardsField[i][j].your==true&&cardsField[i][j+1]==null&&
                                    cardsField[i][j].wasMove == false){
                                Log.i(TAG, "was moved!");
                                cardsField[i][j].wasMove = true;
                                cardsField[i][j+1]=cardsField[i][j];
                                cardsField[i][j]=null;
                                fightField[i][j+1].setImageResource(R.drawable.mellie);




                                fightField[i][j].setImageResource(R.drawable.without_draw);

                            }else if (cardsField[i][j].cardID == 1 &&cardsField[i][j].your == true
                                    &&cardsField[i][j + 1] != null&&cardsField[i][j+1].your == false){
                                cardsField[i][j+1].hp-=cardsField[i][j].strength;
                                if(cardsField[i][j+1].cardID==1){
                                    cardsField[i][j].hp-=cardsField[i][j+1].strength;
                                }
                                if(cardsField[i][j+1].hp<=0){
                                    cardsField[i][j+1]=null;
                                    fightField[i][j+1].setImageResource(R.drawable.without_draw);
                                }
                            }
                        }
                    }







                    //checking damage to bot
                    if (cardsField[i][3]!=null&&cardsField[i][3].your==true) {
                        botGetDMG(cardsField[i][3].strength);
                        cardsField[i][3].hp -= 1000;
                        if (cardsField[i][3].hp < 0) {
                            cardsField[i][3] = null;
                            fightField[i][3].setImageResource(R.drawable.without_draw);
                        }
                    }


                    //checking damage to Player
                    if (cardsField[i][0]!=null&&cardsField[i][0].your==false) {
                        playerGetDMG(cardsField[i][0].strength);
                        cardsField[i][0].hp -= 1000;
                        if (cardsField[i][0].hp < 0) {
                            cardsField[i][0] = null;
                            fightField[i][0].setImageResource(R.drawable.without_draw);
                        }
                    }

                }


                //Your Wizard behaviour

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (cardsField[i][j]!=null&&cardsField[i][j].cardID==2&&
                                cardsField[i][j].your==false){

                            for (int z = 0; z < 3; z++) {
                                for (int p = 0; p < 4; p++) {
                                        if (cardsField[z][p]!=null&&cardsField[z][p].your==false){
                                        cardsField[i][j].wasMove = true;
                                        cardsField[z][p].hp -= cardsField[i][j].strength;
                                        if (cardsField[z][p].hp<0){
                                            cardsField[z][p]=null;
                                            fightField[z][p].setImageResource(R.drawable.without_draw);
                                        }
                                    }
                                }
                            }
                            if(j!=2&&cardsField[i][j]!=null&&cardsField[i][j].cardID==2 &&
                                    cardsField[i][j+1]==null&&cardsField[i][j].wasMove==false){
                                cardsField[i][j].wasMove=true;
                                fightField[i][j+1].setImageResource(R.drawable.wizard);
                                fightField[i][j].setImageResource(R.drawable.without_draw);
                                cardsField[i][j+1]=cardsField[i][j];
                                cardsField[i][j]=null;

                            }else if(j==2&&cardsField[i][j]!=null&&cardsField[i][j].cardID==2){
                                botGetDMG(cardsField[i][j].strength);
                            }



                        }
                    }


                }




                //Bots AI
                boolean supToSpawner=true;
                boolean wasSpawned=false;
                int numberOfEnemyUnits=0;
                int numberOfEnemyUnitsOnLine=0;
                for (int i = 0; i < 3; i++) {
                    numberOfEnemyUnitsOnLine=0;
                    for (int j = 0; j < 4; j++) {
                        if(cardsField[i][j]!=null&&cardsField[i][j].your==true){
                            numberOfEnemyUnits++;
                            numberOfEnemyUnitsOnLine++;

                            //behaviour when player placed knight
                            if(cardsField[i][j].cardID==1&&cardsField[i][3]==null){
                                cardsField[i][3]=new Cards(barricade, false);
                                fightField[i][3].setImageResource(R.drawable.barricade_e);
                                if(cardsField[0][3]==null){
                                    cardsField[0][3]=new Cards(wizard, false);
                                    fightField[0][3].setImageResource(R.drawable.wizard_e);
                                }else if(cardsField[1][3]==null){
                                    cardsField[1][3]=new Cards(wizard, false);
                                    fightField[1][3].setImageResource(R.drawable.wizard_e);
                                }else if(cardsField[2][3]==null){
                                    cardsField[2][3]=new Cards(wizard, false);
                                    fightField[2][3].setImageResource(R.drawable.wizard_e);
                                }


                                //behaviour when player placed wizard
                            }else if (cardsField[i][j].cardID==2&&
                                    (cardsField[i][3]==null||
                                            cardsField[i][3]!=null&&cardsField[i][3].your==false)){
                                cardsField[i][3]=new Cards(knight, false);
                                fightField[i][3].setImageResource(R.drawable.mellie_e);

                            }
                        }

                        /*if(numberOfEnemyUnitsOnLine>2){
                            if (cardsField[i][3]==null||cardsField[i][3]!=null&&
                                    cardsField[i][3].cardID!=4){
                                cardsField[i][3]=new Cards(fireball,false);
                                fightField[i][3].setImageResource(R.drawable.magic);
                            }}
                            */


                        /*if (numberOfEnemyUnits>2) {
                            if (cardsField[i][3] == null) {
                                cardsField[i][3] = new Cards(wizard, false);
                                fightField[i][3].setImageResource(R.drawable.wizard_e);
                            }
                        }*/


                        //if Player didnt place any units on line
                        if (wasSpawned==false){

                            wasSpawned=true;
                        }else{

                            wasSpawned=false;
                        }
                        if(numberOfEnemyUnitsOnLine==0&&wasSpawned==false){


                            cardsField[i][3]=new Cards(knight, false);
                            fightField[i][3].setImageResource(R.drawable.mellie_e);
                        }


                    }

                }
                numberOfEnemyUnits=0;

                //Bots knight behaviour
                for (int i = 0; i<3; i++) {
                    for (int j = 1; j < 4; j++) {
                        if (cardsField[i][j] != null) {
                            if (cardsField[i][j].cardID == 1 &&
                                    cardsField[i][j].your == false && cardsField[i][j - 1] == null ) {
                                Log.i(TAG, "was moved!");

                                cardsField[i][j - 1] = cardsField[i][j];
                                cardsField[i][j] = null;
                                fightField[i][j - 1].setImageResource(R.drawable.mellie_e);
                                fightField[i][j].setImageResource(R.drawable.without_draw);

                            }else if (cardsField[i][j].cardID == 1 &&cardsField[i][j].your == false
                                    &&cardsField[i][j - 1] != null&&cardsField[i][j-1].your == true){
                                cardsField[i][j-1].hp-=cardsField[i][j].strength;
                                if(cardsField[i][j-1].cardID==1){
                                    cardsField[i][j].hp-=cardsField[i][j-1].strength;
                                }
                                if(cardsField[i][j-1].hp<=0){
                                    cardsField[i][j-1]=null;
                                    fightField[i][j-1].setImageResource(R.drawable.without_draw);
                                }
                            }

                        }


                    }

                }

                //Bots wizard behaviour
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (cardsField[i][j]!=null&&cardsField[i][j].cardID==2
                                &&cardsField[i][j].your==false){

                            for (int z = 0; z < 3; z++) {
                                for (int p = 0; p < 4; p++) {
                                    if (cardsField[z][p]!=null&&cardsField[z][p].your==true){
                                        cardsField[i][j].wasMove = true;

                                        cardsField[z][p].hp -= cardsField[i][j].strength;
                                        if (cardsField[z][p].hp<0){
                                            cardsField[z][p]=null;
                                            fightField[z][p].setImageResource(R.drawable.without_draw);
                                        }
                                    }
                                }
                            }
                            if(j!=1&&cardsField[i][j]!=null&&cardsField[i][j].cardID==2 &&
                                    cardsField[i][j-1]==null&&cardsField[i][j].wasMove==false){
                                cardsField[i][j].wasMove=true;
                                fightField[i][j-1].setImageResource(R.drawable.wizard_e);
                                fightField[i][j].setImageResource(R.drawable.without_draw);
                                cardsField[i][j-1]=cardsField[i][j];
                                cardsField[i][j]=null;

                            }else if(j==1&&cardsField[i][j]!=null&&cardsField[i][j].cardID==2){
                                playerGetDMG(cardsField[i][j].strength);
                            }



                        }
                    }


                }


                //barricade fix(bots)
                for (int i = 0; i < 3; i++) {
                    if(cardsField[i][3]!=null&&cardsField[i][3].cardID==4&&cardsField[i][2]!=null
                            &&cardsField[i][2].cardID==1&&cardsField[i][2].your==true){
                        cardsField[i][3].hp-=cardsField[i][2].strength;
                        if(cardsField[i][3].hp<=0){
                            cardsField[i][3]=null;
                            fightField[i][3].setImageResource(R.drawable.without_draw);
                        }
                    }
                }









            }


        });

//TODO-----------------------------------------------------------------------------------------------------









        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                fightField[i][j].setImageResource(R.drawable.without_draw);
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i==0){
                    fightField[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosenColumn = 0;




                        }
                    });
                }else if (i==1){
                    fightField[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosenColumn = 1;



                        }
                    });
                }else if (i==2){
                    fightField[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            chosenColumn = 2;




                        }
                    });
                }

            }
        }


        ImageView deck[]=new ImageView[4];

        deck[0] = findViewById(R.id.card1);
        deck[1]= findViewById(R.id.card2);
        deck[2] = findViewById(R.id.card3);
        deck[3]= findViewById(R.id.card4);





        deck[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(chosenColumn==0){

                    cardsField[0][0]=new Cards(knight);
                    fightField[0][0].setImageResource(R.drawable.mellie);


                }else if(chosenColumn==1){

                    cardsField[1][0]=new Cards(knight);
                    fightField[1][0].setImageResource(R.drawable.mellie);

                }else if(chosenColumn==2){

                    cardsField[2][0]=new Cards(knight);
                    fightField[2][0].setImageResource(R.drawable.mellie);

                }

            }


        });
        deck[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(chosenColumn==0){
                    cardsField[0][0]=new Cards(wizard);
                    fightField[0][0].setImageResource(R.drawable.wizard);


                }else if(chosenColumn==1){
                    cardsField[1][0]=new Cards(wizard);
                    fightField[1][0].setImageResource(R.drawable.wizard);

                }else if(chosenColumn==2){
                    cardsField[2][0]=new Cards(wizard);
                    fightField[2][0].setImageResource(R.drawable.wizard);

                }
            }
        });
        deck[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenColumn==0){
                    cardsField[0][0]=new Cards(barricade);
                    fightField[0][0].setImageResource(R.drawable.barricade);


                }else if(chosenColumn==1){
                    cardsField[1][0]=new Cards(barricade);
                    fightField[1][0].setImageResource(R.drawable.barricade);

                }else if(chosenColumn==2){
                    cardsField[2][0]=new Cards(barricade);
                    fightField[2][0].setImageResource(R.drawable.barricade);

                }
            }
        });
        //TODO доработать со стороны того чтобы било по всем
        deck[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenColumn==0){
                    cardsField[0][0]=new Cards(fireball);
                    fightField[0][0].setImageResource(R.drawable.magic);



                }else if(chosenColumn==1){
                    cardsField[1][0]=new Cards(fireball);
                    fightField[1][0].setImageResource(R.drawable.magic);

                }else if(chosenColumn==2){
                    cardsField[2][0]=new Cards(fireball);
                    fightField[2][0].setImageResource(R.drawable.magic);

                }
            }
        });



        nameOfPlayer.setText(MainActivity.UsersName);
        hpOfPlayer.setText("Hp: "+playersHP);



    }

    void botGetDMG(int dmg){
        botsHP-=dmg;
        TextView EndMessage=findViewById(R.id.new_about_win);
        Intent toEnd = new Intent(this,EndOfTheGameActivity.class);
        TextView hpOfEnemy = findViewById(R.id.hpOfEnemy);
        hpOfEnemy.setText("Hp: "+botsHP);
        if (botsHP<=0){

            startActivity(toEnd);
        }

    }
    void playerGetDMG(int dmg){
        TextView EndMessage=findViewById(R.id.new_about_win);
        Intent toEnd = new Intent(this,EndOfTheGameActivity.class);
        playersHP-=dmg;
        TextView hpOfPlayer = findViewById(R.id.hpOfPlayer);
        hpOfPlayer.setText("Hp: "+playersHP);
        if (playersHP<=0){
            startActivity(toEnd);

        }

    }
    //base cards:
    Cards knight = new Cards("Knight","", 1, "mellie",5, 1400,3,
            700, 70,true,false);
    Cards wizard = new Cards("Wizard",
            "Usual wizard with stick!",2, "wizard",
            7,1000,4,500,10,true, false);
    Cards fireball = new Cards("Fire Sharp",
            "the most usual line from fire, what can make good carry ",3, "magic",
            2,1,1,2250,0,true);
    Cards barricade = new Cards("Barricade",
            "barricade can stop enemies",4, "barricade",
            2,3000,1,1,0,true);
}