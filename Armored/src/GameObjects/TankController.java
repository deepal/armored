/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

/**
 *
 * @author Deepal
 */
import Networking.*;

public class TankController{
    Sender sender;
    public static int playerNo;
    
    public TankController(Sender messageSender){
        this.sender=messageSender;
    }
    
    
    public void moveUp(){
       this.sender.sendMessage(Sender.MOVE_UP);
    }
    
    public void moveDown(){
        this.sender.sendMessage(Sender.MOVE_DOWN);
    }
    
    public void moveRight(){
        this.sender.sendMessage(Sender.MOVE_RIGHT);
    }
    
    public void moveLeft(){
        this.sender.sendMessage(Sender.MOVE_LEFT);
    }
    
    public void shoot(){
        this.sender.sendMessage(Sender.SHOOT);
    }
    
}
