package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class PrintRegister (val i : Int) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        println("Register $i: " + ctx.getRegister(i).value)
    }

}