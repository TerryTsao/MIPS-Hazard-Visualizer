package gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Main {
   public static void main(String[] args) {
      // FileReader.setLookAndFeel();
      // FileReader.openFileChooser();
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            JFrame frame = new JFrame("MIPS Hazard Visualizer");
            //frame.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(GUIConstants.INIT_FRAME_WIDTH,
                  GUIConstants.INIT_FRAME_HEIGHT);
            frame.setVisible(true);
            JPanel mainPanel = new JPanel(new BorderLayout());
            frame.add(mainPanel);
            mainPanel.setBackground(Color.cyan);
            InstructionPanel instructionPanel = new InstructionPanel();
            PipelineDiagram pipelinePanel = new PipelineDiagram();
            mainPanel.add(instructionPanel, BorderLayout.WEST);
            mainPanel.add(pipelinePanel, BorderLayout.CENTER);
         }
      });
   }
}
