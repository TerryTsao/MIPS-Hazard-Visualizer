/**
 * The hazard detection unit.
 */

package control;

import data.Instruction;

public class Hazard {
   /**
    * Detects the need for forwarding/bypassing.
    * 
    * @param iFirst
    *           The first instruction.
    * @param iSecond
    *           The second instruction.
    * @return null if no bypass needed; RS or RT when bypass is necessary.
    */
   public static String detectBypass(Instruction iFirst, Instruction iSecond) {
      if (iFirst == null || iSecond == null)
         return null;
      String outputReg = iFirst.getOutputReg();
      String[] inputReg = iSecond.getInputReg();
      String[] regType = {"RS", "RT"};
      int counter = 0;

      for (String reg : inputReg) {
         if (reg.equals(outputReg))
            return regType[counter];
         counter++;
      }
      return null;
   }

   /**
    * Detects the need for a stall.
    * 
    * @param i
    *           The instruction to detect.
    * @return 0 if no need to stall; 1 if it's load-use hazard; 2 if it's branch
    *         hazard.
    */
   public static int detectStall(Instruction i) {
      if (i == null || i.getCmd() == null)
         return 0;
      switch (i.getCmd()) {
      case "lw":
         return 1;
      case "beq":
      case "bne":
         return 2;
      default:
         return 0;
      }
   }
}
