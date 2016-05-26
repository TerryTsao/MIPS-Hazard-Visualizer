package gui.tester;

import javax.swing.JFrame;

import gui.ProcessorDiagram;

public class TestProcessorDiagramPrototype {
   public static void main(String[] args) {
      JFrame frame = new JFrame("MIPS Hazard Visualizer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      ProcessorDiagram processor = new ProcessorDiagram();
      frame.add(processor);
      frame.pack();
   }
}
