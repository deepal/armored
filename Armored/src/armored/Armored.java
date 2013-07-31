/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package armored;

import Game.ControlPanel;
import GameObjects.LifePack;
import Networking.Receiver;
import Networking.Sender;

/**
 *
 * @author Deepal
 */

public class Armored {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //(new Sender("localhost", 6000)).start();
        //(new Receiver("localhost", 7000)).start();
       // new ControlPanel();
        UI.Game game = new UI.Game();
        
    }
}
