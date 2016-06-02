package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JComponent;

/**
 * Arrow to indicate ship angle/thrust.
 * 
 */
public class Arrow extends JComponent {
   private int size;
   private double posX, posY, length;

   private enum cycleType {EX, MEM};
   /**
    * Constructor that sets arrow size.
    */
   public Arrow() {
      size = 4;
   }

   /**
    * Draw a vector arrow from the ship.
    * Magnitude and angle indicate ship thrust.
    * 
    * Reference: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    * 
    * @param g
    * @param ship
    */
   void draw(Graphics g, int coordX, int coordYBegin, int coordYEnd) {
      Graphics2D g2d = (Graphics2D) g.create();

      // Draw vertical arrow
      g2d.setColor(Color.red);
      g2d.setStroke(new BasicStroke(3));
      g2d.drawLine(coordX, coordYBegin, coordX, coordYEnd);
      g2d.setStroke(new BasicStroke(0));
      g2d.fillPolygon(new int[] {coordX+5, coordX, coordX-5},
            new int[] {coordYEnd, coordYEnd+12, coordYEnd}, 3);
   }


   public boolean setArrowPosition(ProcessorDiagram pro1, ProcessorDiagram pro2, Arrow.cycleType type) {
      if(pro1 != pro2) {
         int levelDifference = pro2.getLevel() - pro1.getLevel();
         length = levelDifference * ProcessorDiagram.Y_DISTANCE * (int)ProcessorDiagram.SCALE_RATIO;
         posY =  pro1.getLevel() * ProcessorDiagram.Y_DISTANCE * (int)ProcessorDiagram.SCALE_RATIO ;
         posX = pro1.getLevel() * ProcessorDiagram.INDENT * (int)ProcessorDiagram.SCALE_RATIO ;
         switch (type){
         case EX: 
            posX += 800 * ProcessorDiagram.SCALE_RATIO;
            break;
         case MEM:
            posX += 1100 * ProcessorDiagram.SCALE_RATIO;
            break;
         }

         return true;
      }
      return false;
   }
}














