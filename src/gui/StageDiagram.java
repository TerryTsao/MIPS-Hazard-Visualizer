package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;

import javax.swing.JComponent;

public class StageDiagram extends JComponent {
   private String stageName;
   private Rectangle dimension;

   public StageDiagram(String stageName, Rectangle dimension) {
      this.stageName = stageName;
      this.dimension = dimension;
   }

   /**
    * @return the stageName
    */
   public String getStageName() {
      return stageName;
   }

   /**
    * @param stageName
    *           the stageName to set
    */
   public void setStageName(String stageName) {
      this.stageName = stageName;
   }

   /**
    * @return the dimension
    */
   public Rectangle getDimension() {
      return dimension;
   }

   /**
    * @param dimension
    *           the dimension to set
    */
   public void setDimension(Rectangle dimension) {
      this.dimension = dimension;
   }

   @Override
   public void paintComponent(Graphics g) {
      Graphics2D g2d = (Graphics2D)g;
      g2d.setColor(Color.black);
      g2d.fillRect(dimension.x, dimension.y, dimension.width,
            dimension.height);
      Font font = g2d.getFont().deriveFont(10f);
      g2d.setFont(font);
      FontRenderContext frc = g2d.getFontRenderContext();
      int textWidth = (int)font.getStringBounds(stageName, frc).getWidth();
      LineMetrics lm = font.getLineMetrics(stageName, frc);
      int textHeight = (int)(lm.getAscent() + lm.getDescent());
      int sx = dimension.x + (dimension.width - textWidth) / 2;
      int sy = (int)(dimension.y + (dimension.height + textHeight) / 2
            - lm.getDescent());
      g.setColor(Color.white);
      g.drawString(stageName, sx, sy);
   }
}
