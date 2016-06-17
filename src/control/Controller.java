package control;

import data.Instruction;
import data.InstructionList;
import gui.GUIGlobal;
import gui.PipelineDiagram;

public class Controller {
   public static void update(PipelineDiagram pipe) {
      Instruction[] instructions = new Instruction[GUIGlobal.NUM_OF_LINES];
      int pc = InstructionList.getProgramCounter();
      // populate array of 5 instructions
      for (int i = 0; i < GUIGlobal.NUM_OF_LINES; i++) {
         if (pc + i >= 0 && pc + i < InstructionList.getSize())
            instructions[i] = InstructionList.getList().get(pc + i);
      }
      // check for stalls
      for (int i = 0; i < instructions.length; i++) {
         int stall = Hazard.detectStall(instructions[i]);
         if (stall > 0) {
            shiftArray(instructions, i, stall);
            pipe.setBubbleVis(i, true);
         }
         else
            pipe.setBubbleVis(i, false);
      }
      // check for forwarding
      int[] outputs = {0, 0, 1, 1};
      int[] inputs = {1, 2, 2, 3};
      for (int i = 0; i < outputs.length; i++) {
         String fwd = Hazard.detectBypass(instructions[outputs[i]],
                                          instructions[inputs[i]]);
         if (fwd != null)
            pipe.setArrowVis(i, true);
         else
            pipe.setArrowVis(i, false);
      }
   }
   
   private static void shiftArray(Object[] array, int pos, int steps) {
      for (int i = array.length - 1; i >= pos + steps; i--) {
         array[i] = array[i - steps];
         array[i - steps] =  null;
      }
   }
}
