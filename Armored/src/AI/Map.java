/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AI;

/**
 *
 * @author Deepal
 */
public class Map extends Thread {
    public char[][] mapLayout;
    
    public Map(){
        mapLayout=new char[10][10];
    }
    
    //this method is for testing purpose
    public void testInput(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(i==j){
                    mapLayout[i][j]='W';
                }
                else{
                    mapLayout[i][j]='O';
                }
            }
        }
    }
    //this method is for testing purpose
    
    public void drawMap(){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print(mapLayout[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    public void updateMap(){
        
    }
    
    public void showLayout(){
        
    }
    
    public void run(){
        while(true){
            
        }
    }
 
    //this main method is for testing purpose
    public static void main(String args[]){
        Map mp=new Map();

    }
    //this main method is for testing purpose
}
