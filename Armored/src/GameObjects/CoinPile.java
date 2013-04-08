/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Point;

/**
 *
 * @author Deepal
 */
public class CoinPile extends Thread{
    public int value;
    public String type;
    public Point location;
    public int lifetime;
    public boolean visible;
    
    public CoinPile(int x,int y,int lifetime){
        location=new Point(x, y);
        this.type="coin_pile";
        this.lifetime=lifetime;
        this.visible=true;
        this.start();
    }
    
    public void run(){
        while(true){
            if(this.lifetime>0){
               try{
                    Thread.sleep(1);
                    System.out.println("Coin pile will disappear in "+lifetime+" ms");
                    this.lifetime--;
                }
                catch(Exception ex){
                    ex.printStackTrace();
                } 
            }
            else{
                this.visible=false;
                this.interrupt();
                break;
            }         
        }
        System.out.println("Coin pile visibility = "+this.visible);
        
    }
    
    public static void main(String args[]){
        CoinPile cp=new CoinPile(1, 1, 11110);
    }
}
