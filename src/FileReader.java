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
import java.util.regex.*;




public class FileReader
{
   static File asmFile;
   static Path asmFilePath;


   public FileReader(){

   }

   public static void openFileChooser() {
      JFileChooser chooser = new JFileChooser();
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
      String cmd = "";
      String registers = "";
      String reg1 = "";
      String reg2 = "";
      String reg3 = "";
      String label = "";
      String comment = "";
      String inLineLabel = "";

      try (InputStream in = Files.newInputStream(file);
            BufferedReader reader =
                  new BufferedReader(new InputStreamReader(in))) {
         String line = new String();
         int lineNumber = 1;
         
         //break line info command and registers
         while ((line = reader.readLine()) != null) {   
            if(!line.trim().isEmpty()){

               cmd = "";
               registers = "";
               reg1 = "";
               reg2 = "";
               reg3 = "";
               label = "";
               comment = "";
               inLineLabel = "";

               System.out.println(line);
               
               cmd = regexChecker("^[a-z]+((?=\n)|(?= ))", line);
               System.out.println(lineNumber + " Cmd: " + cmd);

               registers = regexChecker("\\$[a-z][0-9]", line);
               System.out.println(lineNumber + " Registers: " + registers);

               label = regexChecker("[a-zA-Z0-9]+:", line);
               System.out.println(lineNumber + " Label: " + label);

               comment = regexChecker("#.*", line);
               System.out.println(lineNumber + " Comment: " + comment);

               inLineLabel = regexChecker("[a-zA-Z0-9]*(?=\\()", line);
               System.out.println(lineNumber + " inLineLabel: " + inLineLabel + "\n\n");

               

               if(registers.length() >= 4){
                  reg1 = registers.substring(0, 2);
                  reg2 = registers.substring(2, 4);
                  if(registers.length() > 4) {
                     reg3 = registers.substring(4, 6);
                  }
               }
               
//               System.out.println("Label: " + label);
//               System.out.print("Cmd, reg1, inLineLabel, reg2, reg3: " + cmd + "  " + reg1 + " " + 
//                     inLineLabel + " "+ reg2 + " " + reg3 + " " + comment);                  
//               System.out.println();
               
               
            }
            lineNumber++;
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


   public static String regexChecker(String theRegex, String str2Check){

      Pattern checkRegex = Pattern.compile(theRegex);

      Matcher regexMatcher = checkRegex.matcher(str2Check);
      String returnString = "";
      while ( regexMatcher.find() ){
         if (regexMatcher.group().length() != 0){
            returnString += regexMatcher.group();
         }
      }

      return returnString;
   }



   public static void main(String [] args)
   {

      FileReader.setLookAndFeel();
      FileReader.openFileChooser();





   }






}
