/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;
import UI.MessageDecorder;
import GameObjects.*;
import java.util.ArrayList;

/**
 *
 * @author Deepal
 */
public class Map extends Thread {
    public char[][] mapLayout;
    public char k='A';
    public Bot[] bots;
    public ArrayList<BrickWall> bricks;
    public ArrayList<CoinPile> coins;
    public ArrayList<LifePack> lifePacks;
    public ArrayList<StoneWall> stonewall;
    public ArrayList<Water> water;
    
    
            
            
    public Map(MessageDecorder msgDec){
        mapLayout=new char[10][10];
        this.bots=msgDec.bots;
        this.bricks=msgDec.bricks;
        this.coins=msgDec.coins;
        this.lifePacks=msgDec.lifePacks;
        this.stonewall=msgDec.stoneWall;
        this.water=msgDec.water;
        
    }
    
    //this method is for testing purpose
    public void testInput(){
        
    }
    //this method is for testing purpose
    
    public void drawMap(){
        
    }
    
    public void updateMap(){
        
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(i==j){
                    mapLayout[i][j]=k;
                }
                else{
                    mapLayout[i][j]=(char)(k+1);
                }
            }
        }
        k++;
    }
    
    public void showLayout(){
//        for(int i=0;i<10;i++){
//            for(int j=0;j<10;j++){
//                System.out.print(mapLayout[i][j]+" ");
//            }
//            System.out.println("");
//        }
//        System.out.println("");
        
        System.out.println("Stone wall count : "+ this.stonewall.size());
        System.out.println("Water count : "+this.water.size());
        
    }
    
    public void run(){
//        while(true){
//            showLayout();
//            try{
//                Thread.sleep(1000);
//            }
//            catch(Exception ex){
//                ex.printStackTrace();                        
//            }
//        }
        this.showLayout();
    }
 
    //this main method is for testing purpose
    public static void main(String args[]){
        Map mp=new Map(null);
        mp.start();
    }
    //this main method is for testing purpose
}
