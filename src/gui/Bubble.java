package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * Arrow to indicate ship angle/thrust.
 * 
 */
public class Bubble extends JComponent {
   //private int size;
   private Image image;
   private double posX, posY;
   private int level;
   private final static int RADIUS = 51;
   private final static int SPACING_BETWEEN_STAGES = 89;
   private final static int FIRST_LINE_X_POS = 285;
   private final static int FIRST_LINE_Y_POS = 124;
   
   public Bubble() {
      this(0);
   }
   
   public Bubble(int level) {
      this.level = level;
      updateXPos(level);
      updateYPos(level); 
      try {
         image = ImageIO.read(new File("resources/Bubble.png"));
      } catch (IOException ex) {}      
   }

   /**
    * 
    * Reference: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    * 
    * @param g
    * @param io
    */
   void draw(Graphics g, ImageObserver io) {
      Graphics2D g2d = (Graphics2D) g.create();

      //draw 3 bubble images at 3 pipeline stages.
      g2d.drawImage(image, (int)posX, (int)posY, RADIUS, RADIUS, io);
      g2d.drawImage(image, (int)posX+SPACING_BETWEEN_STAGES, (int)posY, RADIUS, RADIUS, io);
      g2d.drawImage(image, (int)posX+(SPACING_BETWEEN_STAGES*2), (int)posY, RADIUS, RADIUS, io);
   }


//   public boolean setPosX(double posX) {
//      if(posX >= 0) {
//         this.posX = posX;
//         return true;
//      }
//      return false;
//   }
//
//   public boolean setPosY(double posY) {
//      if(posY >= 0) {
//         this.posY = posY;
//         return true;
//      }
//      return false;
//   }
   
   public boolean setLevel(int level) {
      if(level >= 0 && level < PipelineDiagram.NUM_OF_LINES) {
         this.level = level;
         updateXPos(level);
         updateYPos(level);
         return true;
      }
      return false;
   }
   
   private void updateXPos(int level) {
      posX = FIRST_LINE_X_POS + (ProcessorDiagram.SCALE_RATIO * ProcessorDiagram.INDENT * level);
   }
   
   private void updateYPos(int level) {
      posY = FIRST_LINE_Y_POS + (ProcessorDiagram.SCALE_RATIO * ProcessorDiagram.Y_DISTANCE * level); 
   }

   public double getPosX() {
      return posX;
   }

   public double getPosY() {
      return posY;
   }

   public double getRadius() {
      return RADIUS;
   }
   
   public int getLevel() {
      return level;
   }

}














