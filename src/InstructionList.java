import java.util.ArrayList;

public class InstructionList
{

   private ArrayList<LineOfCode> codeList;

   public InstructionList() {
      codeList = new ArrayList<LineOfCode>();

   }

   public void addLineOfCode(LineOfCode lineOfCode) {
      codeList.add(lineOfCode);
   }

   public LineOfCode getLineOfCodeAtIndex(int index) {
      return codeList.get(index);
   }

   public int getInstructionListLength() {
      return codeList.size();
   }

   public void printArrayList() {
      for(LineOfCode x: codeList) {
         if(!x.getCmd().isEmpty() || !x.getComment().isEmpty()) {
            System.out.println(
                  x.getCmd() + " " +
                        x.getReg1() + " " +
                        x.getReg2() + " " +
                        x.getReg3() + " " +
                        x.getComment() + "\n");
         }

      }
   }


}
