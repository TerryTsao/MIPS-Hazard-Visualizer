/**
 * Stores an instruction from asm files.
 */

package data;

import java.util.regex.Pattern;

public class Instruction
{
   /**
    * Each part of an instruction. Could be null.
    */
   private String label, cmd, arg1, arg2, arg3, comment;

   /**
    * Sets every member as empty String.
    */
   public Instruction() {
      this.label = "";
      this.cmd = "";
      this.arg1 = "";
      this.arg2 = "";
      this.arg3 = "";
      this.comment = "";
   }

   /**
    * Sets member according to parameter.
    * 
    * @param label
    *           The label.
    * @param cmd
    *           The command.
    * @param arg1
    *           The first argument.
    * @param arg2
    *           The second argument.
    * @param arg3
    *           The third argument.
    * @param comment
    *           The comment.
    */
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

   /**
    * Returns the instruction as a String, without label or comment.
    * 
    * @return The instruction without label or comment.
    */
   public String getInstruction() {
      return (cmd != null ? cmd + " " : "")
            + (arg1 != null ? arg1 + " " : "")
            + (arg2 != null ? arg2 + " " : "")
            + (arg3 != null ? arg3 + " " : "");
   }

   /**
    * Returns the full instruction as a String, with label and comment.
    * 
    * @return The full instruction with label and comment.
    */
   public String getFullInstruction() {
      return (label != null ? label + ": ": "")
            + (cmd != null ? cmd + " " : "")
            + (arg1 != null ? arg1 + " " : "")
            + (arg2 != null ? arg2 + " " : "")
            + (arg3 != null ? arg3 + " " : "")
            + (comment != null ? "#" + comment : "");
   }

   /**
    * Returns the input register.
    * 
    * @return The input register.
    */
   public String[] getInputReg() {
      if (cmd == null)
         return new String[0];
      String[] input;
      switch (cmd) {
      case "j":
      case "jal":
      case "jalr":
      case "jr":
      case "syscall":
         input = new String[0];
         break;
      case "beq":
      case "bne":
      case "la":
      case "li":
         input = new String[1];
         input[0] = arg1;
         break;
      case "lw":
         input = new String[1];
         input[0] = Pattern.compile("\\((\\$\\w+)\\)").matcher(arg2).group();
         break;
      case "sw":
         input = new String[2];
         input[0] = Pattern.compile("\\((\\$\\w+)\\)").matcher(arg2).group();
         input[1] = arg1;
      default:
         input = new String[2];
         input[0] = arg2;
         input[1] = arg3;
      }
      return input;
   }

   /**
    * Returns the output register.
    * 
    * @return The output register, null if doesn't exist.
    */
   public String getOutputReg() {
      if (cmd == null)
         return null;
      String output;
      switch (cmd) {
      case "beq":
      case "bne":
      case "j":
      case "jal":
      case "jalr":
      case "jr":
      case "sw":
      case "syscall":
         output = null;
         break;
      default:
         output = arg1;
      }
      return output;
   }
}
