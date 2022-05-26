package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndOfTheGameActivity extends AppCompatActivity {
    boolean youWin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_end_of_the_game);
        Button backToMenu = findViewById(R.id.back_to_menu);
        Intent toMenu=new Intent(this,MainActivity.class);
        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(toMenu);
            }
        });
        TextView EndMessage = findViewById(R.id.new_about_win);
        if(FightWithBotActivity.playersHP>=0){
            EndMessage.setText("You win!");

        }else{
            EndMessage.setText("You lose!");

        }
    }
}