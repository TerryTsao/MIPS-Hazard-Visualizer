package control;

import data.Instruction;

public class Hazard {
   /**
    * Detects the need for forwarding/bypassing.
    * @param iFirst
    * @param iSecond
    * @return
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
    * @param i
    * @return
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
