/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import UI.MessageDecorder;
import java.awt.Point;

/**
 *
 * @author Deepal
 */
public class CoinPile {//extends Thread{
    public int value;
    public String type;
    public Point location;
    public int lifetime;
    public boolean visible;
    
    public CoinPile(int x,int y,int lifetime,int value){
        location=new Point(x, y);
        this.type="coin_pile";
        this.lifetime=lifetime;
        if(this.lifetime<=0){
            this.visible = false;
        }
        else{
            this.visible = true;
        }
        this.value=value;
        //this.start();
    }
    
//    public void run(){
//        while(true){
//            for(int i=0;i< MessageDecorder.playerCount;i++){
//                if(MessageDecorder.bots[i].x==this.location.x && MessageDecorder.bots[i].y==this.location.y && this.visible){
//                    System.out.println("Coinpile "+this.getId()+" acquired by Bot-"+i);
//                    this.visible=false;
//                    this.interrupt();
//                    break;
//                }
//            }
//            if(this.visible==false){
//                break;
//            }
//            if(this.lifetime>0){
//               try{
//                    this.sleep(1);
//                    if(lifetime%1000==0){
//                        System.out.println("Coin pile at "+this.getId()+" will disappear in "+lifetime/1000+" s");
//                    }
//                    this.lifetime--;
//                }
//                catch(Exception ex){
//                    ex.printStackTrace();
//                } 
//            }
//            else{
//                this.visible=false;
//                this.interrupt();
//                
//                break;
//            }         
//        }
//        System.out.println("Coin pile visibility = "+this.visible);
//        
//    }
    
}
