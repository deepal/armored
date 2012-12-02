/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import GameObjects.Bot;
import GameObjects.BrickWall;
import GameObjects.StoneWall;
import GameObjects.Water;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Deepal
 */
public class MessageDecorder {
    
    public Location[] players;
    public Bot[] bots;
    public ArrayList<BrickWall> bricks;
    public ArrayList<Water> water;
    public ArrayList<StoneWall> stoneWall;
    int playerNo;
    public int playerCount;
    public String message;
    public MessageDecorder(String message){
        bots=new Bot[5];
        
        //test - define the bots[] array to test globalupdate() alone
            for(int k=0;k<5;k++){
                bots[k]=new Bot();
            }
        //test over 
        
        
        this.message=message;
        char messageType=message.charAt(0);        
        switch(messageType){
            case 'I': initiationMessage();break;
            case 'S': startMessage();break;
            case 'G': globalUpdate();break;
            case 'L': lifePackMessage();break;
            case 'C': coinMessage();break;
            default: System.out.println("Unknown Message!!");
        }    
    }
    
    public void initiationMessage(){
        System.out.println("Initiation message");
        int x,y;
        bricks=new ArrayList<BrickWall>();
        water=new ArrayList<Water>();
        stoneWall=new ArrayList<StoneWall>();
        
        String[] commands=message.split("#");
        String[] data=commands[0].split(":");
        String brickData,stoneData,waterData;
        brickData=data[2];
        stoneData=data[3];
        waterData=data[4];
        
        String[] brickCoordinates=brickData.split(";");        
        for(int i=0;i<brickCoordinates.length;i++){
            x=Integer.parseInt(brickCoordinates[i].split(",")[0]);
            y=Integer.parseInt(brickCoordinates[i].split(",")[1]);
            bricks.add(new BrickWall(x, y));
        }
        
        String[] stoneCoordinates=stoneData.split(";");
        for(int i=0;i<stoneCoordinates.length;i++){
            x=Integer.parseInt(stoneCoordinates[i].split(",")[0]);
            y=Integer.parseInt(stoneCoordinates[i].split(",")[1]);
            stoneWall.add(new StoneWall(x, y));
        }
        
        String[] waterCoordinates=waterData.split(";");
        for(int i=0;i<waterCoordinates.length;i++){
            x=Integer.parseInt(waterCoordinates[i].split(",")[0]);
            y=Integer.parseInt(waterCoordinates[i].split(",")[1]);
            water.add(new Water(x, y));
        }
        
//        //test----------------------------------------------------------------------
//        BrickWall bw;
//        StoneWall sw;
//        Water w;
//        System.out.println("Brick coordinates");
//        for(int i=0;i<bricks.size();i++){
//            bw=(BrickWall)bricks.get(i);
//            System.out.println(bw.x+","+bw.y);
//        }
//        System.out.println("Stone wall coordinates");
//        for(int i=0;i<stoneWall.size();i++){
//            sw=(StoneWall)stoneWall.get(i);
//            System.out.println(sw.x+","+sw.y);
//        }
//        System.out.println("Water coordinates");
//        for(int i=0;i<water.size();i++){
//            w=(Water)water.get(i);
//            System.out.println(w.x+","+w.y);
//        }
//        //test over-----------------------------------------------------------------
        
    }
    
    
    
    
    
    public void startMessage(){
        //System.out.println("Start message");
        players             = new Location[5];
        String[] commands   = message.split("#");
        String[] part       = commands[0].split(":");
        playerCount     = part.length-1;
        int playerNo,x,y,direc;
        String playerData,coordinate;
        
        for(int i=1;i<part.length;i++){
            playerData  = part[i];
            playerNo    = playerData.charAt(1)-48;
            coordinate  = playerData.split(";")[1];
            
            direc   =Integer.parseInt(playerData.split(";")[2]);
            x       =Integer.parseInt(coordinate.split(",")[0]);
            y       =Integer.parseInt(coordinate.split(",")[1]);
            players[i-1]=new Location(i-1, x, y, direc);
        }
        
//        //test-------------------------------------------------------------------
//        for(int i=0;i<playerCount;i++){
//            System.out.println("Player "+players[i].playerID);
//            System.out.println("Position : "+players[i].x+","+players[i].y);
//            System.out.println("Direction: "+players[i].direction);
//        }
//        //test over--------------------------------------------------------------
        
    }
    
    public void globalUpdate(){
        playerCount=5;
        System.out.println("Global Update");
        String[] dataset=message.split(":");
        String coordinate;
        int x,y,direction,shoots,health,coins,points;
        for(int i=1;i<=playerCount;i++){
            coordinate=dataset[i].split(";")[1];
            x=Integer.parseInt(coordinate.split(",")[0]);
            y=Integer.parseInt(coordinate.split(",")[1]);
            this.bots[i-1].x=x;
            this.bots[i-1].y=y;
            this.bots[i-1].direction=Integer.parseInt(dataset[i].split(";")[2]);
            this.bots[i-1].shoots=Integer.parseInt(dataset[i].split(";")[3]);
            this.bots[i-1].health=Integer.parseInt(dataset[i].split(";")[4]);
            this.bots[i-1].coins=Integer.parseInt(dataset[i].split(";")[5]);
            this.bots[i-1].points=Integer.parseInt(dataset[i].split(";")[6]);
        }
        System.out.println(playerCount);
        //test-------------------------------------------------------------------
        for(int i=0;i<playerCount;i++){
            System.out.println("Bot "+i);
            System.out.println("\tCoordinates : "+bots[i].x+","+bots[i].y);
            System.out.println("\tDirection   : "+bots[i].direction);
            System.out.println("\tShoots      : "+bots[i].shoots);
            System.out.println("\tHealth      : "+bots[i].health);
            System.out.println("\tCoins       : "+bots[i].coins);
            System.out.println("\tPoints      : "+bots[i].points);
        }
        //test over--------------------------------------------------------------
        
        
    }
    
    public void lifePackMessage(){
        System.out.println("Life pack message");
    }
    
    public void coinMessage(){
        System.out.println("Coin message");
    }
    
    //this main method is for testing purposes
    public static void main(String args[]){
        new MessageDecorder("G:P0;6,5;3;1;110;0;0:P1;1,7;3;0;100;936;936:P2;5,5;3;0;70;0;0:P3;9,7;1;0;100;0;0:P4;4,5;1;0;100;940;940:7,2,0;1,4,0;8,6,0;0,8,0;2,4,0;7,6,0;9,3,0;1,3,0;3,2,0#");
    }
}
