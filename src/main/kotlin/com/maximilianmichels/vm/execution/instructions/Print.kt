package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext
import com.maximilianmichels.vm.execution.machine.Opcode
import com.maximilianmichels.vm.execution.util.writeString
import java.io.OutputStream

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

    override fun serialize(out: OutputStream) {
        out.write(Opcode.PRINT.opCode)
        writeString(string, out)
    }
}