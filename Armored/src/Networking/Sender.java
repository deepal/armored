
package Networking;


import java.net.*;
import java.io.*;

public class Sender{
    public Socket clientSocket=null;
    private int port;
    private String host;
    private String message;
    //private BufferedReader in=null;
    private PrintWriter out=null;
    
    public static final String JOIN="JOIN#";
    public static final String MOVE_UP="UP#";
    public static final String MOVE_DOWN="DOWN#";
    public static final String MOVE_RIGHT="RIGHT#";
    public static final String MOVE_LEFT="LEFT#";
    public static final String SHOOT="SHOOT#";
    
    public Sender(String host,int port){
        this.host=host;
        this.port=port;
        //init(host, port);
    }
    
    public void init(String host,int port){
        
        try{
            clientSocket=new Socket(host,port);
        }catch(UnknownHostException uhex){
            System.out.println("Unknown host "+host+" !");
            uhex.printStackTrace();
        }
        catch(IOException iox){
            System.out.println("Could not create socket!");
            iox.printStackTrace();
        }
        try{
            //in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out=new PrintWriter(clientSocket.getOutputStream(),true);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
//    public void run(){
//        try{
//            sendMessage(JOIN_MESSAGE);
//            //in.close();
//            out.close();
//            clientSocket.close();
//        }catch(Exception ex){
//            System.out.println("Could not send the message to the server!");
//            ex.printStackTrace();
//        }
//        
//    }
    
    
    public void sendMessage(String message){
        init(host,port);
        try{
            out.write(message);
            out.flush();
            out.close();
            clientSocket.close();
        }catch(Exception ex){
            System.out.println("Could not send the message!!");
            ex.printStackTrace();
        }
        System.out.println("Message sent: "+message);
        
    }
    
//    public static void main(String args[]){
////        Sender sender=new Sender("localhost",6000);
////        sender.start();
//        
//    }
}
