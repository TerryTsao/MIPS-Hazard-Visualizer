package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ProcessorDiagram extends JPanel {
   private final String[] STAGE_NAMES = { "IF", "ID", "EX", "MEM", "WB" };

   public ProcessorDiagram() {
      setLayout(new GridBagLayout());
      setBorder(BorderFactory.createLineBorder(Color.red));
      addStages();
   }

   private void addStages() {
      for (int i = 0; i < 5; i++) {
         StageDiagram is;
         is = new StageDiagram(STAGE_NAMES[i],
               new Rectangle(0, 0, 100, 100));
         GridBagConstraints constraint = new GridBagConstraints();
         constraint.gridx = i;
         constraint.gridy = 0;
         constraint.weightx = 0.5;
         constraint.ipady = 100;
         constraint.fill = GridBagConstraints.HORIZONTAL;
         add(is, constraint);
      }
   }
}
