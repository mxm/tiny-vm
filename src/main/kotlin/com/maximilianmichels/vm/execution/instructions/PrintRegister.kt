package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.RegisterContext

class PrintRegister (val i : Int) : Instruction {

    override fun exec(ctx: RegisterContext) {
        println("Register $i: " + ctx.getRegister(i).value)
    }

}