/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import AI.Controller;
import GameObjects.Bot;
import GameObjects.BrickWall;
import GameObjects.CoinPile;
import GameObjects.LifePack;
import GameObjects.StoneWall;
import GameObjects.TankController;
import Networking.Receiver;
import Networking.Sender;
import java.awt.Point;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import UI.MessageDecorder;
import java.util.Iterator;
import sun.swing.UIAction;
import UI.Game;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opencl.INTELImmediateExecution;
import org.lwjgl.opengl.GL11;
import org.lwjgl.openal.AL;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;
import sun.misc.JavaxSecurityAuthKerberosAccess;

/**
 *
 * @author Deepal
 */
public class Play extends BasicGameState {

    Image tree, grassland, ground, brickwall, stonewall, bullet, smoke, tank, margin;
    String player, health, points, coins;
    float xPos = 0, yPos = 0;
    Animation moveUp, moveDown, moveRight, moveLeft, shoot;
    int[] duration = {};
    Point[] blockList;
    float animation = 0;
    ArrayList<Bullet> bullets;
    TankController tc;
    Animation water;
    int[][] scores;
    public static Controller ctrl;
    
    public Play(int state) {

        ctrl = new Controller();
        System.out.println(Game.SENDER.toString());
        bullets = new ArrayList<Bullet>();
        tc = Controller.tc;
        
        scores = new int[5][4];
    }

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        tree = new Image("res/tree" + Game.CELL_WIDTH + ".png");
        grassland = new Image("res/grass" + Game.CELL_WIDTH + ".png");
        ground = new Image("res/ground" + Game.CELL_WIDTH + ".jpg");
        //water = new Image("res/water1"+Game.CELL_WIDTH+".jpg");
        brickwall = new Image("res/brick" + Game.CELL_WIDTH + ".jpg");
        stonewall = new Image("res/rocks" + Game.CELL_WIDTH + ".png");
        tank = new Image("res/p5-up" + Game.CELL_WIDTH + ".png");
        margin = new Image("res/rocks" + Game.CELL_WIDTH + ".png");
        Image[] waters = {new Image("res/water-1" + Game.CELL_WIDTH + ".jpg"), new Image("res/water-2" + Game.CELL_WIDTH + ".jpg"), new Image("res/water-3" + Game.CELL_WIDTH + ".jpg")};//, new Image("res/water-4" + Game.CELL_WIDTH + ".jpg"), new Image("res/water-5" + Game.CELL_WIDTH + ".jpg")};
        int[] duration = {200, 200, 200};//, 10, 10};
        water = new Animation(waters, duration, true);


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {

//        grphcs.drawString(mouse, 50, 50);
        for (int i = 0; i <= UI.Game.GRID_PARAMETER; i++) {
            for (int j = 0; j < UI.Game.GRID_PARAMETER; j++) {
                grassland.draw(i * Game.CELL_WIDTH, j * Game.CELL_WIDTH);
                //grphcs.drawString(i+","+j, i*48, j*48);
                if (i == UI.Game.GRID_PARAMETER) {
                    margin.draw(i * Game.CELL_WIDTH, j * Game.CELL_WIDTH);
                }
            }
        }
        updateTexture();
        for(int i=0;i<MessageDecorder.playerCount;i++){
            scores[i][0] = i;
            scores[i][1] = MessageDecorder.bots[i].coins;
            scores[i][2] = MessageDecorder.bots[i].points;
            scores[i][3] = MessageDecorder.bots[i].health;
        }
        drawScores(grphcs);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input ip = gc.getInput();
        if (ip.isKeyPressed(Input.KEY_LEFT)) {
            tc.moveLeft();
        }
        if (ip.isKeyPressed(Input.KEY_RIGHT)) {
            tc.moveRight();
        }
        if (ip.isKeyPressed(Input.KEY_DOWN)) {
            tc.moveDown();
        }
        if (ip.isKeyPressed(Input.KEY_UP)) {
            tc.moveUp();
        }
        if (ip.isKeyPressed(Input.KEY_SPACE)) {
            tc.shoot();
        }

    }

    public void updateTexture() throws SlickException {
        drawBots();
        drawLand();
        drawTreasure();
        drawCannons();
        //testDrawBullet();
    }

