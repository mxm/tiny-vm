package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class Load (val register : Int, val value : Int) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        ctx.getRegister(register).value = value
    }

}