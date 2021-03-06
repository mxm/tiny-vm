package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext
import com.maximilianmichels.vm.execution.machine.Opcode
import java.io.OutputStream

class Add(val firstReg : Int, val secondReg : Int, val targetReg : Int) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        val result = ctx.getRegister(firstReg).value + ctx.getRegister(secondReg).value
        ctx.getRegister(targetReg).value = result
    }

    override fun serialize(out: OutputStream) {
        out.write(Opcode.ADD.opCode)
        out.write(firstReg)
        out.write(secondReg)
        out.write(targetReg)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Add) return false

        if (firstReg != other.firstReg) return false
        if (secondReg != other.secondReg) return false
        if (targetReg != other.targetReg) return false

        return true
    }

    override fun hashCode(): Int {
        var result = firstReg
        result = 31 * result + secondReg
        result = 31 * result + targetReg
        return result
    }

}