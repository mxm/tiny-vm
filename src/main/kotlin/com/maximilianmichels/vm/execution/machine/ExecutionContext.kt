package com.maximilianmichels.vm.execution.machine

interface ExecutionContext {

    fun getRegister(i : Int) : Register

    fun stop()
}
