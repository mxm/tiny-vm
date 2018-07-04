tiny-vm
=======

tiny-vm is an attempt to create a virtual machine similar to the Java Virtual
Machine (JVM). Like the JVM, it doesn't run machine code but a very minimalist
assembly language.

Operations
----------

The language supports the following operations:


| INSTR  | Description |
| ------ | ----------- |
| `HALT` | Halts the machine |
| `NOOP` | Does nothing |
| `LOAD $1 $2` | Loads $2 into register $1 |
| `ADD $1 $2 $3`  | Loads the result of registers $1 + $2 into register $3 |
| `PRINTREG $1` | Prints register $1 |
| `PRINT $1 $2` | Prints a string with length $1 and UTF-8 encoded contents $2 |

Frontend
--------

The `Main` class accepts a file as the first argument which it will attempt to
read the above instructions from. It will then compile these to the above binary
format. The following would be a valid program:

```
# just a simple program
PRINT hello world!
NOOP
LOAD 0 128
PRINTREG 0
LOAD 1 2
PRINTREG 1
ADD 0 1 2
PRINTREG 2
HALT
```

Intermediate Code
-----------------

During intermediate code generation, the code is written in a file with the
`.tiny` extension. The instructions are encoded as follows:

| INSTR  | FORMAT | Description |
| ------ | -------| ----------- |
| `HALT` | byte 0 | Halts the machine |
| `LOAD` | byte 1, byte $1, int $2 | Loads $2 into register $1 |
| `ADD`  | byte 2, byte $1, byte $2, byte $3 | Loads the result of $1 + $2 into $3 |
| `NOOP` | byte 9 | Does nothing |
| `PRINTREG` | byte 42, byte $1 | Prints register $1 |
| `PRINT` | int $1, byte[] $2 | Prints a string with length $1 and UTF-8 encoded contents $2 |

Execution
---------

Afterwards it is run. Instructions from the intermediate code are executed one by
one.

The output of the above program will be:

```
0 Executing Print
  "hello world!"
1 Executing Noop
2 Executing Load
3 Executing PrintRegister
  Register 0: 128
4 Executing Load
5 Executing PrintRegister
  Register 1: 2
6 Executing Add
7 Executing PrintRegister
  Register 2: 130
8 Executing Halt
```


Building
--------

To build and run tests, execute `gradle build`.
