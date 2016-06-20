package test;

import static control.Hazard.detectBypass;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import data.Instruction;

public class TestHazard {
   Instruction i1, i2;

   @Before
   public void setup() {
      i1 = new Instruction(null, "add", "$t1", "$t3", "$t7", null);
      i2 = new Instruction(null, "sub", "$t2", "$t7", "$t1", null);
   }

   @Test
   public void testDetectBypass() {
      String result = detectBypass(i1, i2);
      assertEquals("RT", result);
   }
}
