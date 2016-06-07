package data;
import java.util.ArrayList;

public class InstructionList
{

   private ArrayList<Instruction> codeList;
   private int programCounter;

   public InstructionList() {
      codeList = new ArrayList<Instruction>();
      programCounter = 0;
   }

   public void addInstruction(Instruction instruction) {
      codeList.add(instruction);
   }

   public String getInstructionAtIndex(int index) {
      return codeList.get(index).getInstructionString();
   }

   public int getInstructionListLength() {
      return codeList.size();
   }

   public int getProgramCounter() {
      return programCounter;
   }

   //prints out comments, command, and registers of each line of code
   public void printArrayList() {
      for(Instruction x: codeList) {
         if(!x.getCmd().isEmpty() || !x.getComment().isEmpty()) {
            System.out.println(
                  x.getCmd() + " " +
                        x.getArg1() + " " +
                        x.getArg2() + " " +
                        x.getArg3() + " " +
                        x.getComment() + "\n");
         }
      }
   }
}
