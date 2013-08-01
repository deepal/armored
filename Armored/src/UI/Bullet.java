/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author Deepal
 */
public class Bullet {
    float x;
    float y;
    boolean blocked;
    int direction;
    public Bullet(){
        
    }
    public Bullet(int x, int y, int direc){
        this.x = x;
        this.y = y;
        blocked = false;
        this.direction = direc;
    }
}
