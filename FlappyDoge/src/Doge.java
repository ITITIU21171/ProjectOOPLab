package src;

import java.awt.Rectangle;

public class Doge extends Objects {
    private float spd = 0;

    private boolean isFlying= false;
    private Rectangle rect;
    private boolean isLive= true;

    public Doge(int x, int y, int w, int h){
            super(x,y,w,h);
            rect = new Rectangle(x,y,w,h);
        }
    public void setLive(boolean b){
        isLive = b;
    }
    public boolean getIsLive(){
            return isLive;
        }
    public Rectangle getRect(){
        return rect;
    }
    public void setSpd(float vt){
        this.spd = vt;
    }
    public void update(long delTime){
        spd += FDog.g;

        this.setPosY(this.getPosY()+ spd);
        this.rect.setLocation((int)this.getPosX(), (int)this.getPosY());
        if(spd < 0) isFlying=true;
        else isFlying = false;
    }
    public void fly(){
        spd = -3;
    }
    public boolean getIsFlying(){
            return isFlying;
    }
}
