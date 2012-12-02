/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Networking.Receiver;
import java.util.Observer;

/**
 *
 * @author Deepal
 */

public class MapUpdater extends Thread{
    String message;
    Receiver receiver;
    public MapUpdater(Receiver receiver){
        this.receiver=receiver;
    }
    
    public void run(){
        while(true){
            updateMap();
        }
    }
    
    public void updateMap(){
        message=receiver.receivedMessage;
        
    }
}
