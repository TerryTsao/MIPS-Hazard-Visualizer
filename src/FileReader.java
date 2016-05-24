import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;


public class FileReader
{
      
   
   public static void main(String [] args)
   {
            
      JFileChooser chooser = new JFileChooser();
      //FileNameExtensionFilter filter = new FileNameExtensionFilter(
          //"JPG & GIF Images", "jpg", "gif");
      //chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         System.out.println("You chose to open this file: " +
              chooser.getSelectedFile().getName());
      }
      
      
   }

}
