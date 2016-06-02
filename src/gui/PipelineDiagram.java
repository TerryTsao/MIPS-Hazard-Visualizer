package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class PipelineDiagram extends JPanel {
   public static final int NUM_OF_LINES = 5;

   private ProcessorDiagram[] processorDiagrams;

   public PipelineDiagram() {
      setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
      processorDiagrams = new ProcessorDiagram[NUM_OF_LINES];
      for (int i = 0; i < NUM_OF_LINES; i++)
         processorDiagrams[i] = new ProcessorDiagram(i);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

      for (ProcessorDiagram processor : processorDiagrams)
         processor.draw(g);
   }
}
