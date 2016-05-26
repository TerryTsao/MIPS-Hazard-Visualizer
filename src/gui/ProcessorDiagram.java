package gui;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ProcessorDiagram extends JPanel {
   public ProcessorDiagram() {
      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      setPreferredSize(new Dimension(400, 100));
      add(new JButton("IF"));
      add(Box.createHorizontalGlue());
      add(new JButton("ID"));
      add(Box.createHorizontalGlue());
      add(new JButton("EX"));
      add(Box.createHorizontalGlue());
      add(new JButton("MEM"));
      add(Box.createHorizontalGlue());
      add(new JButton("WB"));
   }
}
