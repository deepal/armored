/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import AI.Map;
import GameObjects.Bot;
import GameObjects.BrickWall;
import GameObjects.CoinPile;
import GameObjects.LifePack;
import GameObjects.StoneWall;
import GameObjects.Water;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Deepal
 */
public class MessageDecorder {
    
    public Location[] players;
    public static Bot[] bots;
    public static ArrayList<BrickWall> bricks;
    public static ArrayList<Water> water;
    public static ArrayList<StoneWall> stoneWall;
    public static ArrayList<CoinPile> coins;
    public static ArrayList<LifePack> lifePacks;
    int playerNo;
    public static int playerCount;
    public String message;
    
    public MessageDecorder(){
        bots=new Bot[5];
        for(int k=0;k<5;k++){
            bots[k]=new Bot();
        }
        bricks=new ArrayList<BrickWall>();
        water= new ArrayList<Water>();
        stoneWall =new ArrayList<StoneWall>();
        coins = new ArrayList<CoinPile>();
        lifePacks = new ArrayList<LifePack>();
    }
    
    public void processMessage(String message){
        this.message=message;
        char messageType=message.charAt(0);
        
        if(message.equals("PITFALL#")){
            System.err.println("Game Over!!!!!");
            System.exit(0);
        }
        else if(message.equals("GAME_NOT_STARTED_YET#")){
            System.out.println("Please wait! Game is about to start...");
        }
        else if(message.equals("GAME_ALREADY_STARTED#")){
            System.out.println("You cannot join the game once it is started!");
        }
        else if(message.equals("TOO_QUICK#")){
            
        }
        else{
            switch(messageType){
                case 'I': initiationMessage();break;
                case 'S': startMessage();break;
                case 'G': globalUpdate();break;
                case 'L': lifePackMessage();break;
                case 'C': coinMessage();break;
                default: System.out.println("Unknown Message!!");
            }
        }
    }
    
    
    public void gameLostMessage(){
        System.err.println("Game Over!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
    
    public void initiationMessage(){
        System.err.println("[*] Initiation Message");
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
        System.err.println("[*] Start Message");
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
        
        System.err.println("[*] Global Update");
        playerCount=0;
        for(int i=0;i<message.length();i++){
            if(message.charAt(i)=='P'){
                playerCount++;
            }
        }
        String[] dataset=message.split(":");      
        String[] brickset;        
        String coordinate;
        int x,y,damageLevel,direction,shoots,health,coins,points;
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
        
        dataset[playerCount+1]=dataset[playerCount+1].split("#")[0];
        brickset=dataset[playerCount+1].split(";");
        bricks.clear();
        for(int i=0;i<brickset.length;i++){
            x=Integer.parseInt(brickset[i].split(",")[0]);
            y=Integer.parseInt(brickset[i].split(",")[1]);
            damageLevel=Integer.parseInt(brickset[i].split(",")[2]);         
            bricks.add(new BrickWall(x, y,damageLevel));          
        }
        
        //test-------------------------------------------------------------------
        for(int i=0;i<bricks.size();i++){
            System.out.println("Brick "+(i));
            System.out.println("\tx = "+bricks.get(i).location.x);
            System.out.println("\ty = "+bricks.get(i).location.y);
            System.out.println("\tdamage level = "+bricks.get(i).damageLevel);                   
        }
        //test over--------------------------------------------------------------
        
        
        
//        //test-------------------------------------------------------------------
//        System.out.println(playerCount);
//        for(int i=0;i<playerCount;i++){
//            System.out.println("Bot "+i);
//            System.out.println("\tCoordinates : "+bots[i].x+","+bots[i].y);
//            System.out.println("\tDirection   : "+bots[i].direction);
//            System.out.println("\tShoots      : "+bots[i].shoots);
//            System.out.println("\tHealth      : "+bots[i].health);
//            System.out.println("\tCoins       : "+bots[i].coins);
//            System.out.println("\tPoints      : "+bots[i].points);
//        }
//        //test over--------------------------------------------------------------
        
        
    }
    
    public void lifePackMessage(){
        System.err.println("[*] Life Pack update");
        String msg=this.message.split("#")[0];
        int x,y,lifeTime;
        x=Integer.parseInt(((msg.split(":")[1]).split(",")[0]));
        y=Integer.parseInt(((msg.split(":")[1]).split(",")[1]));
        lifeTime=Integer.parseInt(msg.split(":")[2]);  
        lifePacks.add(new LifePack(x, y, lifeTime));
    }
    
    public void coinMessage(){
        System.err.println("[*] Coin pile update");
        String msg=this.message.split("#")[0];
        int x,y,lifeTime,value;
        x=Integer.parseInt(((msg.split(":")[1]).split(",")[0]));
        y=Integer.parseInt(((msg.split(":")[1]).split(",")[1]));
        lifeTime=Integer.parseInt(msg.split(":")[2]);  
        value=Integer.parseInt(msg.split(":")[3]);
        coins.add(new CoinPile(x, y, lifeTime, value));
    }
    
    //this main method is for testing purposes
    public static void main(String args[]){
        new MessageDecorder().processMessage("G:P0;0,0;0;0;100;0;0:5,8,0;7,8,0;2,6,0;6,8,0;8,6,0#");
    }
}
