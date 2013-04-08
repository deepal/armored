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
public class BrickWall{
    public int damageLevel;
    public String type;
    public Point location;
    
    public BrickWall(int x,int y){
        this.location.x=x;
        this.location.y=y;
        this.type="brick_wall";
        this.damageLevel=0;
    }
    
    public BrickWall(int x,int y,int damage){
        this.location.x=x;
        this.location.y=y;
        this.type="brick_wall";
        this.damageLevel=damage;
    }
}
