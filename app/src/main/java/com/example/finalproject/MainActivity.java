package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finalproject.localgame.WaitingActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public static String UsersName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand=new Random();
        Intent toFight = new Intent(this, FightWithBotActivity.class);
        Intent toTips = new Intent(this, TipsActivity.class);
        Intent toWaiting = new Intent(this, WaitingActivity.class);
        /*String name, String description, int cardID,String type, short phases,
                 double hp, short nowPhase, Bitmap cardArt, double strength,
                 double usualTimeChange*/
        /*Cards knight = new Cards("Knight",
                "Usual meelie fighter with sword!",1, "meelie",
                5,1000,3,300,75);*/
        //base cards:





        Button startFightOnline = findViewById(R.id.start_fight_online);
        Button setName = findViewById(R.id.set_name);
        Button startFight=findViewById(R.id.start_fight);

        EditText inUsersName=findViewById(R.id.in_name);


        startFight.setEnabled(false);



        setName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inUsersName.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(),"Enter your nickname, please",Toast.LENGTH_SHORT).show();

                }else{
                    UsersName =  inUsersName.getText().toString();

                    startFight.setEnabled(true);
                    setName.setVisibility(View.INVISIBLE);
                    setName.setEnabled(false);
                    inUsersName.setEnabled(false);
                    toWaiting.putExtra("YOUR_NAME",UsersName);
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
        startFightOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toWaiting);
            }
        });








    }

}