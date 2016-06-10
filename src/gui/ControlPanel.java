package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.FileReader;
import data.InstructionList;

public class ControlPanel extends JPanel {
   private JButton prev, next;
   private JButton switchView;
   private JButton loadFile;

   public ControlPanel(MainPanel mainPanel) {
      setLayout(new FlowLayout(FlowLayout.RIGHT));
      prev = new JButton("prev");
      next = new JButton("next");
      switchView = new JButton("view");
      loadFile = new JButton("load");
      add(prev);
      add(next);
      add(switchView);
      add(loadFile);

      prev.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            InstructionList.shiftProgramCounter(-1);
            mainPanel.getInstructionPanel().fetchInstructions();
            mainPanel.repaint();
         }
      });

      next.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            InstructionList.shiftProgramCounter(1);
            mainPanel.getInstructionPanel().fetchInstructions();
            mainPanel.repaint();
         }
      });

      loadFile.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            FileReader.openFileChooser();
         }
      });
   }
}
