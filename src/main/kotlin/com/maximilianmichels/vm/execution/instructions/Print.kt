package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class Print (val string : String) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        println(string)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Print) return false

        if (string != other.string) return false

        return true
    }

    override fun hashCode(): Int {
        return string.hashCode()
    }

}