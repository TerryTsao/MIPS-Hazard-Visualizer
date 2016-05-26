package data;
import java.util.ArrayList;

public class InstructionList
{

   private ArrayList<Instruction> codeList;

   public InstructionList() {
      codeList = new ArrayList<Instruction>();

   }

   public void addInstruction(Instruction instruction) {
      codeList.add(instruction);
   }

   public Instruction getInstructionAtIndex(int index) {
      return codeList.get(index);
   }

   public int getInstructionListLength() {
      return codeList.size();
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
