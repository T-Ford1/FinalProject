package net;

import components.game.MessageBar;
import java.io.IOException;

/**
 *
 * @author ford.terrell
 */
public class Network extends Thread {

    public static IOGate io;
    private static boolean connected;

    public Network() {
        tryConnect();
    }

    public static final void tryConnect() {
        try {
            io = new IOGate();
            connected = true;
        } catch (IOException ex) {
            connected = false;
        }
    }

    public void run() {
        while (connected) {
            try {
                parseInput(io.getInput());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void parseInput(String in) {
        io.output("0 got input");
        if (in.startsWith("0")) {
            MessageBar.displayMessage(in.substring(2));
        } else if (in.startsWith("1")) {
            parseCommand(in.split(" "));
        }
    }

    private static void parseCommand(String[] cmd) {
        switch (cmd[1]) {
            case "0":
                close();
                break;
        }
    }

    public static void close() {
        if (connected) {
            io.close();
        }
    }
}
