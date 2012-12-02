/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

import java.awt.Point;

/**
 *
 * @author Deepal
 */
public class BrickWall extends GameObject{
    public int health;
    
    public BrickWall(int x,int y){
        super(x,y);
        this.type="brick_wall";
        this.health=100;
    }
}
