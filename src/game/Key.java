package game;

public class Key {
    protected boolean pressed = false;
    protected long lastTimePressed;
    protected int key;

    public Key(int key) {
        this.key = key;
    }

    public int getKey() {
        return this.key;
    }

    public void set(int key, boolean isPressed) {
        if (key == this.key) {
            pressed = isPressed;
        }
    }

    //clicked once of on and once for off
    //isPressed until user presses again
    public void toggle(int key){
        // only switch if it has been 500ms since last time pressed
        if (key == this.key) {
            if(System.currentTimeMillis() - lastTimePressed > 500){
                pressed = !pressed;
                lastTimePressed = System.currentTimeMillis();
            }
        }
    }

    public boolean isPressed(){
        return pressed;
    }
}
