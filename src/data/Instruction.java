package data;

public class Instruction
{

   private String label, cmd, arg1, arg2, arg3, inLineLabel, comment = "";

   public Instruction() {
      this.label = "";
      this.cmd = "";
      this.arg1 = "";
      this.arg2 = "";
      this.arg3 = "";
      this.inLineLabel = "";
      this.comment = "";
   }

   public Instruction(String label, String cmd, String arg1, String arg2, String arg3, 
         String inLineLabel, String comment) {
      this.label = label;
      this.cmd = cmd;
      this.arg1 = arg1;
      this.arg2 = arg2;
      this.arg3 = arg3;
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
      return arg1;
   }

   public  String getReg2(){
      return arg2;
   }

   public  String getReg3(){
      return arg3;
   }

   public  String getInLineLabel(){
      return inLineLabel;
   }

   public  String getComment(){
      return comment;
   }



}
