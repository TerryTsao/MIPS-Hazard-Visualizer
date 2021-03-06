/**
 * The main panel of the program.
 * It includes the instruction panel and the pipeline diagram panel.
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MainPanel extends JPanel {
   /**
    * The instruction panel object.
    */
   private InstructionPanel instructionPanel;
   /**
    * The pipeline diagram object.
    */
   private PipelineDiagram pipelinePanel;

   /**
    * Default constructor.
    */
   public MainPanel() {
      setLayout(new BorderLayout());
      instructionPanel = new InstructionPanel();
      pipelinePanel = new PipelineDiagram();
      add(instructionPanel, BorderLayout.WEST);
      add(pipelinePanel, BorderLayout.CENTER);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      GUIGlobal.mainPanelWidth = getWidth();
      GUIGlobal.mainPanelHeight = getHeight();
   }

   /**
    * @return the instructionPanel
    */
   public InstructionPanel getInstructionPanel() {
      return instructionPanel;
   }

   /**
    * @param instructionPanel
    *           the instructionPanel to set
    */
   public void setInstructionPanel(InstructionPanel instructionPanel) {
      this.instructionPanel = instructionPanel;
   }

   /**
    * @return the pipelinePanel
    */
   public PipelineDiagram getPipelinePanel() {
      return pipelinePanel;
   }

   /**
    * @param pipelinePanel
    *           the pipelinePanel to set
    */
   public void setPipelinePanel(PipelineDiagram pipelinePanel) {
      this.pipelinePanel = pipelinePanel;
   }
}
