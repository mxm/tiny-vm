package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext
import com.maximilianmichels.vm.execution.machine.Opcode
import java.io.OutputStream

class Halt private constructor() : Instruction {

    companion object {

        private val value = Halt()

        fun get(): Halt {
            return value
        }
    }

    override fun exec(ctx: ExecutionContext) {
        ctx.stop()
    }

    override fun serialize(out: OutputStream) {
        out.write(Opcode.HALT.opCode)
    }
}