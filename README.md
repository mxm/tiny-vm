tiny-vm
=======

tiny-vm is an attempt to create a virtual machine similar to the Java Virtual
Machine (JVM). Like the JVM it doesn't run machine code but a very minimalist
assembly language.

Operations
----------

The language supports the following operations:
- HALT => Halts the machine
- LOAD(byte $1, int $2) => Loads $2 into register $1
- ADD(byte $1, byte $2, byte $3) => Loads the result of $1 + $2 into $3
- PRINT(byte $1) => prints register $1
- PRINTSTRING(int $1, byte[] $2) => prints a string with length $1 and UTF-8
  encoded contents $2

Frontend
--------

There is no frontend yet. So you'll have to write your input file in binary.
