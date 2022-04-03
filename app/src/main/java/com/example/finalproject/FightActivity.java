package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.ierarchy.Cards;

import java.util.Random;

public class FightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        TextView nameOfEnemy=findViewById(R.id.nameOfEnemy);
        TextView hpOfEnemy = findViewById(R.id.hpOfEnemy);
        TextView nameOfPlayer = findViewById(R.id.nameOfPlayer);
        TextView hpOfPlayer = findViewById(R.id.hpOfPlayer);
        Random random = new Random();
        nameOfEnemy.setText("Bot"+random.nextInt(100000));
        int botsHP = 10000;
        int playersHP = 10000;
        hpOfEnemy.setText("Hp: "+botsHP);



        ImageView c1s_1e = findViewById(R.id.fight_1s_1e);
        ImageView c2s_1e = findViewById(R.id.fight_2s_1e);
        ImageView c3s_1e = findViewById(R.id.fight_3s_1e);
        ImageView c1s_2e = findViewById(R.id.fight_1s_2e);
        ImageView c2s_2e = findViewById(R.id.fight_2s_2e);
        ImageView c3s_2e = findViewById(R.id.fight_3s_2e);
        ImageView c1s_3e = findViewById(R.id.fight_1s_3e);
        ImageView c2s_3e = findViewById(R.id.fight_2s_3e);
        ImageView c3s_3e = findViewById(R.id.fight_3s_3e);
        ImageView c1s_4e = findViewById(R.id.fight_1s_4e);
        ImageView c2s_4e = findViewById(R.id.fight_2s_4e);
        ImageView c3s_4e = findViewById(R.id.fight_3s_4e);

        nameOfPlayer.setText(MainActivity.UsersName);
        hpOfPlayer.setText("Hp: "+playersHP);


    }
    void playerGetDMG(){

    }
    void botGetDMG(){

    }
}