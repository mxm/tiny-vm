package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

interface Instruction {

    fun exec(ctx : ExecutionContext)
}