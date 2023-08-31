package model.view;

import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

public class LimitedPasswordField extends JPasswordField  {

	private static final int MAX_DIGITS = 6;

	 @Override
	    protected void processKeyEvent(KeyEvent e) {
	        char typedChar = e.getKeyChar();
	        int keyCode = e.getKeyCode();

	        if (Character.isDigit(typedChar) && getPassword().length < MAX_DIGITS) {
	            super.processKeyEvent(e);
	        } else if (keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE) {
	            super.processKeyEvent(e);
	        }
	 }
}
