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

      //int len = coordYEnd - coordYBegin;


      // Draw vertical arrow
      g2d.setColor(Color.red);
      g2d.setStroke(new BasicStroke(2));
      g2d.drawLine(coordX, coordYBegin, coordX, coordYEnd);
      g2d.setStroke(new BasicStroke(0));
      g2d.fillPolygon(new int[] {coordX+6, coordX, coordX-6},
                      new int[] {coordYEnd, coordYEnd+10, coordYEnd}, 3);
  }
}
