package com.maximilianmichels.vm.execution.machine

class Register {

    var value = 0

    fun set (value : Int) {
        this.value = value
    }

    fun get() : Int {
        return value
    }

}