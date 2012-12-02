/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

/**
 *
 * @author Deepal
 */
public class XNALauncher {
    public XNALauncher(){
        try{
            Runtime rt=Runtime.getRuntime();
            Process p=rt.exec("TankDemo.exe"); //Program.exe should be replaced by the name of GUI program           
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public static void main(String args[]){
        new XNALauncher();
    }
}
