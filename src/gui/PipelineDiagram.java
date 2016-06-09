package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PipelineDiagram extends JPanel {
   public static final int NUM_OF_LINES = 5;

   private ProcessorDiagram[] processorDiagrams;
   private Arrow arrowTest;
   private Bubble bubbleTest;
   //private Arrow [] arrowArray; //holds all arrows to be drawn.

   public PipelineDiagram() {
      processorDiagrams = new ProcessorDiagram[NUM_OF_LINES];
      for (int i = 0; i < NUM_OF_LINES; i++)
         processorDiagrams[i] = new ProcessorDiagram(i);
      
      arrowTest = new Arrow();
      bubbleTest = new Bubble();
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      double xScale = getWidth() 
            / (double)GUIGlobal.PIPELINE_PANEL_REF_WIDTH;
      double yScale = getHeight() 
            / (double)GUIGlobal.PIPELINE_PANEL_REF_HEIGHT;
      g2d.scale(xScale, yScale);

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

      for (ProcessorDiagram processor : processorDiagrams)
         processor.draw(g);
      
      arrowTest.setPosX(432);
      arrowTest.setPosY(240);
      arrowTest.setLength(66);
      arrowTest.draw(g);
      
      bubbleTest.setLevel(0);
      bubbleTest.draw(g, this);
      
      bubbleTest.setLevel(2);
      bubbleTest.draw(g, this);
      
      bubbleTest.setLevel(4);
      bubbleTest.draw(g, this);
      
      //should not set level to 5 because it's > NUM_OF_LINES
      bubbleTest.setLevel(5);
      bubbleTest.draw(g, this);

      System.out.println(getWidth() + " " + getHeight());
   }
}
