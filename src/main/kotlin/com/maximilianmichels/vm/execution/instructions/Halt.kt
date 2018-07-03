package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

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
}