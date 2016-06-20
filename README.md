# MIPS Hazard Visualizer
##### team01-project02

Brief description of submitted files:

###### Control

src/control/Controller.java
   - loads 5 instructions and checks where bypass/stalling is needed
   - and checks where forwarding is needed

src/control/Hazard.java
   - Code for detecting bypass
   - Code for detecting stalls
   
---

###### Data

src/data/FielReader.java
   - Reads asm file line by line and loads instruction list with instructions.

src/data/Instruction.java
   - Defines an instruction / line of code
   - Instruction can hold labels, commands, registers and/or comments.

src/data/InstructionList.java
   - Defines the list of all instructions read from file

src/data/JumpTable.java
   - Holds program counter values to be used when jumping
   - This will be integrated into program in future releases

---


###### GUI

src/gui/Arrow.java
   - Defines arrows of forwarding
   - Based on specific inputs, it draws arrows in right place
   
src/gui/Bubble.java
   - Defines bubbles of stalls
   - Based on level, it draws bubble in right place
   
src/gui/ControlPanel.java
   - Defines panel with buttons of "prev", "next", "view", and "load"
   
src/gui/GUIGlobal.java
   - Constants that are used thoughout the program
   
src/gui/InstructionPanel.java
   - Draws instruction in panel to the left of pipeline diagram panel (Main Panel).
   
src/gui/Main.java
   - Loads panels and initiates program.
   
src/gui/MainPanel.java
   - Panel that holds pipeline diagrams, arrows, and bubbles.
   
src/gui/PipelineDiagram.java
   - A set of 5 processor diagrams, drawn in a pipelined fashion.
   
src/gui/ProcessorDiagram.java
   - A single, simplified view of a MIPS processor, with its 5 sections: IF, ID, EX, MEM, WB.
   - IF: Instruction Fetch
   - ID: Instruction Decode
   - EX: Execution of ALU operations
   - MEM: Memory Access
   - WB: Write Back

---

###### Resources

resources/Bubble.png
   - Picture of a single bubble/stall symbol.

resources/default.asm
   - Default .asm (assembly) file that gets loaded
   - Contains instructions that require forwarding and stalls

resources/MVC\ Diagram.png
   - Model-View-Controller Diagram of program.

![Running screenshot](resources/Run.png?token=AOcnylhWOPnpVm-f-vvbURAQQdPoII2Zks5XQdl2wA%3D%3D)

resources/Screen\ Recording.mp4
   - Video recording of gameplay.

resources/Pipeline.png
   - Image of single, simplified MIPS Processor Diagram

resources/UML Diagram.png
   - UML Diagram of significant classes in MIPS Hazard Visualizer.

![UML Diagram](resources/UML%20Class%20Diagram.png?token=AOcnyknBODC1WY1AVeUr4kD-F2POimQJks5XQdmgwA%3D%3D)

---

   
Project1Proposal.md
   - Project proposal of MIPS Hazard Visualizer.

README.md
   - Overview of all submitted files.
