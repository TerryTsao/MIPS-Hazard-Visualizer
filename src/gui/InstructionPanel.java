package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import data.Instruction;
import data.InstructionList;

public class InstructionPanel extends JPanel {
   public static final int NUM_OF_LINES = 5;

   private String[] instructions;
   private InstructionList iListReference;

   public InstructionPanel(InstructionList iList) {
      instructions = new String[NUM_OF_LINES];
      for (int i = 0; i < NUM_OF_LINES; i++)
         instructions[i] = new String();
      iListReference = iList;
   }

   public void fetchInstructions() {
      int pc = iListReference.getProgramCounter();
      for (int i = 0; i < NUM_OF_LINES; i++)
         instructions[i] = iListReference.getInstructionAtIndex(pc + i);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D)g;

      double xScale = getWidth() 
            / (double)GUIConstants.INSTRUCTION_PANEL_REF_WIDTH;
      double yScale = getHeight() 
            / (double)GUIConstants.INSTRUCTION_PANEL_REF_HEIGHT;
      g2d.scale(xScale, yScale);

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

      for (int i = 0; i < NUM_OF_LINES; i++)
         g2d.drawString(instructions[i], 200, 200 + 200 * i);
   }
}
