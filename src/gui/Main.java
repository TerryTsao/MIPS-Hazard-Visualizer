/**
 * The main of the program. 
 * 
 * @author joshuaptfan, orrblue, TerryTsao
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import data.FileReader;

public class Main {
   public static void main(String[] args) {
      FileReader.setLookAndFeel();
      FileReader.openDefalutFile();
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            JFrame frame = new JFrame("MIPS Hazard Visualizer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(GUIGlobal.INIT_FRAME_WIDTH,
                  GUIGlobal.INIT_FRAME_HEIGHT);
            frame.setMinimumSize(new Dimension(GUIGlobal.MIN_FRAME_WIDTH,
                  GUIGlobal.MIN_FRAME_HEIGHT));
            frame.setVisible(true);
            JPanel panel = new JPanel(new BorderLayout());
            MainPanel mainPanel = new MainPanel();
            ControlPanel ctrlPanel = new ControlPanel(mainPanel);
            panel.add(ctrlPanel, BorderLayout.NORTH);
            panel.add(mainPanel, BorderLayout.CENTER);
            frame.add(panel);
         }
      });
   }
}
