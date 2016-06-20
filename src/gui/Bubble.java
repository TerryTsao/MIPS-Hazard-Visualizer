/**
 * Draws bubble on diagram stages for stall.
 */

package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Bubble extends JComponent {
   //private int size;
   /**
    * Image of bubble.
    */
   private Image image;
   /**
    * x and y position.
    */
   private double posX, posY;
   /**
    * Diagram level to draw on.
    */
   private int level;

   /**
    * Radius of bubble image.
    */
   private final static int RADIUS = 51;
   /**
    * Space between stages.
    */
   private final static int SPACING_BETWEEN_STAGES = 89;
   /**
    * The x position of first line.
    */
   private final static int FIRST_LINE_X_POS = 285;
   /**
    * The y position of first line.
    */
   private final static int FIRST_LINE_Y_POS = 124;

   /**
    * Default constructor. It sets the level to be zero and x and y positions
    * accordingly.
    */
   public Bubble() {
      this(0);
   }

   /**
    * Sets the level of the bubble and x and y positions accordingly.
    * 
    * @param level
    */
   public Bubble(int level) {
      this.level = level;
      updateXPos(level);
      updateYPos(level); 
      try {
         image = ImageIO.read(new File("resources/Bubble.png"));
      } catch (IOException ex) {}      
   }

   /**
    * Draws the bubble image.
    * 
    * @param g
    *           The Graphics object.
    * @param io
    *           The ImageObserver object.
    */
   public void draw(Graphics g, ImageObserver io) {
      Graphics2D g2d = (Graphics2D) g.create();

      //draw 3 bubble images at 3 pipeline stages.
      g2d.drawImage(image, (int)posX, (int)posY, RADIUS, RADIUS, io);
      g2d.drawImage(image, (int)posX+SPACING_BETWEEN_STAGES, (int)posY, RADIUS, RADIUS, io);
      g2d.drawImage(image, (int)posX+(SPACING_BETWEEN_STAGES*2), (int)posY, RADIUS, RADIUS, io);
   }

   /**
    * Sets level and x and y positions accordingly.
    * 
    * @param level
    * @return
    */
   public boolean setLevel(int level) {
      if (level >= 0 && level < GUIGlobal.NUM_OF_LINES) {
         this.level = level;
         updateXPos(level);
         updateYPos(level);
         return true;
      }
      return false;
   }

   /**
    * Update x position according to level.
    * 
    * @param level
    *           Level of diagram.
    */
   private void updateXPos(int level) {
      posX = FIRST_LINE_X_POS + (ProcessorDiagram.SCALE_RATIO * ProcessorDiagram.INDENT * level);
   }

   /**
    * Update y position according to level.
    * 
    * @param level
    *           Level of diagram.
    */
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
