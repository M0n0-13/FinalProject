package com.example.finalproject;

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

public class FightActivity extends AppCompatActivity {
    public int chosenColumn = -1;
    //base cards:
    int botsHP = 10000;
    int playersHP = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

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
        DeckActivity DA = new DeckActivity();


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

        Cards cardsField[][]= new Cards[3][4];
        stopMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 4; j++) {
                        cardsField[i][j].conservate();
                    }
                }
                for (int i = 3; i > 0; i--) {
                    for (int j = 3; j >0; j--) {
                        if ((cardsField[i][j]==knight||cardsField[i][j]==wizard)&&
                                cardsField[i][j].your==true&&cardsField[i+1][j]==null){
                            cardsField[i+1][j]=cardsField[i][j];
                            cardsField[i][j]=null;
                            fightField[i+1][j].setImageResource(cardsField[i][j]==wizard?
                                    R.drawable.wizard:R.drawable.mellie);
                            fightField[i][j].setImageResource(R.drawable.without_draw);

                        }else if (cardsField[i+1][j].your==false){
                            cardsField[i+1][j].hp-=cardsField[i][j].strength;
                            cardsField[i][j].hp-=cardsField[i+1][j].strength;
                            if (cardsField[i][j].hp<=0){
                                cardsField[i][j]=null;
                                fightField[i][j].setImageResource(R.drawable.without_draw);
                            }
                            if(cardsField[i+1][j].hp<=0){
                                cardsField[i+1][j]=null;
                                fightField[i+1][j].setImageResource(R.drawable.without_draw);
                            }
                        }
                    }
                }
            }
        });


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
                    cardsField[0][0]=knight;
                    fightField[0][0].setImageResource(R.drawable.mellie);


                }else if(chosenColumn==1){
                    cardsField[1][0]=knight;
                    fightField[1][0].setImageResource(R.drawable.mellie);

                }else if(chosenColumn==2){
                    cardsField[2][0]=knight;
                    fightField[2][0].setImageResource(R.drawable.mellie);

                }
            }
        });
        deck[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenColumn==0){
                    cardsField[0][0]=wizard;
                    fightField[0][0].setImageResource(R.drawable.wizard);


                }else if(chosenColumn==1){
                    cardsField[1][0]=wizard;
                    fightField[1][0].setImageResource(R.drawable.wizard);

                }else if(chosenColumn==2){
                    cardsField[2][0]=wizard;
                    fightField[2][0].setImageResource(R.drawable.wizard);

                }
            }
        });
        deck[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenColumn==0){
                    cardsField[0][0]=barricade;
                    fightField[0][0].setImageResource(R.drawable.barricade);


                }else if(chosenColumn==1){
                    cardsField[1][0]=barricade;
                    fightField[1][0].setImageResource(R.drawable.barricade);

                }else if(chosenColumn==2){
                    cardsField[2][0]=barricade;
                    fightField[2][0].setImageResource(R.drawable.barricade);

                }
            }
        });
        //TODO доработать со стороны того чтобы било по всем
        deck[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chosenColumn==0){
                    cardsField[0][0]=fireball;
                    fightField[0][0].setImageResource(R.drawable.magic);



                }else if(chosenColumn==1){
                    cardsField[1][0]=fireball;
                    fightField[1][0].setImageResource(R.drawable.magic);

                }else if(chosenColumn==2){
                    cardsField[2][0]=fireball;
                    fightField[2][0].setImageResource(R.drawable.magic);

                }
            }
        });



        nameOfPlayer.setText(MainActivity.UsersName);
        hpOfPlayer.setText("Hp: "+playersHP);



    }
    void botGetDMG(int dmg){
        botsHP-=dmg;
        TextView hpOfEnemy = findViewById(R.id.hpOfEnemy);
        hpOfEnemy.setText("Hp: "+botsHP);
        if (botsHP<=0){
            Toast.makeText(getApplicationContext(),"YOU WON!",Toast.LENGTH_LONG);
            Intent toMenu = new Intent(this, MainActivity.class);
            startActivity(toMenu);
        }

    }
    void playerGetDMG(int dmg){
        playersHP-=dmg;
        TextView hpOfPlayer = findViewById(R.id.hpOfPlayer);
        hpOfPlayer.setText("Hp: "+playersHP);
        if (playersHP<=0){
            Toast.makeText(getApplicationContext(),"YOU LOSE!",Toast.LENGTH_LONG);
            Intent toMenu = new Intent(this, MainActivity.class);
            startActivity(toMenu);
        }

    }
    //base cards:
    Cards knight = new Cards("Knight","", 1, "mellie",5, 20003,3,
            700, 75,true);
    Cards wizard = new Cards("Wizard",
            "Usual wizard with stick!",2, "wizard",
            7,1500,4,360,60,true);
    Cards fireball = new Cards("Fire Sharp",
            "the most usual line from fire, what can make good carry ",3, "magic",
            1,1,1,2500,0,true);
    Cards barricade = new Cards("Barricade",
            "barricade can stop enemies",4, "barricade",
            1,3000,1,0,0,true);
}