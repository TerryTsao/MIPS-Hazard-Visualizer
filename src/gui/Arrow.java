package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Arrow to indicate ship angle/thrust.
 * 
 */
public class Arrow extends JComponent {
   private double posX, posY, length;
   private final static int FIRST_LINE_Y_POS = 150;
   private final static int FIRST_LINE_X_POS = 107;
   private final static int ARROW_HEAD_LENGTH = 12;
   private final static int EX_X_POS_ADJUSTMENT = 785;
   private final static int MEM_X_POS_ADJUSTMENT = 1085;


   protected enum cycleType {EX, MEM};
   /**
    * Constructor that sets arrow size.
    */
   public Arrow() {

   }

   /**
    * Reference: http://stackoverflow.com/questions/4112701/drawing-a-line-with-arrow-in-java
    * 
    * @param g
    * @param ship
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


   //When a value needs to be forwarded from one command to another, an arrow will be drawn accordingly
   public boolean setArrowPosition(ProcessorDiagram pro1, ProcessorDiagram pro2, 
         Arrow.cycleType type, String register) {
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

         return true;
      }
      else {
         posX = -1000; //Invalid input so set coordinate off screen
      }
      return false;
   }

   private int calcLength(int levelDifference) {
      return (levelDifference * ProcessorDiagram.Y_DISTANCE * 
            (int)ProcessorDiagram.SCALE_RATIO) - ARROW_HEAD_LENGTH;
   }

   private double calcPosY(int pro1Level) {
      return FIRST_LINE_Y_POS + (pro1Level * ProcessorDiagram.Y_DISTANCE * 
            (int)ProcessorDiagram.SCALE_RATIO);
   }

   private double calcPosX(int pro1Level) {
      return FIRST_LINE_X_POS + (pro1Level * ProcessorDiagram.INDENT * 
            ProcessorDiagram.SCALE_RATIO);
   }

   private void adjustLength(String register) {
      if(register.equals("RS") || register.equals("rs")) {
         length -= (40 * ProcessorDiagram.SCALE_RATIO / 10);
      }
      else if(register.equals("RT") || register.equals("rt")){
         length += (40 * ProcessorDiagram.SCALE_RATIO / 10);
      }
   }

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














