package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class PrintRegister (val i : Int) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        println("Register $i: " + ctx.getRegister(i).value)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PrintRegister) return false

        if (i != other.i) return false

        return true
    }

    override fun hashCode(): Int {
        return i
    }

}