package data;

public class Hazard {
   public String detectBypass(Instruction iFirst, Instruction iSecond) {
      String outputReg = iFirst.getOutputReg();
      String[] inputReg = iSecond.getInputReg();
      String[] regType = {"rs", "rt"};
      int counter = 1;

      for (String reg : inputReg) {
         if (reg == outputReg)
            return regType[counter];
         counter++;
      }
      return null;
   }
}
