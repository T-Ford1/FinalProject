package frame;

import components.game.Background;
import components.game.MenuBar;
import components.title.Title;
import components.game.ToolBar;
import java.awt.Color;
import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 *
 * @author ford.terrell
 */
public class Timer extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private static BufferStrategy bs;
    private static Window panel;
    private static boolean running;
    
    private static final double ns = 1_000_000_000.0 / 60.0;
    private static long ticks, renders;

    public Timer() {
        Dimension screen = getDefaultToolkit().getScreenSize();
        setAutoRequestFocus(true);
        setPreferredSize(screen);
        setIgnoreRepaint(true);
        setResizable(false);
        setUndecorated(true);
        add(panel = new Window());
        pack();
        panel.init();
        panel.createBufferStrategy(3);
        bs = panel.getBufferStrategy();
        runTitle();
    }

    public void start() {
        running = true;
        setVisible(true);
        run();
    }

    public void run() {
        long startTime = System.nanoTime();
        //conversion from nanoseconds to 1/60 of a second
        
        while (running) {
            //do game updates (60 per second)
            //if scheduled for one or multiple
            //updates, do game tick
            while ((System.nanoTime() - startTime) / ns > ticks) {
                update();
                //update the game
                ticks++;
                //one more tick recorded
            }
            //if has updated game, render image
            renders++;
            render();
        }
    }

    public void render() {
        Graphics g = bs.getDrawGraphics();
        g.drawImage(Window.getImage(), 0, 0, null);
        panel.renderAll();
        bs.show();
        g.dispose();
    }

    public void update() {
        panel.update();
    }
    
    public static void runTitle() {
        Window.removeAll();
        new Title(panel.getSize());
    }
    
    public static void runGame() {
        Window.removeAll();
        new Background(panel.getSize(), components.game.Type.SHIFTING);
        new MenuBar(panel.getSize());
        new ToolBar(panel.getSize());
    }
    
    public static void close() {
        double ratio = (double) renders / (double) ticks;
        System.out.println("Average FPS: " + (int) (60 * ratio));
        running = false;
        System.exit(0);
    }
}
