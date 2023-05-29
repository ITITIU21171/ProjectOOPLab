package src;

import java.awt.Rectangle;

public class wall extends Objects {
    private Rectangle rect;
    private boolean isBehindDoge = false;
    
    public wall(int x, int y, int w, int h){
        super(x, y, w, h);
        rect = new Rectangle(x, y, w,h);
    }
    public void update(){
        setPosX(getPosX() -2);
        rect.setLocation((int)this.getPosX(), (int)this.getPosY());
    }

    public Rectangle getRect() {
            return rect;
        }
    public void setIsBehindDoge(boolean b){
        isBehindDoge = b;
    }
    public boolean getIsBehindDoge(){
        return isBehindDoge;
    }
}
