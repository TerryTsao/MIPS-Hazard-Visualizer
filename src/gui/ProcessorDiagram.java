package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ProcessorDiagram extends JPanel {
   private final String[] STAGE_NAMES = { "IF", "ID", "EX", "MEM", "WB" };

   public ProcessorDiagram() {
      setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
      setPreferredSize(new Dimension(400, 40));
      setMaximumSize(getPreferredSize());
      setBorder(BorderFactory.createLineBorder(Color.red));
      addStages();
   }

   private void addStages() {
      JButton[] stages = new JButton[5];
      for (int i = 0; i < 5; i++) {
         add(Box.createHorizontalGlue());
         stages[i] = new JButton(STAGE_NAMES[i]);
         stages[i].setMinimumSize(new Dimension(500, 277));
         add(stages[i]);
      }
      add(Box.createHorizontalGlue());
   }
}
