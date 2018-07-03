package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.ExecutionContext

class Print (val string : String) : Instruction {

    override fun exec(ctx: ExecutionContext) {
        println(string)
    }

}