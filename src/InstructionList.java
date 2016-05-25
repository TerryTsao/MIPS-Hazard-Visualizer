import java.util.ArrayList;

public class InstructionList
{

   ArrayList<LineOfCode> codeList;
   
   public InstructionList() {
      codeList = new ArrayList<LineOfCode>();
      
   }
   
   public void addLineOfCode(LineOfCode lineOfCode) {
      codeList.add(lineOfCode);
   }
   
   public LineOfCode getLineOfCodeAtIndex(int index) {
      return codeList.get(index);
   }

   
}
