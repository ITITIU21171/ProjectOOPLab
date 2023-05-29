package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class thread extends JPanel implements Runnable {
    

    private Thread thread;

    private Screen context;

    private float scale_x = 1 ,scale_y = 1 ;

    private BufferedImage iBufferedImage;

    public static int FPS = 120;

    private int masterWidth,masterHeight;

    public thread(Screen scr){
        this.context = scr;
        masterHeight = context.screenHeight;
        masterWidth = context.screenWidth;
        this.thread = new Thread(this);
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, context.screenWidth, context.screenHeight);
        Graphics2D g2 = (Graphics2D)g;
        if (iBufferedImage != null) {
            g2.scale(scale_x, scale_y);
            g2.drawImage(iBufferedImage, 0, 0, null);
        }
    }
     public void startThread() {
        thread.start();
    }

    public void sizeUpdated() {
        if (this.getWidth() <= 0 ) return;

        context.screenHeight = this.getHeight();
        context.screenWidth = this.getWidth();

        scale_x = (float)context.screenHeight/(float)masterHeight;
        scale_y = (float)context.screenWidth/(float)masterWidth;
    }
    //to scale the screen to the image size


    @Override
    public void run() {
        long T = 1000/FPS;
        long beginTime = System.currentTimeMillis();
        long endTime;
        long sleepTime;
        long TimeBuffer = T/2;
        
        while(true){    
            
            sizeUpdated();
            
            context.gameUpdate(System.currentTimeMillis());
            try{
                
                iBufferedImage = new BufferedImage(masterWidth, masterHeight, BufferedImage.TYPE_INT_ARGB);
                if(iBufferedImage == null) return;
                Graphics2D g2 = (Graphics2D) iBufferedImage.getGraphics();
                
                if(g2!=null){
                    context.gamePaint(g2);
                }
                    
            }catch(Exception myException){
                myException.printStackTrace();
            }
            
            repaint();
            
            endTime = System.currentTimeMillis();
            sleepTime = T - (endTime - beginTime);
            if(sleepTime < 0) sleepTime = TimeBuffer;
            
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {}
            
            beginTime = System.currentTimeMillis();
        }

    }
    
}
