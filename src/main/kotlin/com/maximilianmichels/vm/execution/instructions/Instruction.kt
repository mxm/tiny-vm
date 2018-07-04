package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext
import java.io.OutputStream

interface Instruction {

    fun exec(ctx : ExecutionContext)

    fun serialize(out : OutputStream)
}