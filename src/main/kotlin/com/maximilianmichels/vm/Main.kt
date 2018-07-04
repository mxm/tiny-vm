package com.maximilianmichels.vm;

import com.maximilianmichels.vm.compiler.Compiler
import com.maximilianmichels.vm.execution.Decoder
import com.maximilianmichels.vm.execution.machine.DefaultExecutionContext
import com.maximilianmichels.vm.frontend.Parser
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

fun main (args : Array<String>) {
    val decoder = readFromFile(args)
    decoder.use {
        start(it)
    }
}

fun start(decoder: Decoder) {
    val executionContext = DefaultExecutionContext(3)

    var pc = 0
    while (executionContext.running) {
        val instr = decoder.readInstruction()
        println("$pc Executing ${instr.javaClass.simpleName}")
        instr.exec(executionContext)
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

    val inputStream = file.inputStream()
    val parser = Parser(inputStream)
    val compiler = Compiler(parser)

    val compiledFile = File(file.parent, file.name + ".tiny")

    compiler.use {
        it.compile(FileOutputStream(compiledFile))
    }

    return Decoder(FileInputStream(compiledFile))
}