
package main;

import frame.Timer;

/**
 *
 * @author ford.terrell
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        start(false);
    }
    
    public static void start(boolean ud) {
    	new Timer(ud).start();
    }
}
