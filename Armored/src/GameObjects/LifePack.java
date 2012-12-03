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
public class LifePack extends GameObject{
    public int healthBonus;
    
    public LifePack(int x,int y){
        super(x, y);
        this.type="health_pack";
    }
}
