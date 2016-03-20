package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ford.terrell
 */
public class Keyboard implements KeyListener {

	private final boolean[] keys;

	private String pressed;

	public Keyboard() {
		pressed = "";
		keys = new boolean[125];
	}

	public void update() {
		if (pressed.length() > 0) {
			pressed = pressed.substring(1);
			pressed = "";
			for(int i = 0; i < 125; i++) {
				keys[i] = false;
			}
		}
	}

	public void keyPressed(KeyEvent ke) {
	}

	public void keyReleased(KeyEvent ke) {
		if (ke.getKeyCode() < 125) {
			keys[ke.getKeyCode()] = true;
			pressed += " ";
		}
	}

	public void keyTyped(KeyEvent ke) {
	}

	public boolean isPressed(int keyCode) {
		return keyCode < keys.length && keyCode > -1 && keys[keyCode];
	}
}
