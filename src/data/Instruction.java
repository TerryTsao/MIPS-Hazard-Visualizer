package data;

public class Instruction
{
   private String label, cmd, arg1, arg2, arg3, comment;

   public Instruction() {
      this.label = "";
      this.cmd = "";
      this.arg1 = "";
      this.arg2 = "";
      this.arg3 = "";
      this.comment = "";
   }

   public Instruction(String label, String cmd, String arg1, String arg2, String arg3, 
         String comment) {
      this.label = label;
      this.cmd = cmd;
      this.arg1 = arg1;
      this.arg2 = arg2;
      this.arg3 = arg3;
      this.comment = comment;
   }

   public String getLabel() {
      return label;
   }

   public String getCmd() {
      return cmd;
   }

   public String getArg1() {
      return arg1;
   }

   public String getArg2() {
      return arg2;
   }

   public String getArg3() {
      return arg3;
   }

   public String getComment() {
      return comment;
   }

   public String getInstruction() {
      return (cmd != "" ? cmd + " " : "")
            + (arg1 != "" ? arg1 + " " : "")
            + (arg2 != "" ? arg2 + " " : "")
            + (arg3 != "" ? arg3 + " " : "");
   }

   public String getFullInstruction() {
      return (label != null ? label + ": ": "")
            + (cmd != null ? cmd + " " : "")
            + (arg1 != null ? arg1 + " " : "")
            + (arg2 != null ? arg2 + " " : "")
            + (arg3 != null ? arg3 + " " : "")
            + (comment != null ? "#" + comment : "");
   }
 
   public String[] getInputReg() {
      String[] input;
      switch (cmd) {
      case "beq":
      case "bne":
      case "sw":
         input = new String[1];
         input[0] = arg1;
         break;
      default:
         input = new String[2];
         input[0] = arg2;
         input[1] = arg3;
      }
      return input;
   }
   
   public String getOutputReg() {
      String output;
      switch (cmd) {
      case "beq":
      case "bne":
      case "lw":
      case "sw":
         output = null;
         break;
      default:
         output = arg1;
      }
      return output;
   }
}
