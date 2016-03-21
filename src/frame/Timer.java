
package frame;
import static java.awt.Toolkit.getDefaultToolkit;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import main.Main;

/**
 *
 * @author ford.terrell
 */
public class Timer extends JFrame implements Runnable, WindowListener {
	private static final long serialVersionUID = 1L;
	
	private BufferStrategy bs;
	private final Window panel;
	private boolean running;

	public Timer(boolean ud) {
		Dimension screen = getDefaultToolkit().getScreenSize();
		setAutoRequestFocus(true);
		setPreferredSize(screen);
		setIgnoreRepaint(true);
		addWindowListener(this);
		setResizable(false);
		setUndecorated(ud);
		add(panel = new Window());
		pack();
		panel.init();
		panel.createBufferStrategy(3);
		bs = panel.getBufferStrategy();
		setVisible(true);
	}
	
	public void start() {
		running = true;
		run();
	}

	public void run() {
        long startTime = System.nanoTime();
        int frames_per_second = 0, ticks = 0, ticks_per_second = 0, seconds_passed = 0;
        final double ns = 1_000_000_000.0 / 60.0;
        //conversion from nanoseconds to 1/60 of a second
        while (running) {
            if ((System.nanoTime() - startTime) / 1_000_000_000 > seconds_passed) {
                seconds_passed++;
                //if a second has passed
                setTitle("Game | " + frames_per_second + " fps, " + ticks_per_second + " ups, "
                        + seconds_passed + (seconds_passed == 1 ? " second" : " seconds") + " running");
                frames_per_second = 0;
                ticks_per_second = 0;
            }
            //do game updates (60 per second)
            //if scheduled for one or multiple
            //updates, do game tick
            while ((System.nanoTime() - startTime) / ns > ticks) {
                update();
                //update the game
                ticks++;
                //one more tick recorded
                ticks_per_second++;
                //one more tick this second recorded
            }
            //if has updated game, render image
            render();
            frames_per_second++;
        }
    }
	
	public void render() {
		Graphics g = bs.getDrawGraphics();
		g.drawImage(Window.getImage(), 0, 0, null);
		panel.render();
		bs.show();
		g.dispose();
	}
	
	public void update() {
		panel.update();
		if(!panel.screen) {
			dispose();
			Main.start(!isUndecorated());
		}
	}
	
	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		running = false;
		System.exit(0);
	}
}
