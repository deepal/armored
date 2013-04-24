package Networking;

import Game.ControlPanel;
import UI.MessageDecorder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Receiver extends Thread{
    ServerSocket serverSocket;
    Socket clientSocket;
    Socket xnaSocket;
    PrintWriter xnaOut;
    BufferedReader in;
    MessageDecorder msgDec;
    public String receivedMessage;
    int port;
    String host;
    public Receiver(String host,int port){
        this.port=port;
        this.host=host;
        this.msgDec = new MessageDecorder();
    }
    public void run(){
        while(true){
            try{
                serverSocket=new ServerSocket(7000);
                clientSocket=serverSocket.accept();
                //xnaSocket=new Socket("localhost", 8000);
                //clientSocket=new Socket(host, port);
                in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //xnaOut=new PrintWriter(xnaSocket.getOutputStream(),true);
            }
            catch(Exception ex){
                System.out.println("Connection Failure...");
                ex.printStackTrace();
            }
            try{
                receivedMessage=in.readLine();
                if(receivedMessage!=null){
                    System.out.println(receivedMessage);
                    msgDec.processMessage(receivedMessage);
                    //xnaOut.write(receivedMessage);
                    //xnaOut.flush();
                }
                //xnaOut.close();
                //xnaSocket.close();
                serverSocket.close();
                in.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }  
        }            
    }  
}