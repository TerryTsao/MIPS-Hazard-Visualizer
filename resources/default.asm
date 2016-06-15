#test file for Hazard Detection Visualizer Program for CS 40A
.text

Start:
li $t2, 10
li $t3, 5

add $t1, $t2, $t3 # This line does stuff
add $t4, $t1, $t3 # Forwarding needed
sub $t6, $t1, $t3 # Forwarding needed again
add $t6, $t1, $t2 # No forwarding

lw $t3, 0($t1)
add $t4, $t3, $t2 #Stall and forward
sw $t4, 0($t2) #I dont think this needs forwarding

add $t6, $t3, $t2
sw $t1, 0($t6) # Might need forwarding.


.data
MatrixA: .space 64
