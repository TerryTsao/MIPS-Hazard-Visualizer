package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PipelineDiagram extends JPanel {
   public static final int NUM_OF_LINES = 5;

   private ProcessorDiagram[] processorDiagrams;
   private Arrow arrowTest;
   //private Arrow [] arrowArray; //holds all arrows to be drawn.

   public PipelineDiagram() {
      processorDiagrams = new ProcessorDiagram[NUM_OF_LINES];
      for (int i = 0; i < NUM_OF_LINES; i++)
         processorDiagrams[i] = new ProcessorDiagram(i);
      
      arrowTest = new Arrow();
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;
      setBackground(Color.red);
      setOpaque(true);
      setPreferredSize(new Dimension(300,900));

      double xScale = getWidth() 
            / (double)GUIConstants.PIPELINE_PANEL_REF_WIDTH;
      double yScale = getHeight() 
            / (double)GUIConstants.PIPELINE_PANEL_REF_HEIGHT;
      g2d.scale(xScale, yScale);

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

      for (ProcessorDiagram processor : processorDiagrams)
         processor.draw(g);
      
      // arrowTest.draw(g, 432, 240, 306);

      System.out.println(getWidth() + " " + getHeight());

   }
}
