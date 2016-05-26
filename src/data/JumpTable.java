package data;

import java.util.HashMap;

/**
 * Stores the line index of each label to support jumping.
 */
public class JumpTable {
   private HashMap<String, Integer> labels;
   
   public JumpTable() {
      labels = new HashMap<>();
   }

   /**
    * Inserts a label and its index if the label does not already exist.
    * 
    * @param label
    * @param index
    */
   public boolean insert(String label, int index) {
      if (!labels.containsKey(label)) {
         labels.put(label, index);
         return true;
      } else
         return false;
   }

   /**
    * Returns index of queried label, null if label is not found in hashmap.
    * 
    * @param label
    * @return index
    */
   public int getIndex(String label) {
      return labels.get(label);
   }
}
