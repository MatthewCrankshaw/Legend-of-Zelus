package game;

import game.Game;

import java.awt.*;
import java.awt.event.*;

/**
 * Created by Matthew.c on 19/01/2017.
 */
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    public Key up = new Key();
    public Key down = new Key();
    public Key left = new Key();
    public Key right = new Key();
    public Key space = new Key();
    public Key e_teleport = new Key();

    public Key shoot = new Key();

    private int mouseX = 0;
    private int mouseY = 0;

    public InputHandler(Game game) {
        game.addKeyListener(this);
        game.addMouseMotionListener(this);
        game.addMouseListener(this);
    }

    //========================================================================
    //Key Listeners

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {}


    //========================================================================
    //Mouse Listeners

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        toggleMouse(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toggleMouse(false);
    }



    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void toggleMouse(boolean isPressed){
        shoot.toggle(isPressed);
    }

    public void toggleKey(int KeyCode, boolean isPressed) {
        if (KeyCode == KeyEvent.VK_W || KeyCode == KeyEvent.VK_UP) {
            up.toggle(isPressed);
        }
        if (KeyCode == KeyEvent.VK_A || KeyCode == KeyEvent.VK_LEFT) {
            left.toggle(isPressed);
        }
        if (KeyCode == KeyEvent.VK_S | KeyCode == KeyEvent.VK_DOWN) {
            down.toggle(isPressed);
        }
        if (KeyCode == KeyEvent.VK_D || KeyCode == KeyEvent.VK_RIGHT) {
            right.toggle(isPressed);
        }
        if (KeyCode == KeyEvent.VK_E){
            e_teleport.toggle(isPressed);
        }
        if (KeyCode == KeyEvent.VK_SPACE) {
            space.toggle(isPressed);
        }
    }

    public int getMouseX(){
        return mouseX;
    }

    public int getMouseY(){
        return mouseY;
    }

    //************************************************************
    //Key Class to store data for different types of keys
    //*************************************************************

    public class Key {
        private int numTimesPressed = 0;
        private boolean pressed = false;

        public void toggle(boolean isPressed) {
            pressed = isPressed;
            if(isPressed) {
                numTimesPressed++;
            }
        }

        public int getNumTimesPressed(){
            return numTimesPressed;
        }

        public boolean isPressed(){
            return pressed;
        }
    }
}

