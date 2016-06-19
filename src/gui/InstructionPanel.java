/**
 * The instructional panel that shows the currently shown instructions.
 */

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
   /**
    * The indent before every instructions.
    */
   public static final int INDENT = 33;

   /**
    * The instructions currently shown.
    */
   private Instruction[] instructions;

   /**
    * Sets panel size and instantiate members.
    */
   public InstructionPanel() {
      setPreferredSize(
            new Dimension(GUIGlobal.PIPELINE_PANEL_REF_WIDTH / 3, 900));

      instructions = new Instruction[GUIGlobal.NUM_OF_LINES];
      fetchInstructions();
   }

   /**
    * Fetch NUM_OF_LINES instructions according to program counter.
    */
   public void fetchInstructions() {
      for (int i = 0; i < GUIGlobal.NUM_OF_LINES; i++) {
         int pc = InstructionList.getProgramCounter();
         if (pc + i >= 0 && pc + i < InstructionList.getSize())
            instructions[i] = InstructionList.getList().get(pc + i);
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

   /**
    * Prints instructions on the panel.
    * 
    * @param g2d
    *           The Graphics2D object.
    */
   private void printInstructions(Graphics2D g2d) {
      int lineNum = InstructionList.getProgramCounter();

      for (int i = 0; i < GUIGlobal.NUM_OF_LINES; i++) {
         int y =
               (int)(ProcessorDiagram.ORIGIN.y + ProcessorDiagram.Y_DISTANCE
                     * (i + 0.69) * ProcessorDiagram.SCALE_RATIO);

         String[] parts = {
               instructions[i].getCmd(),
               instructions[i].getArg1(),
               instructions[i].getArg2(),
               instructions[i].getArg3()
               };
         if (instructions[i].getInstruction().length() > 2) {
            // line number
            g2d.setColor(Color.gray);
            Font temp = g2d.getFont();
            g2d.setFont(new Font("helvetica", Font.ITALIC, 17));
            g2d.drawString(String.format("%03d", lineNum++), 5, y);
            g2d.setFont(temp);
         }
         drawParts(parts, y, g2d);
      }
   }

   /**
    * Draws every parts of an instruction to constitute the entire instruction.
    * 
    * @param parts
    *           Every parts of the instruction in String, that is cmd, arg1,
    *           arg2, arg3.
    * @param y
    *           The y coordinate to draw on.
    * @param g2d
    *           The Graphics2D object.
    */
   private void drawParts(String[] parts, int y, Graphics2D g2d) {
      int x = 0;
      int pc = InstructionList.getProgramCounter();

      for (int i = 0; i < parts.length; i++) {
         if (parts[i] == null)
            continue;
         // TODO: add validator
         if (true)
            g2d.setColor(Color.black);
         else
            g2d.setColor(Color.red);
         if (pc + i >= 0 && pc + i < InstructionList.getSize())
            g2d.drawString(parts[i], x += INDENT * 2, y);
      }
   }
}
