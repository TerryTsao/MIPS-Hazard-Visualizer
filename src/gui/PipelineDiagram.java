package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class PipelineDiagram extends JPanel {
   public static final int NUM_OF_LINES = 5;

   private ProcessorDiagram[] processorDiagrams;

   static public Arrow arrowsArray[] = new Arrow[4];
   static public Bubble bubblesArray[] = new Bubble[4];

   private boolean arrowVis[] = {false, false, false, false};
   private boolean bubbleVis[] = {false, false, false, false};

   //private Arrow [] arrowArray; //holds all arrows to be drawn.

   static {
      arrowsArray[0] = new Arrow();
      arrowsArray[1] = new Arrow();
      arrowsArray[2] = new Arrow();
      arrowsArray[3] = new Arrow();
      bubblesArray[0] = new Bubble();
      bubblesArray[1] = new Bubble();
      bubblesArray[2] = new Bubble();
      bubblesArray[3] = new Bubble();
   }
   
   
   public PipelineDiagram() {
      processorDiagrams = new ProcessorDiagram[NUM_OF_LINES];
      for (int i = 0; i < NUM_OF_LINES; i++)
         processorDiagrams[i] = new ProcessorDiagram(i);
   }
   
   public void setArrowVis(int index, boolean flag) {
      if (index >= 0 && index < 4)
         arrowVis[index] = flag;
   }

   public void setBubbleVis(int index, boolean flag) {
      if (index >= 0 && index < 4)
         bubbleVis[index] = flag;
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

      ProcessorDiagram pro0, pro1, pro2, pro3, pro4;
      pro0 = new ProcessorDiagram(0);
      pro1 = new ProcessorDiagram(1);
      pro2 = new ProcessorDiagram(2);
      pro3 = new ProcessorDiagram(3);
      pro4 = new ProcessorDiagram(4);

      arrowsArray[0].setArrowPosition(pro0, pro1, Arrow.cycleType.MEM, "", 0);
      arrowsArray[1].setArrowPosition(pro0, pro2, Arrow.cycleType.MEM, "RS", 9);
      arrowsArray[2].setArrowPosition(pro1, pro2, Arrow.cycleType.EX, "RT", 18);
      arrowsArray[3].setArrowPosition(pro1, pro3, Arrow.cycleType.EX, "", 27);

      bubblesArray[0].setLevel(pro0.getLevel());
      bubblesArray[1].setLevel(pro1.getLevel());
      bubblesArray[2].setLevel(pro2.getLevel());
      bubblesArray[3].setLevel(pro3.getLevel());

      for (int i = 0; i < 4; i++) {
         if (arrowVis[i])
            arrowsArray[i].draw(g);
         if (bubbleVis[i])
            bubblesArray[i].draw(g, this);
      }

      System.out.println(getWidth() + " " + getHeight());
   }
}
