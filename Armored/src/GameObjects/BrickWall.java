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
    public int damageLevel;
    
    public BrickWall(int x,int y){
        super(x,y);
        this.type="brick_wall";
        this.damageLevel=0;
    }
    
    public BrickWall(int x,int y,int damage){
        super(x, y);
        this.type="brick_wall";
        this.damageLevel=damage;
    }
}
