package wumpus2.pkg2;

import java.util.logging.Level;
import java.util.logging.Logger;
import util.Ambiente;
import util.AG;

public class WUMPUS22 {

    public static Ambiente ambiente = new Ambiente();

    public static void main(String[] args) {
  
 
     
        try {
            ambiente.runAag(new AG().run());
        } catch (InterruptedException ex) {
            Logger.getLogger(WUMPUS22.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     
     

    }

}
