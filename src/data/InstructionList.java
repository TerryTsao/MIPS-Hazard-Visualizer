package data;
import java.util.ArrayList;

public class InstructionList
{
   private static ArrayList<Instruction> list;
   private static int programCounter;

   static {
      list = new ArrayList<Instruction>();
      programCounter = 0;
   }

   public static void addInstruction(Instruction instruction) {
      list.add(instruction);
   }

   public static String getInstructionAtIndex(int index) {
      return list.get(index).getInstruction();
   }

   public static int getInstructionListLength() {
      return list.size();
   }

   public static int getProgramCounter() {
      return programCounter;
   }

   public static void shiftProgramCounter(int num) {
      if (programCounter + num >= 0 && programCounter + num < list.size())
         programCounter += num;
   }

   //prints each line of code
   public static void printArrayList() {
      System.out.println("*******Start of file: ********\n");
      for(Instruction x: list) {
         if(x.getLabel() != null)
            System.out.println(x.getLabel() + ":");
         if(x.getCmd() != null)
            System.out.print(" " + x.getCmd());
         if(x.getArg1() != null)
            System.out.print(" " + x.getArg1());
         if(x.getArg2() != null)
            System.out.print(" " + x.getArg2());
         if(x.getArg3() != null)
            System.out.print(" " + x.getArg3());
         if(x.getComment() != null)
            System.out.print(" #" + x.getComment());
         
         System.out.println("\n");
      }
      System.out.println("*******End of file: ********");

   }
}
