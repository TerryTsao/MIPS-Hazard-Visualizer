import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Component;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;



public class FileReader
{
   static File asmFile;
   static Path asmFilePath;


   public FileReader(){

   }

   public static void openFileChooser() {
      JFileChooser chooser = new JFileChooser();
      //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      int returnVal = chooser.showOpenDialog(null);
      if(returnVal == JFileChooser.APPROVE_OPTION) {
         System.out.println("You chose to open this file: " +
               chooser.getSelectedFile().getName());
         asmFile = chooser.getSelectedFile();
         asmFilePath = Paths.get( asmFile.getPath());
         System.out.println(asmFilePath);
         readFile();
      }
   }

   private static void readFile() {
      Path file = asmFilePath;
      try (InputStream in = Files.newInputStream(file);
            BufferedReader reader =
                  new BufferedReader(new InputStreamReader(in))) {
         String line = null;
         while ((line = reader.readLine()) != null) {
            System.out.println(line);
         }
      } catch (IOException x) {
         System.err.println(x);
      }
   }

   public static File getFile() {
      return asmFile;
   }

   public static Path getPath() {
      return asmFilePath;
   }

   /**
    * 
    */
   public static void setLookAndFeel() {
      try {
         // Set cross-platform Java L&F (also called "Metal")
         UIManager.setLookAndFeel(
               "javax.swing.plaf.nimbus.NimbusLookAndFeel");
      } 
      catch (UnsupportedLookAndFeelException e) {
         // handle exception
      }
      catch (ClassNotFoundException e) {
         // handle exception
      }
      catch (InstantiationException e) {
         // handle exception
      }
      catch (IllegalAccessException e) {
         // handle exception
      }
   }




   public static void main(String [] args)
   {

      FileReader.setLookAndFeel();
      FileReader.openFileChooser();





   }






}
