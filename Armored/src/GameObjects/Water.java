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
        location=new Point(x, y);
        this.type="water";
    }
}
