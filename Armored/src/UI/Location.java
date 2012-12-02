/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.Point;

/**
 *
 * @author Deepal
 */
public class Location extends Point{
    public int direction;
    public int z;
    public int playerID;
    public Location(int x,int y,int direction){
        super(x,y);
        this.direction=direction;     
        z=direction;
    }
    
    public Location(int id, int x, int y, int direction){
        super(x,y);
        this.playerID=id;
        this.direction=direction;
    }
    
    public Location(){
        //nothing to see here
    }
    
}
