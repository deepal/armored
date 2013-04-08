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
public class Water{
    public String type;
    public Point location;
    public Water(int x,int y){
        this.location.x=x;
        this.location.y=y;
        this.type="water";
    }
}
