/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

/**
 *
 * @author Deepal
 */
public class Node {
    
    public static final int WHITE = 0;
    public static final int GREY =1;
    public static final int BLACK =2;
    public static final int INF = 999;
    
    char val;
    int color;
    int d;
    public Node pre;
    
    public Node(){
        
    }
    public Node(char obj, int color, int d, Node pre){
        this.val = obj;
        this.color = color;
        this.d = d;
        this.pre = pre;
    }
}
