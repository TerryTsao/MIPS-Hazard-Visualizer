/**
 * An arrow object is used to indicate forwarding in data hazard.
 */

package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Arrow extends JComponent {
   /**
    * Coordinates and length of arrow.
    */
   private double posX, posY, length;

   /**
    * Y coordinate of first line.
    */
   private final static int FIRST_LINE_Y_POS = 150;
   /**
    * X coordinate of first line.
    */
   private final static int FIRST_LINE_X_POS = 107;
   /**
    * Length of arrow head.
    */
   private final static int ARROW_HEAD_LENGTH = 12;
   /**
    * Adjustment of x coordinate for Execution stage.
    */
   private final static int EX_X_POS_ADJUSTMENT = 785;
   /**
    * Adjustment of x coordinate for Memory stage.
    */
   private final static int MEM_X_POS_ADJUSTMENT = 1085;

   /**
    * Type of cycle.
    */
   protected enum cycleType {EX, MEM};
   
   /**
    * Default constructor.
    */
   public Arrow() {
      posX = 0;
      posY = 0;
      length = 0;
   }

   /**
    * Drawing the arrow on the Graphics object.
    * 
    * Reference: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    * 
    * @param g 
    *           The Graphics object.  
    */
   public void draw(Graphics g) {
      Graphics2D g2d = (Graphics2D) g.create();

      // Draw vertical arrow
      g2d.setColor(Color.red);
      g2d.setStroke(new BasicStroke(3));
      g2d.drawLine((int)posX, (int)posY, (int)posX, (int)(posY + length));
      g2d.setStroke(new BasicStroke(0));
      g2d.fillPolygon(new int[] {(int)posX+5, (int)posX, (int)posX-5},
            new int[] {(int)(posY + length), (int)(posY + length + ARROW_HEAD_LENGTH), 
                  (int)(posY + length)}, 3);
   }

   /**
    * Sets arrow position according to ProcessDiagram, cycle type, register name
    * and offset value. Or off screen if processor diagram level difference if
    * not 1 or 2.
    * 
    * @param pro1
    *           Processor diagram the arrow starts from.
    * @param pro2
    *           Processor diagram the arrow goes to.
    * @param type
    *           Type of the cycle.
    * @param register
    *           Register the arrow goes to. Usually rs or rt.
    * @param offset
    *           The offset on x position.
    * @return true if processor level difference is 1 or 2, false otherwise.
    */
   public boolean setArrowPosition(ProcessorDiagram pro1, ProcessorDiagram pro2, 
         Arrow.cycleType type, String register, int offset) {
      int levelDifference = pro2.getLevel() - pro1.getLevel();
      if(pro1 != pro2 && levelDifference > 0 && levelDifference <= 2) {
         length = calcLength(levelDifference);
         posY =  calcPosY(pro1.getLevel());
         posX = calcPosX(pro1.getLevel());

         switch (type){
         case EX: 
            posX += (EX_X_POS_ADJUSTMENT * ProcessorDiagram.SCALE_RATIO / 10);
            //adjust length to reach rs or rt registers when needed
            adjustLength(register);
            break;
         case MEM:
            posX += (MEM_X_POS_ADJUSTMENT * ProcessorDiagram.SCALE_RATIO / 10);
            //adjust length to reach rs or rt registers when needed
            if(levelDifference == 2) {
               adjustLength(register);
            }
            break;
            
         }
         posX += offset;

         return true;
      }
      else {
         posX = -1000; //Invalid input so set coordinate off screen
      }
      return false;
   }

   /**
    * Calculate length of arrow.
    * 
    * @param levelDifference
    *           Level difference of processor diagrams, usually 1 or 2.
    * @return Calculated arrow length.
    */
   private int calcLength(int levelDifference) {
      return (levelDifference * ProcessorDiagram.Y_DISTANCE * 
            (int)ProcessorDiagram.SCALE_RATIO) - ARROW_HEAD_LENGTH;
   }

   /**
    * Calculate y position of arrow.
    * 
    * @param pro1Level
    *           Processor diagram level.
    * @return Calculated y position.
    */
   private double calcPosY(int pro1Level) {
      return FIRST_LINE_Y_POS + (pro1Level * ProcessorDiagram.Y_DISTANCE * 
            (int)ProcessorDiagram.SCALE_RATIO);
   }

   /**
    * Calculate x position of arrow.
    * 
    * @param pro1Level
    *           Processor diagram level.
    * @return Calculated x position.
    */
   private double calcPosX(int pro1Level) {
      return FIRST_LINE_X_POS + (pro1Level * ProcessorDiagram.INDENT * 
            ProcessorDiagram.SCALE_RATIO);
   }

   /**
    * Adjust length of arrow according to register name, usually rs or rt.
    * 
    * @param register
    *           Register name, rs or rt.
    */
   private void adjustLength(String register) {
      if(register.equals("RS") || register.equals("rs")) {
         length -= (40 * ProcessorDiagram.SCALE_RATIO / 10);
      }
      else if(register.equals("RT") || register.equals("rt")){
         length += (40 * ProcessorDiagram.SCALE_RATIO / 10);
      }
   }

   /**
    * Sets x position if posX is non-negative.
    * 
    * @param posX
    *           x position.
    * @return true if posX >= 0, false otherwise.
    */
   public boolean setPosX(double posX) {
      if(posX >= 0) {
         this.posX = posX;
         return true;
      }
      return false;
   }

   /**
    * Sets y position if posY is non-negative.
    * 
    * @param posY
    *           y position.
    * @return true if posY >= 0, false otherwise.
    */
   public boolean setPosY(double posY) {
      if(posY >= 0) {
         this.posY = posY;
         return true;
      }
      return false;
   }

   /**
    * Sets length if it's non-negative.
    * 
    * @param length
    *           length of arrow.
    * @return true if length >= 0, false otherwise.
    */
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
