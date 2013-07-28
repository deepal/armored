/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import GameObjects.BrickWall;
import GameObjects.CoinPile;
import GameObjects.LifePack;
import Networking.Receiver;
import Networking.Sender;
import java.awt.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import UI.MessageDecorder;
import java.util.Iterator;
import sun.swing.UIAction;
import UI.Game;

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
        for (int i = 0; i < UI.Game.GRID_PARAMETER; i++) {
            for (int j = 0; j < UI.Game.GRID_PARAMETER; j++) {
                grassland.draw(i * Game.CELL_WIDTH, j * Game.CELL_WIDTH);
                //grphcs.drawString(i+","+j, i*48, j*48);
            }
        }
        updateTexture();
        
        
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
//        Input input = gc.getInput();
//
//        if (input.isKeyPressed(Input.KEY_UP)) {
//            moveUp();
//        }
//        if (input.isKeyPressed(Input.KEY_DOWN)) {
//            moveDown();
//        }
//        if (input.isKeyPressed(Input.KEY_LEFT)) {
//            moveLeft();
//        }
//        if (input.isKeyPressed(Input.KEY_RIGHT)) {
//            moveRight();
//        }
    }

//    public void moveUp() throws SlickException {
//        tank = new Image("res/p5-up.png");
//        yPos -= 48;
//    }
//
//    public void moveDown() throws SlickException {
//        tank = new Image("res/p5-down.png");
//        yPos += 48;
//    }
//
//    public void moveLeft() throws SlickException {
//        tank = new Image("res/p5-left.png");
//        xPos -= 48;
//    }
//
//    public void moveRight() throws SlickException {
//        tank = new Image("res/p5-right.png");
//        xPos += 48;
//    }

    public boolean isBlocked() {
        return true;
    }
    
    public void updateTexture() throws SlickException{
        drawBots();
        drawLand();
        drawTreasure();
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
            bot.draw(MessageDecorder.bots[i].x*Game.CELL_WIDTH,MessageDecorder.bots[i].y*Game.CELL_WIDTH);            
        }
    }
    
    public void drawLand() throws SlickException{
        Image brickwall = null, stonewall = null, water = null;
        BrickWall tempBrickWall = null;
        for(int i=0;i<MessageDecorder.bricks.size();i++){
            tempBrickWall = MessageDecorder.bricks.get(i);
            switch(tempBrickWall.damageLevel){
                case 0:
                    brickwall = new Image("res/brick.jpg");break;
                case 1:
                    brickwall = new Image("res/brick-d1.png");break;
                case 2:
                    brickwall = new Image("res/brick-d2.png");break;
                case 3:
                    brickwall = new Image("res/brick-d3.png");break;
                case 4:
                    brickwall = new Image("res/brick-d4.png");break;
                default:
                    System.err.println("This damage level not specified!!!!!!!!");
            }
            brickwall.draw(tempBrickWall.location.x*Game.CELL_WIDTH, tempBrickWall.location.y*Game.CELL_WIDTH);
        }
        
        for(int i=0;i<MessageDecorder.stoneWall.size();i++){
            stonewall = new Image("res/tree.png");
            stonewall.draw((MessageDecorder.stoneWall.get(i)).location.x*Game.CELL_WIDTH, (MessageDecorder.stoneWall.get(i)).location.y*Game.CELL_WIDTH);
        }
        
        for(int i=0;i<MessageDecorder.water.size();i++){
            water = new Image("res/water.png");
            water.draw((MessageDecorder.water.get(i)).location.x*Game.CELL_WIDTH, (MessageDecorder.water.get(i)).location.y*Game.CELL_WIDTH);
        }
        
    }
    
    public void drawTreasure() throws SlickException{
        Image coin = null, healthpack = null;
        for(int i=0;i<MessageDecorder.coins.size();i++){
            CoinPile cp = MessageDecorder.coins.get(i);
            if(cp.visible){
                coin = new Image("res/coins.png");
                coin.draw(cp.location.x*Game.CELL_WIDTH, MessageDecorder.coins.get(i).location.y*Game.CELL_WIDTH);
            }
            else{
                coin = new Image("res/brick-d4.png");
                coin.draw(cp.location.x*Game.CELL_WIDTH, MessageDecorder.coins.get(i).location.y*Game.CELL_WIDTH);
            }
        }
        
        for(int i=0;i<MessageDecorder.lifePacks.size();i++){
            LifePack lp= MessageDecorder.lifePacks.get(i);
            if(lp.visible){
                healthpack = new Image("res/healthpack.png");
                healthpack.draw(lp.location.x*Game.CELL_WIDTH, MessageDecorder.lifePacks.get(i).location.y*Game.CELL_WIDTH);
            }
            else{
                healthpack = new Image("res/brick-d4.png");
                healthpack.draw(lp.location.x*Game.CELL_WIDTH, MessageDecorder.lifePacks.get(i).location.y*Game.CELL_WIDTH);
            }
        }
        
    }

}
