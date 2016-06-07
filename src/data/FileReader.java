package data;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.regex.*;




public class FileReader
{
   private static File asmFile;
   private static Path asmFilePath;
   private static Instruction lineOfCode;
   private static InstructionList list = new InstructionList();
   private static Matcher match;

   private static String label = "";
   private static String cmd = "";
   private static String arg1 = "";
   private static String arg2 = "";
   private static String arg3 = "";
   private static String comment = "";


   public FileReader(){

   }

   public static void openFileChooser() {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         //System.out.println("You chose to open this file: " +
         //chooser.getSelectedFile().getName() + "\n");
         asmFile = chooser.getSelectedFile();
         asmFilePath = Paths.get( asmFile.getPath());
         //System.out.println(asmFilePath);
         readFile();
      }
   }

   private static void readFile() {
      Path file = asmFilePath;

      try (InputStream in = Files.newInputStream(file);
            BufferedReader reader =
                  new BufferedReader(new InputStreamReader(in))) {
         String line = new String();

         //unused counter that keeps tracks of which line of code we're on. May be deleted.
         int lineNumber = 1;

         String pattern = "(\\.\\w+)?(?:\\s*([\\w.]+)\\s*:)?[\\s,]*([\\w.]+)?[\\s,]*(\\$[\\w]+)?[\\s,]*([\\w\\s+\\-(]*[$]?[\\w)]+)?[\\s,]*(\\$*[\\w]+)?[\\s,]*(?:#(.*))?";
         //Regex101 version: (\.\w+)?(?:\s*([\w.]+)\s*:)?[\s,]*([\w.]+)?[\s,]*(\$[\w]+)?[\s,]*([\w\s+\-(]*[$]?[\w)]+)?[\s,]*(\$*[\w]+)?[\s,]*(?:#(.*))?
         //Java-compatible version: (\\.\\w+)?(?:\\s*([\\w.]+)\\s*:)?[\\s,]*([\\w.]+)?[\\s,]*(\\$[\\w]+)?[\\s,]*([\\w\\s+\\-(]*[$]?[\\w)]+)?[\\s,]*(\\$*[\\w]+)?[\\s,]*(?:#(.*))?

         Pattern checkRegex = Pattern.compile(pattern);

         //Read line by line and break up code into its components. Stop reading at ".data"
         while ((line = reader.readLine()) != null && !line.equals( ".data")) {   
            if(!line.trim().isEmpty()){
               //reset strings
               label = "";
               cmd = "";
               arg1 = "";
               arg2 = "";
               arg3 = "";
               comment = "";
               match = checkRegex.matcher(line);

               if(match.find()) {
                  label = match.group(2);
                  cmd = match.group(3);
                  arg1 = match.group(4);
                  arg2 = match.group(5);
                  arg3 = match.group(6);
                  comment = match.group(7);

                  createAndAddLineOfCode();
               }
            }
            lineNumber++;
         }
         list.printArrayList();

      } catch (IOException x) {
         System.err.println(x);
      }
   }

   public File getFile() {
      return asmFile;
   }

   public Path getPath() {
      return asmFilePath;
   }

   private static void createAndAddLineOfCode() {
      lineOfCode = new Instruction(label, cmd, arg1, arg2, arg3, comment);
      list.addInstruction(lineOfCode);
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

   public static void main(String [] args) {
      FileReader.setLookAndFeel();
      FileReader.openFileChooser();
   }
}
