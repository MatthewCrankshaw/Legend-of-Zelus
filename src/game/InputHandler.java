package game;

import game.Game;
import game.graphics.ui.UserInterface;

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
    public Key escape = new Key();
    public Key one = new Key();
    public Key two = new Key();

    private int mouseX = 0;
    private int mouseY = 0;

    private UserInterface ui;

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
            toggleMouse(true);
        }else if(uiPressed == -1){
            escape.pressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        toggleMouse(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    //========================================================================
    //Mouse toggling
    private void toggleMouse(boolean isPressed){
        space.toggle(isPressed);
    }

    private void toggleKey(int KeyCode, boolean isPressed) {
        switch(KeyCode){
            //Directional Keys for movement
            case KeyEvent.VK_UP:
                up.toggle(isPressed);
                break;
            case KeyEvent.VK_DOWN:
                down.toggle(isPressed);
                break;
            case KeyEvent.VK_LEFT:
                left.toggle(isPressed);
                break;
            case KeyEvent.VK_RIGHT:
                right.toggle(isPressed);
                break;

            //WASD For movement
            case KeyEvent.VK_W:
                up.toggle(isPressed);
                break;
            case KeyEvent.VK_S:
                down.toggle(isPressed);
                break;
            case KeyEvent.VK_A:
                left.toggle(isPressed);
                break;
            case KeyEvent.VK_D:
                right.toggle(isPressed);
                break;

            //Ability keys
            case KeyEvent.VK_E:
                e_teleport.toggle(isPressed);
                break;

            //Menu
            case KeyEvent.VK_ESCAPE:
                escape.swtch();
                if (escape.isPressed()){
                    ui.setGamePaused(true);
                }else{
                    ui.setGamePaused(false);
                }
                break;
            case KeyEvent.VK_1:
                if(escape.isPressed()){
                    System.out.println("Info: User quit program!");
                    System.exit(0);
                }
                break;
        }
    }

    //===============================================================
    //Getters and Setters

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
        private long lastTimePressed;

        //used for movement
        //isPressed for as long as user holds down key
        private void toggle(boolean isPressed) {
            pressed = isPressed;
            if(isPressed) {
                numTimesPressed++;
            }
        }

        //clicked once of on and once for off
        //isPressed until user presses again
        private void swtch(){
            // only switch if it has been 500ms since last time pressed
            if(System.currentTimeMillis() - lastTimePressed > 500){
                pressed = !pressed;
                lastTimePressed = System.currentTimeMillis();
            }
        }

        public int getNumTimesPressed(){
            return numTimesPressed;
        }

        public boolean isPressed(){
            return pressed;
        }
    }

    public void setUi(UserInterface ui) {
        this.ui = ui;
    }
}

