/**
 * The processor in the pipeline diagram.
 */

package gui;

import static gui.GUIGlobal.NUM_OF_LINES;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ProcessorDiagram {
   /**
    * The image of processor diagram.
    */
   private BufferedImage processorImage;

   /**
    * The indent on each level.
    */
   public static final int INDENT = 30;

   /**
    * The y distance between each level.
    */
   public static final int Y_DISTANCE = 30;

   /**
    * The origin to draw on level 0.
    */
   public static final Point ORIGIN = new Point(100, 100);

   /**
    * The scale ratio to draw on initial screen size.
    */
   public static final double SCALE_RATIO = 3.0;

   /**
    * The level of this processor diagram.
    */
   private int level;

   /**
    * Load the image and set level to 0.
    */
   public ProcessorDiagram() {
      try {
         processorImage = ImageIO.read(new File("resources/Pipeline.png"));
      } catch (IOException ex) {}
      level = 0;
   }

   /**
    * Load the image and set the level.
    * 
    * @param level
    *           The level of this diagram.
    */
   public ProcessorDiagram(int level) {
      this();
      setLevel(level);
   }

   /**
    * @return the processorImage
    */
   public BufferedImage getProcessorImage() {
      return processorImage;
   }

   /**
    * @param processorImage
    *           the processorImage to set
    */
   public void setProcessorImage(BufferedImage processorImage) {
      this.processorImage = processorImage;
   }

   /**
    * @return the level
    */
   public int getLevel() {
      return level;
   }

   /**
    * @param level
    *           the level to set
    */
   public void setLevel(int level) {
      this.level = level >= 0 && level < NUM_OF_LINES ? level : 0;
   }

   public void draw(Graphics g) {
      Graphics2D g2d = (Graphics2D)g;
      AffineTransform trans = new AffineTransform();
      trans.translate(ORIGIN.x + INDENT * level * SCALE_RATIO,
            ORIGIN.y + Y_DISTANCE * level * SCALE_RATIO);
      trans.scale(SCALE_RATIO / 10 , SCALE_RATIO / 10);
      AffineTransformOp op =
            new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
      g2d.drawImage(processorImage, op, 0, 0);
   }
}
