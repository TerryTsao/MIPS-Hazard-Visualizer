package gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
   private JButton prev, next;
   private JButton switchView;
   private JButton loadFile;

   public ControlPanel() {
      setLayout(new FlowLayout(FlowLayout.RIGHT));
      prev = new JButton("prev");
      next = new JButton("next");
      switchView = new JButton("view");
      loadFile = new JButton("load");
      add(prev);
      add(next);
      add(switchView);
      add(loadFile);
   }
}
