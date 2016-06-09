package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * Arrow to indicate ship angle/thrust.
 * 
 */
public class Bubble extends JComponent {
   //private int size;
   private Image image;
   private double posX, posY, length;

   private enum cycleType {EX, MEM};
   /**
    * Constructor that sets arrow size.
    */
   
   public Bubble(String filename, double posX, double posY) {
      this((new ImageIcon(filename)).getImage(), posX, posY);
   }
   
   public Bubble(Image img, double posX, double posY) {
      image = img;
      this.posX = posX;
      this.posY = posY;
   }
   
   public Bubble() {
      
   }
   
   public Bubble(double posX, double posY) {
      
   }

   /**
    * 
    * Reference: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    * 
    * @param g
    * @param ship
    */
   void draw(Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();

      // Draw vertical arrow
      g2d.setColor(Color.red);
      g2d.setStroke(new BasicStroke(3));
      g2d.drawLine((int)posX, (int)posY, (int)posX, (int)(posY + length));
      g2d.setStroke(new BasicStroke(0));
      g2d.fillPolygon(new int[] {(int)posX+5, (int)posX, (int)posX-5},
            new int[] {(int)(posY + length), (int)(posY + length + 12), (int)(posY + length)}, 3);
   }


//   //When a value needs to be forwarded from one command to another, an arrow will be drawn accordingly
//   //TODO: Handle cases where arrow needs to differentiate between rs and rt registers.
//   public boolean setArrowPosition(ProcessorDiagram pro1, ProcessorDiagram pro2, Arrow.cycleType type) {
//      if(pro1 != pro2) {
//         int levelDifference = pro2.getLevel() - pro1.getLevel();
//         length = levelDifference * ProcessorDiagram.Y_DISTANCE * (int)ProcessorDiagram.SCALE_RATIO;
//         posY =  pro1.getLevel() * ProcessorDiagram.Y_DISTANCE * (int)ProcessorDiagram.SCALE_RATIO ;
//         posX = pro1.getLevel() * ProcessorDiagram.INDENT * (int)ProcessorDiagram.SCALE_RATIO ;
//         switch (type){
//         case EX: 
//            posX += 800 * ProcessorDiagram.SCALE_RATIO;
//            break;
//         case MEM:
//            posX += 1100 * ProcessorDiagram.SCALE_RATIO;
//            break;
//         }
//
//         return true;
//      }
//      return false;
//   }


   public boolean setPosX(double posX) {
      if(posX >= 0) {
         this.posX = posX;
         return true;
      }
      return false;
   }

   public boolean setPosY(double posY) {
      if(posY >= 0) {
         this.posY = posY;
         return true;
      }
      return false;
   }

   public boolean setLength(double length) {
      if(length >= 0) {
         this.length = length;
         return true;
      }
      return false;
   }

   public double getPosX() {
      return posX;
   }

   public double getPosY() {
      return posY;
   }

   public double getLength() {
      return length;
   }

}














