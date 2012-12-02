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
public class CoinPile extends GameObject{
    public int value;
    
    public CoinPile(int x,int y){
        super(x, y);
        this.type="coin_pile";
    }
}
