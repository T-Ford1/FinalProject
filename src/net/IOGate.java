package net;

import java.io.*;
import java.net.Socket;

/**
 *
 * @author ford.terrell
 */
public class IOGate {

    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public IOGate() throws IOException {
        socket = new Socket(java.net.InetAddress.getLocalHost().getHostAddress(), 9001);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public String getInput() throws Exception {
        return in.readLine();
    }

    public void output(String output) {
        out.println(output);
    }
    
    public void close() {
        try {
            out.println("1 0");
            socket.close();
            in.close();
            out.close();
        } catch (IOException ex) {
        }
    }
}
