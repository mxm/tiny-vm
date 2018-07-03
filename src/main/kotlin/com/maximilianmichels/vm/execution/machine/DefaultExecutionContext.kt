package com.maximilianmichels.vm.execution.machine

class DefaultExecutionContext(numRegisters : Int) : ExecutionContext {

    internal var running = true
    private val registers : MutableList<Register> = mutableListOf()

    init {
        for (i in 1..numRegisters) {
            registers.add(Register())
        }
    }

    override fun stop() {
        running = false
    }

    override fun getRegister(i : Int): Register {
        if (i >= registers.size) {
            throw IllegalStateException("Invalid register number")
        }
        return registers.get(i)
    }

}