package src;

import javax.swing.JFrame;


import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public abstract class Screen extends JFrame implements KeyListener {

    public static int keyPressed = 0;
    public static int keyReleased = 1;
    public int screenHeight = 500;
    public int screenWidth = 500 ;
    public int masterHeight = 500, masterWidth = 500 ;
    public thread gameThread;




    public Screen(int w,int h) {

        this.screenWidth = w;
        this.screenHeight = h;
        masterHeight = screenHeight;
        masterWidth = screenWidth;
        createScreen();
        createThread();
    }

    public void startgame() {
        gameThread.startThread();
    }

    public void RegisterImage(int id, BufferedImage image){
        
    }
    
    public BufferedImage getImageWithID(int id){
        return null;
    }

    public void createThread() {
        gameThread = new thread(this);
        add(gameThread);
    }

    public void createScreen() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        setVisible(true);
        setSize(1000, 800);
        setLocation(450, 200);
        setTitle("Doge escape");
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyAction(e, Screen.keyPressed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyAction(e, Screen.keyReleased);
    }

    public abstract void gameUpdate(long deltaTime);
    public abstract void gamePaint(Graphics2D g);
    public abstract void keyAction(KeyEvent e, int event);
    
}
