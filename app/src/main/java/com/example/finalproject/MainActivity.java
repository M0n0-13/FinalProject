package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static String UsersName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand=new Random();
        Intent toFight = new Intent(this, FightActivity.class);
        Intent toDeck = new Intent(this, DeckActivity.class);
        /*String name, String description, int cardID,String type, short phases,
                 double hp, short nowPhase, Bitmap cardArt, double strength,
                 double usualTimeChange*/
        /*Cards knight = new Cards("Knight",
                "Usual meelie fighter with sword!",1, "meelie",
                5,1000,3,300,75);*/
        //base cards:




        Button setName = findViewById(R.id.set_name);
        Button startFight=findViewById(R.id.start_fight);
        Button changeDeck=findViewById(R.id.change_deck);
        EditText inUsersName=findViewById(R.id.in_name);

        startFight.setEnabled(false);
        changeDeck.setEnabled(false);


        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inUsersName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Enter your nickname, please",Toast.LENGTH_SHORT).show();

                }else{
                    UsersName =  inUsersName.getText().toString();
                    /*changeDeck.setEnabled(true);*/
                    startFight.setEnabled(true);
                    setName.setVisibility(View.INVISIBLE);
                    setName.setEnabled(false);
                    inUsersName.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"Your nickname has been approved",Toast.LENGTH_SHORT).show();
                }

            }
        });
        startFight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toFight);


            }
        });
        changeDeck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toDeck);

            }
        });

        //TODO привязать к кнопкам начала поединка и изменения колоды  соответсвующие активности
        //TODO создать несколько тестовых карт, доработать Cards со стороны атаки и здоровья(может еще чего-нибудь)
        //TODO создать возможность игры против ботов


    }
}