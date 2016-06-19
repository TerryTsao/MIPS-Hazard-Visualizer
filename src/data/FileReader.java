/**
 * Reads asm files.
 */

package data;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class FileReader
{
   /**
    * File object for the asm file.
    */
   private static File asmFile;
   /**
    * Path object for the asm file path.
    */
   private static Path asmFilePath;

   /**
    * Open file chooser object to choose file and load it.
    */
   public static void openFileChooser() {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         asmFile = chooser.getSelectedFile();
         asmFilePath = Paths.get(asmFile.getPath());
         InstructionList.clearList();
         readFile();
      }
      /*
      else {
         System.out.println("\nOPENING DEFAULT FILE\n");
         asmFile = new File("resources/default.asm");
         asmFilePath = Paths.get(asmFile.getPath());
         InstructionList.clearList();
         readFile();
      }*/
   }

   /**
    * Read asm file and parse the instructions with regex. Store them in a list
    * of Instructions objects.
    */
   private static void readFile() {
      Path file = asmFilePath;

      try (InputStream in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
         String line = new String();

         String pattern = "(\\.\\w+)?(?:\\s*([\\w.]+)\\s*:)?[\\s,]*([\\w.]+)?[\\s,]*(\\$[\\w]+)?[\\s,]*([\\w\\s+\\-(]*[$]?[\\w)]+)?[\\s,]*(\\$*[\\w]+)?[\\s,]*(?:#(.*))?";
         //Regex101 version: (\.\w+)?(?:\s*([\w.]+)\s*:)?[\s,]*([\w.]+)?[\s,]*(\$[\w]+)?[\s,]*([\w\s+\-(]*[$]?[\w)]+)?[\s,]*(\$*[\w]+)?[\s,]*(?:#(.*))?
         //Java-compatible version: (\\.\\w+)?(?:\\s*([\\w.]+)\\s*:)?[\\s,]*([\\w.]+)?[\\s,]*(\\$[\\w]+)?[\\s,]*([\\w\\s+\\-(]*[$]?[\\w)]+)?[\\s,]*(\\$*[\\w]+)?[\\s,]*(?:#(.*))?

         Pattern checkRegex = Pattern.compile(pattern);

         //Read line by line and break up code into its components. Stop reading at ".data"
         while ((line = reader.readLine()) != null && !line.equals(".data")) {   
            if(!line.trim().isEmpty()) {
               Matcher match = checkRegex.matcher(line);
               if (match.find()) {
                  String label = match.group(2);
                  String cmd = match.group(3);
                  String arg1 = match.group(4);
                  String arg2 = match.group(5);
                  String arg3 = match.group(6);
                  String comment = match.group(7) != null ? match.group(7).trim() : null;

                  addLine(label, cmd, arg1, arg2, arg3, comment);
               }
            }
         }
         InstructionList.printList();

      } catch (IOException e) {
         System.err.println(e);
      }
   }

   public File getFile() {
      return asmFile;
   }

   public Path getPath() {
      return asmFilePath;
   }

   /**
    * Adds a line in the asm file to InstructionList.
    * 
    * @param label
    *           The label.
    * @param cmd
    *           The command.
    * @param arg1
    *           The first argument.
    * @param arg2
    *           The second argument.
    * @param arg3
    *           The third argument.
    * @param comment
    *           The comment.
    */
   private static void addLine(String label, String cmd,
         String arg1, String arg2, String arg3, String comment) {
      InstructionList.addInstruction(new Instruction(label, cmd, arg1, arg2, arg3, comment));
   }

   /**
    * Set look and feel for GUI.
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

   /**
    * Opens a default asm file and reads it.
    */
   public static void openDefalutFile() {
      asmFile = new File("resources/default.asm");
      asmFilePath = Paths.get(asmFile.getPath());
      readFile();
   }
}
