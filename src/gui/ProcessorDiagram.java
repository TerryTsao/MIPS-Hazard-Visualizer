package gui;

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
   private BufferedImage processorImage;

   public static final int INDENT = 30;

   public static final int Y_DISTANCE = 30;

   public static final Point ORIGIN = new Point(100, 100);

   public static final double SCALE_RATIO = 3.0;

   private int level;

   public ProcessorDiagram() {
      try {
         processorImage = ImageIO.read(new File("resources/Pipeline.png"));
      } catch (IOException ex) {}
      level = 0;
   }

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
      this.level = level >= 0 && level < PipelineDiagram.NUM_OF_LINES ? level : 0;
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
