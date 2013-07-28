/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import GameObjects.BrickWall;
import Networking.Receiver;
import Networking.Sender;
import java.awt.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import UI.MessageDecorder;
import java.util.Iterator;


/**
 *
 * @author Deepal
 */
public class Play extends BasicGameState {

    Image tree, grassland, ground, water, brickwall, stonewall, bullet, smoke, tank;
    String mouse;
    int xPos, yPos;
    Animation moveUp, moveDown, moveRight, moveLeft, shoot;
    int[] duration = {};
    Point[] blockList;

    public Play(int state) {
        xPos = 100;
        yPos = 100;
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        tree = new Image("res/tree.png");
        grassland = new Image("res/grass.png");
        ground = new Image("res/ground.jpg");
        water = new Image("res/water1.jpg");
        brickwall = new Image("res/brick.jpg");
        stonewall = new Image("res/rocks.png");
        tank = new Image("res/p5-up.png");

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

//        grphcs.drawString(mouse, 50, 50);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grassland.draw(i * 48, j * 48);
                //grphcs.drawString(i+","+j, i*48, j*48);
            }
        }
//        tree.draw(5 * 48, 7 * 48);
//        tree.draw(0, 0);
//        tree.draw(3 * 48, 1 * 48);
//        tree.draw(0, 1 * 48);
//        tree.draw(8 * 48, 9 * 48);
//        tree.draw(6 * 48, 3 * 48);
//        tree.draw(6 * 48, 7 * 48);
//        water.draw(5 * 48, 5 * 48);
//        grphcs.drawImage(tank, xPos, yPos);
        updateTexture();
        
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();

        if (input.isKeyPressed(Input.KEY_UP)) {
            moveUp();
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            moveDown();
        }
        if (input.isKeyPressed(Input.KEY_LEFT)) {
            moveLeft();
        }
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            moveRight();
        }
    }

    public void moveUp() throws SlickException {
        tank = new Image("res/p5-up.png");
        yPos -= 48;
    }

    public void moveDown() throws SlickException {
        tank = new Image("res/p5-down.png");
        yPos += 48;
    }

    public void moveLeft() throws SlickException {
        tank = new Image("res/p5-left.png");
        xPos -= 48;
    }

    public void moveRight() throws SlickException {
        tank = new Image("res/p5-right.png");
        xPos += 48;
    }

    public boolean isBlocked() {
        return true;
    }
    
    public void updateTexture() throws SlickException{
        drawBots();
    }
    
    public void drawBots() throws SlickException{  
        Image bot=null;
        for(int i=0;i<MessageDecorder.playerCount;i++){
            switch(MessageDecorder.bots[i].direction){
                case 0:
                    bot = new Image("res/p"+(i+1)+"-up.png");
                    break;
                case 1:
                    bot = new Image("res/p"+(i+1)+"-right.png");
                    break;
                case 2:
                    bot = new Image("res/p"+(i+1)+"-down.png");
                    break;
                case 3:
                    bot = new Image("res/p"+(i+1)+"-left.png");
                    break;
                default:
                    System.err.println("What direction is that???");
                    
            }
            bot.draw(MessageDecorder.bots[i].x*48,MessageDecorder.bots[i].y*48);            
        }
    }
    
    public void drawLand() throws SlickException{
        Image brickwall = null;
        for(int i=0;i<MessageDecorder.bricks.size();i++){
            switch(MessageDecorder.bricks.get(i).damageLevel){
                case 0:
                    brickwall = new Image("");
            }
        }
    }
    
    public void drawTreasure(Graphics g) throws SlickException{
        
    }
    
    public void drawHealthPacks(Graphics g) throws SlickException{
        
    }
}
