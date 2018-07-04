package com.maximilianmichels.vm.execution.machine

enum class Opcode(val opCode: Int) {
    HALT(0),
    LOAD(1),
    ADD(2),
    NOOP(9),
    PRINT(42),
    PRINTREG(43)
}