/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

/**
 *
 * @author Deepal
 */
import GameObjects.BrickWall;
import GameObjects.StoneWall;
import GameObjects.TankController;
import GameObjects.Water;
import Networking.Receiver;
import Networking.Sender;
import UI.Game;
import UI.MessageDecorder;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    public static TankController tc;

    public Controller() {

        Sender messageSender;
        messageSender = new Sender("localhost", 6000);
        tc = new TankController(messageSender);
        Receiver messageReceiver = new Receiver("localhost", 7000);
        messageReceiver.start();
        messageSender.sendMessage(Sender.JOIN);
        UI.Game.SENDER = messageSender;

        System.out.println(UI.Game.SENDER.toString());
        tc = new TankController(UI.Game.SENDER);
    }

    public void act() {

        int x = MessageDecorder.bots[MessageDecorder.myID].x;
        int y = MessageDecorder.bots[MessageDecorder.myID].y;
        int direc = MessageDecorder.bots[MessageDecorder.myID].direction;

        for (int i = 0; i < MessageDecorder.playerCount; i++) {
            if ((MessageDecorder.bots[i].x == x || MessageDecorder.bots[i].y == y) && (i!=MessageDecorder.myID)) {
                try {
                    Thread.sleep(1000);
                    tc.shoot();                   
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

        int xN = 0, yN = 0;

        switch (direc) {
            case 0:
                xN = x;
                yN = y - 1;
                break;
            case 1:
                xN = x + 1;
                yN = y;
                break;
            case 2:
                xN = x;
                yN = y + 1;
                break;
            case 3:
                xN = x - 1;
                yN = y;
                break;
        }
        if (!isBlocked(xN, yN)) {
            switch (direc) {
                case 0:
                    tc.moveUp();
                    break;
                case 1:
                    tc.moveRight();
                    break;
                case 2:
                    tc.moveDown();
                    break;
                case 3:
                    tc.moveLeft();
                    break;
            }

        } else {
            randomMove(MessageDecorder.bots[MessageDecorder.myID].direction);
        }
    }

    public void randomMove(int direc) {
        Random direction = new Random();
        int nextDirection = direction.nextInt(4);
        if (nextDirection == direc) {
            nextDirection = direction.nextInt(4);
        } else {
            switch (nextDirection) {
                case 0:
                    tc.moveUp();
                    break;
                case 1:
                    tc.moveRight();
                    break;
                case 2:
                    tc.moveDown();
                    break;
                case 3:
                    tc.moveLeft();
                    break;
                default:
                    break;
            }
        }
    }

    public boolean isBlocked(int x, int y) {
        for (int i = 0; i < MessageDecorder.playerCount; i++) {
            if ((Math.floor(x) == MessageDecorder.bots[i].x) && (Math.floor(y) == MessageDecorder.bots[i].y) && (i != MessageDecorder.myID) && (MessageDecorder.bots[i].health > 0)) {
                return true;
            }
        }
        for (int i = 0; i < MessageDecorder.bricks.size(); i++) {
            BrickWall br = MessageDecorder.bricks.get(i);
            if ((Math.floor(x) == br.location.x && Math.floor(y) == br.location.y) && (br.damageLevel != 4)) {
                return true;
            }
        }
        for (int i = 0; i < MessageDecorder.stoneWall.size(); i++) {
            StoneWall st = MessageDecorder.stoneWall.get(i);
            if (Math.floor(x) == st.location.x && Math.floor(y) == st.location.y) {
                return true;
            }
        }

        for (int i = 0; i < MessageDecorder.water.size(); i++) {
            Water w = MessageDecorder.water.get(i);
            if (Math.floor(x) == w.location.x && Math.floor(y) == w.location.y) {
                return true;
            }
        }

        if (x >= Game.GRID_PARAMETER || x < 0 || y >= Game.GRID_PARAMETER || y < 0) {
            return true;
        }

        return false;
    }
}
