
public class LineOfCode
{
   
   String label, cmd, reg1, reg2, reg3, inLineLabel, comment = "";
   
   public LineOfCode() {
      this.label = "";
      this.cmd = "";
      this.reg1 = "";
      this.reg2 = "";
      this.reg3 = "";
      this.inLineLabel = "";
      this.comment = "";
   }
   
   public LineOfCode(String label, String cmd, String reg1, String reg2, String reg3, 
         String inLineLabel, String comment) {
      this.label = label;
      this.cmd = cmd;
      this.reg1 = reg1;
      this.reg2 = reg2;
      this.reg3 = reg3;
      this.inLineLabel = inLineLabel;
      this.comment = comment;
   }
   
   public String getLabel(){
      return label;
   }
   
   public  String getCmd(){
      return cmd;
   }
   
   public  String getReg1(){
      return reg1;
   }
   
   public  String getReg2(){
      return reg2;
   }
   
   public  String getReg3(){
      return reg3;
   }
   
   public  String getInLineLabel(){
      return inLineLabel;
   }
   
   public  String getComment(){
      return comment;
   }
   
   

}
