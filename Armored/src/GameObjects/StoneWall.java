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
public class StoneWall{
    public Point location;
    public String type;
    public StoneWall(int x,int y){
        location=new Point(x, y);
        this.type="stone_wall";
    }
}
