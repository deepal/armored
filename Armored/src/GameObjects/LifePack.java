/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import UI.MessageDecorder;
import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Deepal
 */
public class LifePack extends Thread{
    public int healthBonus;
    public int lifetime;
    public String type;
    public Point location;
    public boolean visible;
    
    public LifePack(int x,int y,int lifetime){
        location=new Point(x, y);
        this.lifetime=lifetime;
        this.type="health_pack";
        this.visible=true;
        //this.start();
        
    }
    
    public void run(){
        while(true){
            for(int i=0;i< MessageDecorder.playerCount;i++){
                if(MessageDecorder.bots[i].x==this.location.x && MessageDecorder.bots[i].y==this.location.y && this.visible){
                    System.out.println("Lifepack "+this.getId()+" acquired by Bot-"+i);
                    this.visible=false;
                    this.interrupt();
                    break;
                }
            }
            if(this.visible==false){
                break;
            }
            if(this.lifetime>0){
                try{
                    this.sleep(1);
                    if(lifetime%1000==0){
                        System.out.println("Life pack "+this.getId()+" will be disappeared in "+this.lifetime/1000+" s");
                    }
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
        System.out.println("Life pack visibility = "+this.visible);
    }
    
    
    
}
