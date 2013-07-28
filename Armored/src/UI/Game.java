/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Deepal
 */
public class Game extends StateBasedGame{
    
    public static final String GAME_NAME = "Armored";
    public static final int menu = 0;
    public static final int play = 1;
    public static int WINDOW_HEIGHT = 480;
    public static int WINDOW_WIDTH = 480;
    
    public Game(){
        super(GAME_NAME);
        this.addState(new Menu(menu));
        this.addState(new Play(play));
    }
    
    public static void main(String args[]){
        AppGameContainer appgc;
        try{            
            appgc = new AppGameContainer(new Game());
            appgc.setDisplayMode(WINDOW_WIDTH, WINDOW_HEIGHT, false);
            appgc.start();
        }
        catch(SlickException ex){
            ex.printStackTrace();
        }        
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        this.enterState(menu);
    }

}