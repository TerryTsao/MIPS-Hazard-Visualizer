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

   public static void clearList() {
      list.clear();
   }

   public static int getProgramCounter() {
      return programCounter;
   }

   public static void shiftProgramCounter(int num) {
      if (programCounter + num >= 0 && programCounter + num < list.size())
         programCounter += num;
   }

   // prints interpreted file
   public static void printList() {
      System.out.println("******** Start of file ********");
      for(Instruction i: list) {
         System.out.println(i.getFullInstruction());
      }
      System.out.println("******** End of file ********");

   }

   public static ArrayList<Instruction> getList() {
      return list;
   }
}
