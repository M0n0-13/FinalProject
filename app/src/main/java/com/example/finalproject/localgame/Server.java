package com.example.finalproject.localgame;

import com.example.finalproject.Cards;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread{
    Cards cardsField[][];
    private Socket socketClient;
    private ServerSocket socketServer;
    private BufferedReader in;
    private BufferedWriter out;
    public boolean host = false;


    public Server (Socket socket) throws IOException{
        this.socketClient = socket;
        socketClient = socketServer.accept();
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        host = true;
    }
    void send(String msg){
        try {
            out.write(msg);
            out.flush();

        }catch (IOException e){

        }
    }




}
