package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class Noop private constructor() : Instruction {

    companion object {

        private val value = Noop()

        fun get(): Noop {
            return value
        }
    }

    override fun exec(ctx: ExecutionContext) {

    }
}