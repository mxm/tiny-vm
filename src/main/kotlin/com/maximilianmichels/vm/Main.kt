package com.maximilianmichels.vm;

import com.maximilianmichels.vm.execution.Decoder
import com.maximilianmichels.vm.execution.machine.DefaultExecutionContext
import java.io.File
import java.io.FileNotFoundException

fun main (args : Array<String>) {

    val decoder = readFromFile(args)

    val registerContext = DefaultExecutionContext(3)

    var pc = 0
    while (true) {
        val instr = decoder.readInstruction()
        println("$pc Executing ${instr.javaClass.simpleName}")
        instr.exec(registerContext)
        pc++
    }

}

private fun readFromFile(args : Array<String>) : Decoder {
    if (args.size != 1) {
        throw IllegalArgumentException("Only one argument allowed")
    }

    val file = File(args[0]);
    if (!file.exists()) {
        throw FileNotFoundException("$file does not exist")
    }

    return Decoder(file.inputStream())
}