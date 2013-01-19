/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Point;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Deepal
 */
public class LifePack extends GameObject implements Runnable{
    public int healthBonus;
    public int lifeTime;
    
    public LifePack(int x,int y,int lifetime){
        super(x, y);
        this.lifeTime=lifetime;
        this.type="health_pack";
    }
    
    public void run(){
        while(true){
            if(this.lifeTime==0){
                System.out.println("Life Pack Disappeared!");
                break;
            }
            try {
                this.lifeTime--;
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LifePack.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
