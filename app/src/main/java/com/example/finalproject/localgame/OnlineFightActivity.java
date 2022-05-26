package com.example.finalproject.localgame;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Cards;
import com.example.finalproject.EndOfTheGameActivity;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.example.finalproject.localgame.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class OnlineFightActivity extends AppCompatActivity {


    public static int ip;


    public int chosenColumn = -1;

    public static int enemyHP = 4000;
    public static int yourHP = 4000;

    String enemyName;

    static BufferedReader in;
    static BufferedWriter out;
    static ServerSocket serverSocket;
    static Socket clientSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);
        
        //getIntent().getSerializableExtra("SERVER_IP");
        Client client= new Client();
        String yourName = (String) getIntent().getSerializableExtra("YOUR_NAME");


        Intent toWait = new Intent(this, WaitingActivity.class);
        Intent toEnd = new Intent(this, EndOfTheGameActivity.class);

        boolean host;
        host = (boolean) getIntent().getSerializableExtra("HOST");
        ip = (Integer) getIntent().getSerializableExtra("SERVER_IP");

        //TODO Сделать связь
        if (host){
            try {
                try{
                    serverSocket = new ServerSocket(ip);
                    clientSocket =  serverSocket.accept();
                    try {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        out.write(yourName);
                        enemyName = in.readLine();

                    }finally {
                        clientSocket.close();
                        in.close();
                        out.close();
                    }
                }finally {
                    if(serverSocket!=null)
                        serverSocket.close();



                }


            } catch (IOException e) {

            }


        }else{

            try {
                try {
                    // адрес - локальный хост, порт - 4004, такой же как у сервера
                    Thread thread = new MyThread();
                    thread.start();
                     // этой строкой мы запрашиваем
                    //  у сервера доступ на соединение

                    // читать соообщения с сервера
                    if(clientSocket!=null){
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    }



                    // если соединение произошло и потоки успешно созданы - мы можем
                    //  работать дальше и предложить клиенту что то ввести
                    // если нет - вылетит исключение
                     // ждём пока клиент что-нибудь
                    // не напишет в консоль

                    if(out!=null){
                        out.write(yourName); // отправляем сообщение на сервер
                        out.flush();
                    }
                    if(in!=null){
                        enemyName = in.readLine();
                    }
                     // ждём, что скажет сервер

                } finally { // в любом случае необходимо закрыть сокет и потоки

                    if(clientSocket!= null)
                        clientSocket.close();

                    if(in!= null)
                        in.close();
                    if(out!=null)
                        out.close();

                }
            } catch (IOException e) {

            }
        }


        enemyHP = 4000;
        yourHP = 4000;
        TextView nameOfEnemy=findViewById(R.id.nameOfEnemy);
        TextView hpOfEnemy = findViewById(R.id.hpOfEnemy);
        TextView nameOfPlayer = findViewById(R.id.nameOfPlayer);
        TextView hpOfPlayer = findViewById(R.id.hpOfPlayer);
        Button stopMove = findViewById(R.id.transmitMove);
        Random random = new Random();



        nameOfEnemy.setText(enemyName);
        nameOfPlayer.setText(yourName);


        hpOfEnemy.setText("Hp: "+enemyHP);
        hpOfPlayer.setText("Hp:"+yourHP);
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
        TextView EndMessage=findViewById(R.id.new_about_win);

        Cards cardsField[][]= new Cards[3][4];









    }
    public static class MyThread extends Thread{
        int ip = OnlineFightActivity.ip;

        @Override
        public void run() {
            try {
                clientSocket = new Socket("localhost", ip);
            } catch (IOException e) {

            }
        }
    }
}
