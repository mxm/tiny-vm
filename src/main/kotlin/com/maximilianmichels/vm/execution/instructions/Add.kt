package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.RegisterContext

class Add(val firstReg : Int, val secondReg : Int, val targetReg : Int) : Instruction {

    override fun exec(ctx: RegisterContext) {
        val result = ctx.getRegister(firstReg).value + ctx.getRegister(secondReg).value
        ctx.getRegister(targetReg).value = result
    }

}