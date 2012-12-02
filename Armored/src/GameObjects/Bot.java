/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import UI.Location;

/**
 *
 * @author Deepal
 */
public class Bot extends Location{
    public int health;
    public int points;
    public int coins;
    public int shoots;
    public Bot(int x,int y,int direction){
        super(x, y,direction);
        this.health=100;
    }
    
    public Bot(int x,int y,int direction,int shoots,int health,int coins,int points){
        super(x, y, direction);
        this.shoots=shoots;
        this.health=health;
        this.coins=coins;
        this.points=points;
    }
    
    public Bot(){
        //nothing to see here
    }
}
