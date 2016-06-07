package gui.tester;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import gui.GUIGlobal;
import gui.InstructionPanel;

public class TestInstructPanel {
   public static void main(String[] args) {
      // FileReader.setLookAndFeel();
      // FileReader.openFileChooser();
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            JFrame frame = new JFrame("MIPS Hazard Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(GUIGlobal.INIT_FRAME_WIDTH,
                  GUIGlobal.INIT_FRAME_HEIGHT);
            frame.setVisible(true);
            InstructionPanel ip = new InstructionPanel();
            frame.add(ip, BorderLayout.WEST);
         }
      });
   }
}
