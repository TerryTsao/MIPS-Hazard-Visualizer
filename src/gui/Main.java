package gui;

import javax.swing.JFrame;
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
            MainPanel mainPanel = new MainPanel();
            frame.add(mainPanel);
         }
      });
   }
}
