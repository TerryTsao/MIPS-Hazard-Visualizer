package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import data.Instruction;
import data.InstructionList;

public class InstructionPanel extends JPanel {
   public static final int NUM_OF_LINES = 5;

   public static final int INDENT = 30;

   private Instruction[] instructions;

   public InstructionPanel() {
      setPreferredSize(
            new Dimension(GUIGlobal.PIPELINE_PANEL_REF_WIDTH / 3, 900));

      instructions = new Instruction[NUM_OF_LINES];
      fetchInstructions();
   }

   public void fetchInstructions() {
      for (int i = 0; i < NUM_OF_LINES; i++) {
         instructions[i] = InstructionList.getList()
               .get(InstructionList.getProgramCounter() + i);
      }
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      setPreferredSize(new Dimension(GUIGlobal.mainPanelWidth / 3,
            GUIGlobal.mainPanelHeight));
      Graphics2D g2d = (Graphics2D)g;

      double xScale =
            getWidth() / (double)GUIGlobal.INSTRUCTION_PANEL_REF_WIDTH;
      double yScale =
            getHeight() / (double)GUIGlobal.INSTRUCTION_PANEL_REF_HEIGHT;
      g2d.scale(xScale, yScale);

      // Anti-aliasing
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setColor(Color.black);
      g2d.setFont(new Font("Arial", Font.BOLD, 30));

      // for (int i = 0; i < NUM_OF_LINES; i++) {
      // int y = (int)(ProcessorDiagram.ORIGIN.y + ProcessorDiagram.Y_DISTANCE
      // * (i + 0.69) * ProcessorDiagram.SCALE_RATIO);
      // g2d.drawString(instructions[i], INDENT, y);
      // }
      printInstructions(g2d);
   }

   private void printInstructions(Graphics2D g2d) {
      for (int i = 0; i < NUM_OF_LINES; i++) {
         String[] parts = {
               instructions[i].getCmd(),
               instructions[i].getArg1(),
               instructions[i].getArg2(),
               instructions[i].getArg3()
               };
         drawParts(parts, i, g2d);
      }
   }

   private void drawParts(String[] parts, int level, Graphics2D g2d) {
      int y = (int)(ProcessorDiagram.ORIGIN.y + ProcessorDiagram.Y_DISTANCE
            * (level + 0.69) * ProcessorDiagram.SCALE_RATIO);
      int x = 0;
      for (int i = 0; i < parts.length; i++) {
         if (parts[i] == null)
            continue;
         // TODO: add validator
         if (true)
            g2d.setColor(Color.black);
         else
            g2d.setColor(Color.red);
         g2d.drawString(parts[i], x += INDENT * 2, y);
      }
   }
}
