package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.RegisterContext

class Load (val register : Int, val value : Int) : Instruction {

    override fun exec(ctx: RegisterContext) {
        ctx.getRegister(register).value = value
    }

}