package com.maximilianmichels.vm.execution.machine

interface RegisterContext {

    fun getRegister(i : Int) : Register
}
