/**
 * A list of instructions.
 */

package data;
import java.util.ArrayList;

public class InstructionList
{
   /**
    * An ArrayList of instructions.
    */
   private static ArrayList<Instruction> list;
   /**
    * The pc program counter.
    */
   private static int programCounter;

   static {
      list = new ArrayList<Instruction>();
      programCounter = 0;
   }

   /**
    * Adds an instruction to the list.
    * 
    * @param instruction
    *           The instruction to add.
    */
   public static void addInstruction(Instruction instruction) {
      list.add(instruction);
   }

   /**
    * Returns the instruction at index as String.
    * 
    * @param index
    *           The index of instruction.
    * @return The instruction at index.
    */
   public static String getInstructionAtIndex(int index) {
      return list.get(index).getInstruction();
   }

   /**
    * Returns the length of the instruction list.
    * 
    * @return The length of the list.
    */
   public static int getInstructionListLength() {
      return list.size();
   }

   /**
    * Clear the list.
    */
   public static void clearList() {
      list.clear();
   }

   /**
    * Returns the size of the list.
    * 
    * @return The size of the list.
    */
   public static int getSize() {
      return list.size();
   }

   public static int getProgramCounter() {
      return programCounter;
   }

   /**
    * Shift pc counter by num.
    * 
    * @param num
    *           Shift amount.
    */
   public static void shiftProgramCounter(int num) {
      if (programCounter + num >= 0 && programCounter + num < list.size())
         programCounter += num;
   }

   /**
    * prints interpreted file
    */
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
