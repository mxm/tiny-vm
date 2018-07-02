package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.RegisterContext

interface Instruction {

    fun exec(ctx : RegisterContext)
}