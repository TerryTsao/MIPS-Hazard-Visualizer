package data;

import java.util.HashMap;

/**
 * Stores the line index of each label to support jumping.
 */
public class JumpTable {
   /**
    * The labels in the asm files.
    */
   private HashMap<String, Integer> labels;
   
   /**
    * Default constructor that allocate space for member object.
    */
   public JumpTable() {
      labels = new HashMap<>();
   }

   /**
    * Inserts a label and its index if the label does not already exist.
    * 
    * @param label
    *           The label to insert.
    * @param index
    *           The index of the label.
    * @return true if label doesn't exist; false otherwise.
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
    *           The label for query.
    * @return index The index of the labeled line.
    */
   public int getIndex(String label) {
      return labels.get(label);
   }
}
