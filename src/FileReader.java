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
      String cmd = new String();
      String registers = new String();
      String reg1 = new String();
      String reg2 = new String();
      String reg3 = new String();

      try (InputStream in = Files.newInputStream(file);
            BufferedReader reader =
                  new BufferedReader(new InputStreamReader(in))) {
         String line = new String();

         //break line info command and registers
         while ((line = reader.readLine()) != null) {   
            if(!line.trim().isEmpty()){
               StringBuilder sbLine = new StringBuilder(line);
               
               //remove white space and commas
               int setAtIndex = 0;
               for(int charIndex = 0; charIndex < sbLine.length(); charIndex++){
                  if(!Character.isWhitespace(sbLine.charAt(charIndex)) || sbLine.charAt(charIndex) != ','){
                     sbLine.setCharAt(setAtIndex++, sbLine.charAt(charIndex));
                  }
               }
               sbLine.delete(setAtIndex, line.length());
               
               line = sbLine.toString();

//               if(line.charAt(0) != ' ' && line.charAt(0) != '#')
//               {
//                  cmd.append(line.substring(0, 4));
//
//                  //find all registers
//                  for(int i = 4; i < line.length(); ) {
//                     if(line.charAt(i) == '$') {
//                        registers.append(line.substring(i+1, i+3));
//                        i += 4;
//                     }
//                  }
//               } 
               
               
               
            }
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
   
   
   public static void regexChecker(String theRegex, String str2Check){

      Pattern checkRegex = Pattern.compile(theRegex);
      
      Matcher regexMatcher = checkRegex.matcher( str2Check );
      
      while ( regexMatcher.find() ){
         if (regexMatcher.group().length() != 0){
            
         }
      }
      
   }



   public static void main(String [] args)
   {

      FileReader.setLookAndFeel();
      FileReader.openFileChooser();





   }






}
