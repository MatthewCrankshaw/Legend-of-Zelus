package game;

import game.graphics.Screen;
import game.graphics.ui.UserInterface;

import java.awt.event.*;
import java.util.Vector;

/**
 * Created by Matthew.c on 19/01/2017.
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    protected Vector<Key> keys;

    private int mouseX = 0;
    private int mouseY = 0;

    private UserInterface ui;

    public InputHandler(Screen screen) {
        keys = new Vector<>();
        screen.addKeyListener(this);
        screen.addMouseMotionListener(this);
        screen.addMouseListener(this);
    }

    public void registerKey(Key key) {
        this.keys.add(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        setKey(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int uiPressed = 0;
        if(ui != null) {
            uiPressed = ui.checkInputEvent(mouseX, mouseY);
        }

        if (uiPressed == 0) {
            setKey(e.getButton(), true);
        }else if(uiPressed == -1){
            setToggleKey(e.getButton());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        setKey(e.getButton(),false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private void setKey(int KeyCode, boolean isPressed) {
        for (Key key : this.keys) {
            key.set(KeyCode, isPressed);
        }
    }

    private void setToggleKey(int KeyCode) {
        for (Key key : this.keys) {
            key.toggle(KeyCode);
        }
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    public void setUi(UserInterface ui) {
        this.ui = ui;
    }

    public boolean isKeyPressed(int keyCode) {
        for (Key key : keys) {
            if (key.getKey() == keyCode) {
                return key.isPressed();
            }
        }
        return false;
    }
}

