package src;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class wallGroup {
    private QueueList<wall> walls;
    private BufferedImage wallImage, wallImage2;

    public static int Size = 6;
    private int topChimneyY= -350;
    private int bottomChimneyY= 200;

    public wall getWall(int i){
       return walls.get(i);
    }

    public int getRandomY(){
       Random random = new Random();
      int a;
      a = random.nextInt(10);

      return a*35;
    }
    public wallGroup(){
     try {
        wallImage = ImageIO.read(new File("image/wall2.png"));
        wallImage2 = ImageIO.read(new File("image/wall2.png"));
     } catch (IOException ex) {}

          walls = new QueueList<wall>();
          wall cn;
     for (int i = 0; i < Size/2; i++) {
        int delY = getRandomY() + 20;

        cn = new wall(800 +i *250, bottomChimneyY + delY, 70,300);
        walls.addObj(cn);

        cn = new wall(800 +i *250, topChimneyY + delY, 70,300);
        walls.addObj(cn);

     }
  }
  public void resetWalls(){
     walls = new QueueList<wall>();

     wall cn;
     for (int i = 0; i < Size/2; i++) {
        int delY = getRandomY();

        cn = new wall(800 +i *250, bottomChimneyY + delY, 70,300);
        walls.addObj(cn);

        cn = new wall(800 +i *250, topChimneyY + delY, 70,300);
        walls.addObj(cn);

  }
}
public void updateWall(){
  for (int i = 0; i < Size; i++) {
     walls.get(i).update();
  }
  if(walls.get(0).getPosX()< 74){
     int delY = getRandomY();

     wall cn;
     cn = walls.pop();
     cn.setPosX(walls.get(4).getPosX() + 300);
     cn.setPosY(bottomChimneyY + delY);
     cn.setIsBehindDoge(false);
     walls.addObj(cn);

     cn = walls.pop();
     cn.setPosX(walls.get(4).getPosX());
     cn.setPosY(topChimneyY + delY);
     cn.setIsBehindDoge(false);
     walls.addObj(cn);
  }
}
public void paint(Graphics2D g2){
  for (int i = 0; i < 6; i++) {
     if (i%2 == 0)
     g2.drawImage(wallImage,(int) walls.get(i).getPosX(),(int)walls.get(i).getPosY(), null);
     else g2.drawImage(wallImage2, (int)walls.get(i).getPosX(), (int)walls.get(i).getPosY(),null);
     
  }
}
}
