tiny-vm
=======

tiny-vm is an attempt to create a virtual machine similar to the Java Virtual
Machine (JVM). Like the JVM, it doesn't run machine code but a very minimalist
assembly language.

Operations
----------

The language supports the following operations:

| INSTR  | FORMAT | Description |
| ------ | -------| ----------- |
| `HALT` | byte 0 | Halts the machine |
| `LOAD` | byte 1, byte $1, int $2 | Loads $2 into register $1 |
| `ADD`  | byte 2, byte $1, byte $2, byte $3 | Loads the result of $1 + $2 into $3 |
| `NOOP` | byte 9 | Does nothing |
| `PRINT` | byte 42, byte $1 | Prints register $1 |
| `PRINTSTR` | int $1, byte[] $2 | Prints a string with length $1 and UTF-8 encoded contents $2 |

Frontend
--------

There is no frontend yet. So you'll have to write your input file in binary
using the definitions above.