    public void drawScores(Graphics g){
        g.drawString("Armored - Tank Game", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+20), 50);
        g.drawString("Player", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+20), 150);
        g.drawString("Coins", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+100), 150);
        g.drawString("Points", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+180), 150);
        g.drawString("Health", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+260), 150);
        g.drawString("_________________________________________________________________", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)), 160);
        int i;
        for(i=0;i<MessageDecorder.playerCount;i++){
            for(int j=0;j<4;j++){
                g.drawString(""+scores[i][j], (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+20+(80*j)), 200+(i*50));
            }
        }
        g.drawString("_________________________________________________________________", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)), 200+(i*55));
        g.drawString("Developed by :-", (Game.CELL_WIDTH*(Game.GRID_PARAMETER+1)+10), 240+(i*55));
        g.drawString("- Deepal Jayasekara", (Game.CELL_WIDTH*(Game.GRID_PARAMETER)+50), 280+(i*55));
        g.drawString("- Vishmi Money", (Game.CELL_WIDTH*(Game.GRID_PARAMETER)+50), 320+(i*55));
    }
    
    
    public void drawBots() throws SlickException {
        Image bot = null;
        for (int i = 0; i < MessageDecorder.playerCount; i++) {
            if (MessageDecorder.bots[i].health > 0) {
                switch (MessageDecorder.bots[i].direction) {
                    case 0:
                        bot = new Image("res/p" + (i + 1) + "-up" + Game.CELL_WIDTH + ".png");
                        break;
                    case 1:
                        bot = new Image("res/p" + (i + 1) + "-right" + Game.CELL_WIDTH + ".png");
                        break;
                    case 2:
                        bot = new Image("res/p" + (i + 1) + "-down" + Game.CELL_WIDTH + ".png");
                        break;
                    case 3:
                        bot = new Image("res/p" + (i + 1) + "-left" + Game.CELL_WIDTH + ".png");
                        break;
                    default:
                        System.err.println("What direction is that???");

                }
                bot.draw(MessageDecorder.bots[i].x * Game.CELL_WIDTH, MessageDecorder.bots[i].y * Game.CELL_WIDTH);
            }
        }
    }

    public void drawLand() throws SlickException {
        Image brickwall = null, stonewall = null, water = null;
        BrickWall tempBrickWall = null;
        for (int i = 0; i < MessageDecorder.bricks.size(); i++) {
            tempBrickWall = MessageDecorder.bricks.get(i);
            switch (tempBrickWall.damageLevel) {
                case 0:
                    brickwall = new Image("res/brick" + Game.CELL_WIDTH + ".jpg");
                    break;
                case 1:
                    brickwall = new Image("res/brick-d1" + Game.CELL_WIDTH + ".png");
                    break;
                case 2:
                    brickwall = new Image("res/brick-d2" + Game.CELL_WIDTH + ".png");
                    break;
                case 3:
                    brickwall = new Image("res/brick-d3" + Game.CELL_WIDTH + ".png");
                    break;
                case 4:
                    brickwall = new Image("res/brick-d4" + Game.CELL_WIDTH + ".png");
                    break;
                default:
                    System.err.println("This damage level not specified!!!!!!!!");
            }
            brickwall.draw(tempBrickWall.location.x * Game.CELL_WIDTH, tempBrickWall.location.y * Game.CELL_WIDTH);
        }

        for (int i = 0; i < MessageDecorder.stoneWall.size(); i++) {
            stonewall = new Image("res/tree" + Game.CELL_WIDTH + ".png");
            stonewall.draw((MessageDecorder.stoneWall.get(i)).location.x * Game.CELL_WIDTH, (MessageDecorder.stoneWall.get(i)).location.y * Game.CELL_WIDTH);
        }

        for (int i = 0; i < MessageDecorder.water.size(); i++) {
            //water = new Image("res/water1"+Game.CELL_WIDTH+".jpg");
            this.water.draw((MessageDecorder.water.get(i)).location.x * Game.CELL_WIDTH, (MessageDecorder.water.get(i)).location.y * Game.CELL_WIDTH);
        }

    }

    public void drawTreasure() throws SlickException {
        Image coin = null, healthpack = null;
        for (int i = 0; i < MessageDecorder.coins.size(); i++) {
            CoinPile cp = MessageDecorder.coins.get(i);
            if (cp.visible) {
                coin = new Image("res/coins" + Game.CELL_WIDTH + ".png");
                coin.draw(cp.location.x * Game.CELL_WIDTH, MessageDecorder.coins.get(i).location.y * Game.CELL_WIDTH);
            } else {
                coin = new Image("res/brick-d4" + Game.CELL_WIDTH + ".png");
                coin.draw(cp.location.x * Game.CELL_WIDTH, MessageDecorder.coins.get(i).location.y * Game.CELL_WIDTH);
            }
        }

        for (int i = 0; i < MessageDecorder.lifePacks.size(); i++) {
            LifePack lp = MessageDecorder.lifePacks.get(i);
            if (lp.visible) {
                healthpack = new Image("res/healthpack" + Game.CELL_WIDTH + ".png");
                healthpack.draw(lp.location.x * Game.CELL_WIDTH, MessageDecorder.lifePacks.get(i).location.y * Game.CELL_WIDTH);
            } else {
                healthpack = new Image("res/brick-d4" + Game.CELL_WIDTH + ".png");
                healthpack.draw(lp.location.x * Game.CELL_WIDTH, MessageDecorder.lifePacks.get(i).location.y * Game.CELL_WIDTH);
            }
        }

    }

    public void drawCannons() throws SlickException {
        Bot bot = null;
        for (int i = 0; i < MessageDecorder.playerCount; i++) {
            bot = MessageDecorder.bots[i];
            if (bot.shoots == 1) {
                Audio boom;
                try {
                    boom = AudioLoader.getAudio("WAV", ResourceLoader.getResource("res/cannonboom.wav").openStream());
                    boom.playAsSoundEffect(1.0f, 1.0f, false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                bullets.add(new Bullet(bot.x, bot.y, bot.direction));
                bot.shoots = 0;
            }
        }
        drawCannonAnimation();
    }

    public void drawCannonAnimation() throws SlickException {
        Image cannon = new Image("res/cannonball" + Game.CELL_WIDTH + ".png");
        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);

            if (!isBlocked(b.x, b.y)) {
                cannon.draw((b.x) * Game.CELL_WIDTH, (b.y) * Game.CELL_WIDTH);
            } else {
                //(new Image("res/brick-d4"+Game.CELL_WIDTH+".png")).draw((b.x)*Game.CELL_WIDTH, (b.y)*Game.CELL_WIDTH);
                bullets.set(i, new Bullet(100, 100, 0));
                b.blocked = true;
            }

            switch (b.direction) {
                case 0:
                    b.y -= 0.3;
                    break;
                case 1:
                    b.x += 0.3;
                    break;
                case 2:
                    b.y += 0.3;
                    break;
                case 3:
                    b.x -= 0.3;
                    break;
                default:
                    break;
            }
        }
    }

    public boolean isBlocked(float x, float y) {
        for (int i = 0; i < MessageDecorder.playerCount; i++) {
            if ((Math.floor(x) == MessageDecorder.bots[i].x) && (Math.floor(y) == MessageDecorder.bots[i].y) && (i != MessageDecorder.myID) && (MessageDecorder.bots[i].health>0)) {
                System.err.println("Bullet Blocked!!!!!!!!!!!");
                return true;
            }
        }
        for (int i = 0; i < MessageDecorder.bricks.size(); i++) {
            BrickWall br = MessageDecorder.bricks.get(i);
            if ((Math.floor(x) == br.location.x && Math.floor(y) == br.location.y) && (br.damageLevel != 4)) {
                System.err.println("Bullet Blocked!!!!!!!!!!!");
                return true;
            }
        }
        for (int i = 0; i < MessageDecorder.stoneWall.size(); i++) {
            StoneWall st = MessageDecorder.stoneWall.get(i);
            if (Math.floor(x) == st.location.x && Math.floor(y) == st.location.y) {
                System.err.println("Bullet Blocked!!!!!!!!!!!");
                return true;
            }
        }

        if (x > Game.GRID_PARAMETER || x < 0 || y > Game.GRID_PARAMETER || y < 0) {
            return true;
        }

        return false;
    }
}
