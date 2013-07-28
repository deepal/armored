/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Networking.Receiver;
import Networking.Sender;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import UI.Game;

public class Menu extends BasicGameState {

    Image joingame, exitgame,background;
    

    public Menu(int state) {
        


    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        joingame = new Image("res/joinbutton.png");
        exitgame = new Image("res/exitbutton.png");
        background = new Image("res/background.jpg");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        background.draw(0, 0);
        joingame.draw(100, 380);
        exitgame.draw(300, 380);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        int x = Mouse.getX();
        int y = Mouse.getY();
        if((x>300 && x< 400) && (y>0 && y<100)){
            if(input.isMousePressed(0)){
                System.exit(0);
            }
        }
        
        if((x>100 && x< 200) && (y>0 && y<100)){
            if(input.isMousePressed(0)){
                Sender messageSender;
                messageSender=new Sender("localhost", 6000);
                Receiver messageReceiver=new Receiver("localhost", 7000);
                messageReceiver.start();
                messageSender.sendMessage(Sender.JOIN);
                sbg.enterState(Game.play);
            }
        }

    }

    
}
