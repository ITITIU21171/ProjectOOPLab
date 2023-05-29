package src;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import java.io.IOException;

public class FDog extends Screen {

    public static float g = 0.1f;

    private BufferedImage dog;

    private Animation dogAnima;

    private BufferedImage background;

    private Doge doge;

    private ground Ground;

    private wallGroup Walls;

    private int score = 0;

    private int beginScreen = 0;
    private int playScreen = 1;
    private int endScreen = 2; 
    private int currentScreen = beginScreen;

    public FDog(){
        super(800,600);
        try {
            dog = ImageIO.read(new File("image/doge.png"));
            background = ImageIO.read(new File("image/background3.png"));
        } catch (IOException e) {
        }

        dogAnima = new Animation(50);
        AFrameOnImage foi;
        foi = new AFrameOnImage(0, 0, 60, 80);
        dogAnima.AddFrame(foi);
        foi = new AFrameOnImage(180, 0, 60, 80);
        dogAnima.AddFrame(foi);
        foi = new AFrameOnImage(360, 0, 60, 80);
        dogAnima.AddFrame(foi);
        foi = new AFrameOnImage(180, 0, 60, 80);
        dogAnima.AddFrame(foi);

        doge = new Doge(350, 250, 50, 50); 
        Ground = new ground();
        Walls = new wallGroup();

        startgame();
    }



    public static void main(String[] args) {
       new FDog();
    }

    public void resetGame() {
        doge.setPos(350, 250);
        doge.setLive(true);
        doge.setSpd(0);
        score = 0;
        Walls.resetWalls();
    }

    @Override
    public void gameUpdate(long deltaTime) {
        if (currentScreen == beginScreen) {
            resetGame();
        }
        else if(currentScreen == playScreen){
            if(doge.getIsLive())
            dogAnima.Update_Me(deltaTime);
            doge.update(deltaTime);
            Walls.updateWall();
            Ground.Update();
        }

        for (int i = 0; i < wallGroup.Size; i++) {
            if(doge.getRect().intersects(Walls.getWall(i).getRect())){
                doge.setLive(false);
                System.out.println("set live = false");
            }
        }

        for (int i = 0; i < wallGroup.Size; i++) {
            if (doge.getPosX() > Walls.getWall(i).getPosX() && !Walls.getWall(i).getIsBehindDoge() && i%2==0) {
                score++;
                Walls.getWall(i).setIsBehindDoge(true);
            }
        }

        if(doge.getPosY() + doge.getH() > Ground.getYGround()) currentScreen = endScreen;
    }

    @Override
    public void gamePaint(Graphics2D g2) {
        g2.setColor(Color.decode("#a9d9ce"));
        g2.fillRect(0, 0, masterWidth, masterHeight);

        g2.drawImage(background, 0, 0, masterWidth, masterHeight, null);


        Walls.paint(g2);

        Ground.Paint(g2);

        if (doge.getIsFlying()) {
            dogAnima.PaintAnims((int)doge.getPosX(),(int) doge.getPosY(), dog, g2, 0, -1);
        }else dogAnima.PaintAnims((int)doge.getPosX(),(int) doge.getPosY(), dog, g2, 0, 0);

        g2.setFont(new Font("Arrial", 2, 30));
 
        if(currentScreen == beginScreen){
            g2.setColor(Color.decode("#511838"));
            g2.drawString("press Space to play game", 200, 400);
        }
        if (currentScreen == endScreen) {
            g2.setColor(Color.decode("#511838"));
            g2.drawString("GAME OVER :(((", 300, 150);
            g2.drawString("press Space to play again", 200, 400);
        }

        g2.setColor(Color.decode("#511838"));
            g2.drawString("Score: "+score, 20, 50);
    }

    
    @Override
    public void keyAction(KeyEvent e, int event) {
        if (event == keyPressed) {
            if (currentScreen == beginScreen) {
                currentScreen = playScreen;
            }else if(currentScreen == playScreen){
                if(doge.getIsLive()) doge.fly();
            }else if(currentScreen == endScreen){
                currentScreen = beginScreen;
            }
        }
    }
 
}