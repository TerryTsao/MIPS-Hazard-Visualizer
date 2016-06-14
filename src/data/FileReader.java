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
   private static File asmFile;
   private static Path asmFilePath;

   public static void openFileChooser() {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         asmFile = chooser.getSelectedFile();
         asmFilePath = Paths.get(asmFile.getPath());
         InstructionList.clearList();
         readFile();
      }
   }

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

   private static void addLine(String label, String cmd,
         String arg1, String arg2, String arg3, String comment) {
      InstructionList.addInstruction(new Instruction(label, cmd, arg1, arg2, arg3, comment));
   }

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

   public static void openDefalutFile() {
      asmFile = new File("resources/DataHazard_Sample_CS40A.asm");
      asmFilePath = Paths.get(asmFile.getPath());
      readFile();
   }
}
