package com.maximilianmichels.vm.execution.machine

class DefaultRegisterContext(numRegisters : Int) : RegisterContext {

    private val registers : MutableList<Register> = mutableListOf()

    init {
        for (i in 1..numRegisters) {
            registers.add(Register())
        }
    }

    override fun getRegister(i : Int): Register {
        if (i >= registers.size) {
            throw IllegalStateException("Invalid register number")
        }
        return registers.get(i)
    }

}