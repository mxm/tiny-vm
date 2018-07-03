package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class Load (val register : Int, val value : Int) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        ctx.getRegister(register).value = value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Load) return false

        if (register != other.register) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = register
        result = 31 * result + value
        return result
    }

}