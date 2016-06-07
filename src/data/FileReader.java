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

   private static String cmd = "";
   private static String registers = "";
   private static String arg1 = "";
   private static String arg2 = "";
   private static String arg3 = "";
   private static String label = "";
   private static String comment = "";
   private static String inLineLabel = "";


   public FileReader(){

   }

   public static void openFileChooser() {
      JFileChooser chooser = new JFileChooser();
      int returnVal = chooser.showOpenDialog(null);
      if (returnVal == JFileChooser.APPROVE_OPTION) {
         System.out.println("You chose to open this file: " +
               chooser.getSelectedFile().getName() + "\n");
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
         String line = new String();
         
         //unused counter that keeps tracks of which line of code we're on. May be deleted.
         int lineNumber = 1;

         //Read line by line and break up code into its components. Stop reading at ".data"
         while ((line = reader.readLine()) != null && !line.equals( ".data")) {   
            if(!line.trim().isEmpty()){

               //reset strings
               cmd = "";
               registers = "";
               arg1 = "";
               arg2 = "";
               arg3 = "";
               label = "";
               comment = "";
               inLineLabel = "";

               //set strings to corresponding parts of code
               cmd = regexChecker("^[a-z]+(?:$| )", line);
               registers = regexChecker("\\$[a-z][0-9]", line);
               label = regexChecker("[a-zA-Z0-9]+:", line);
               comment = regexChecker("#.*", line);
               inLineLabel = regexChecker("[a-zA-Z0-9]*(?=\\()", line);
               
               //set individual registers
               if (registers.length() >= 3) {
                  arg1 = registers.substring(0, 3);
                  if (registers.length() >=6) {
                     arg2 = registers.substring(3, 6);
                     if (registers.length() > 6) {
                        arg3 = registers.substring(6, 9);
                     }
                  }
               }

               createAndAddLineOfCode(); 

            }
            lineNumber++;
         }
         
         System.out.println("\n\n");
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
      lineOfCode = new Instruction(label, cmd, arg1, arg2, arg3, inLineLabel, comment);
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

   public static String regexChecker(String theRegex, String str2Check) {

      Pattern checkRegex = Pattern.compile(theRegex);

      Matcher regexMatcher = checkRegex.matcher(str2Check);
      String returnString = "";
      while ( regexMatcher.find() ){
         if (regexMatcher.group().length() != 0) {
            returnString += regexMatcher.group();
         }
      }

      return returnString;
   }

   public static void main(String [] args) {
      FileReader.setLookAndFeel();
      FileReader.openFileChooser();
   }
}
