package com.example.finalproject.localgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;

public class WaitingActivity extends AppCompatActivity {
    Client client;
    Server server;


    /*@Override
    protected void onPostResume() {
        super.onPostResume();
        if ((boolean)getIntent().getSerializableExtra("ENEMY_NOT_FOUNDED")){
            Toast.makeText(this,"Host has not founded",Toast.LENGTH_LONG).show();
        }
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        Intent toFight= new Intent(this, OnlineFightActivity.class);

        Button searchLobby = findViewById(R.id.search_lobby);
        Button createLobby = findViewById(R.id.create_lobby);
        EditText enemiesIP = findViewById(R.id.enemy_port);
        TextView yourIP = findViewById(R.id.your_ip);


        searchLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toFight.putExtra("SERVER_IP", (Integer) Integer.parseInt(enemiesIP.getText().toString()));
                toFight.putExtra("HOST",false);
                startActivity(toFight);




            }
        });
        createLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toFight.putExtra("SERVER_IP", Integer.parseInt(enemiesIP.getText().toString()));
                toFight.putExtra("HOST",true);
                startActivity(toFight);

            }
        });









    }


}