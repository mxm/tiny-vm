package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.RegisterContext

class Print (val string : String) : Instruction {

    override fun exec(ctx: RegisterContext) {
        println(string)
    }

}