package gui.tester;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import gui.ProcessorDiagram;

public class TestProcessorDiagramPrototype {
   public static void main(String[] args) {
      JFrame frame = new JFrame("MIPS Hazard Visualizer");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JPanel panel = new JPanel();
      frame.setVisible(true);
      frame.add(panel);
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      ProcessorDiagram processor = new ProcessorDiagram();
      panel.add(processor);
      frame.pack();
   }
}
