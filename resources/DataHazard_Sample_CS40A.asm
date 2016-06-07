#test file for Hazard Detection Visualizer Program for CS 40A

.text

testLabel:

add $t1, $t2, $t3 # hello bob
add $t4, $t1, $t5
sub $t6, $t1, $t6 #a wild comment appears!!!!

add $t4, $t2, $t2

mul $t1, $t5, $t6

add $t7, $t4, $t1

lw $t3 0($t1)

add $t1, $t3, $t2

li $t1, 30

syscall

syscall

.data
MatrixA: .space 64
MatrixB: .space 64
MatrixC: .space 64

Prompt_1: .asciiz "\n\nInput row 1: "
Prompt_2: .asciiz "\nInput row 2: "
Prompt_3: .asciiz "\nInput row 3: "
Prompt_4: .asciiz "\nInput row 4: "

OutputRow_1: .asciiz "\n\n\nRow 1: "
OutputRow_2: .asciiz "\nRow 2: "
OutputRow_3: .asciiz "\nRow 3: "
OutputRow_4: .asciiz "\nRow 4: "

Space: .asciiz " "
Answer: .space 8 #allocate 8 bytes space for string to be stored