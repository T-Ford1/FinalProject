package net;

import components.game.MessageBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author ford.terrell
 */
public class Network extends Thread {

    public static IOGate io;
    public static boolean connected;
    public static String name;
    public static int id;

    public Network() {
        try {
            tryConnect();
        } catch (Exception ex) {
            io = null;
            connected = false;
            System.err.println("connection refused");
        }
        if(connected) {
            System.out.println("User Data : " + name + ", " + id);
        }
    }

    public static final void tryConnect() throws Exception {
        try {
            Scanner keyb = new Scanner(new File("res/net/client.dat"));
            id = Integer.parseInt(keyb.nextLine());
            name = keyb.nextLine();
        } catch (FileNotFoundException ex) {
            id = -1;
            name = JOptionPane.showInputDialog("What is your name?", "Input name");
        }
        io = new IOGate();
        connected = true;
        io.output(id + "");
        io.output(name);
        if (id == -1) {
            id = Integer.parseInt(io.getInput());
        }
        try (PrintWriter pr = new PrintWriter("res/net/client.dat")) {
            pr.println(id);
            pr.print(name);
        }
    }

    public void run() {
        while (connected) {
            try {
                parseInput(io.getInput());
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                connected = false;
            }
        }
    }

    public static void parseInput(String in) {
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
