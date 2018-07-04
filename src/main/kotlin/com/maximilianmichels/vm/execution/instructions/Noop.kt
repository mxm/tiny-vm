package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext
import com.maximilianmichels.vm.execution.machine.Opcode
import java.io.OutputStream

class Noop private constructor() : Instruction {

    companion object {

        private val value = Noop()

        fun get(): Noop {
            return value
        }
    }

    override fun exec(ctx: ExecutionContext) {

    }

    override fun serialize(out: OutputStream) {
        out.write(Opcode.NOOP.opCode)
    }
}