package com.maximilianmichels.vm;

import com.maximilianmichels.vm.execution.Decode
import com.maximilianmichels.vm.execution.machine.Opcode
import com.maximilianmichels.vm.execution.machine.DefaultRegisterContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException

val DEBUG = true

fun main (args : Array<String>) {

    val decoder = if (DEBUG && args.isEmpty()) {
        readFromMemory()
    } else {
        readFromFile(args)
    }

    val registerContext = DefaultRegisterContext(3)

    var pc = 0
    while (true) {
        val instr = decoder.readInstruction()
        println("$pc Executing ${instr.javaClass.simpleName}")
        instr.exec(registerContext)
        pc++
    }

}

private fun readFromFile(args : Array<String>) : Decode {
    if (args.size != 1) {
        throw IllegalArgumentException("Only one argument allowed")
    }

    val file = File(args[0]);
    if (!file.exists()) {
        throw FileNotFoundException("$file does not exist")
    }

    return Decode(file.inputStream())
}

private fun readFromMemory() : Decode {

    val baos = ByteArrayOutputStream(32)

    fun writeOpcode(opcode : Opcode) {
        baos.write(opcode.opCode)
    }

    // PRINT
    writeOpcode(Opcode.PRINT)
    writeString("hello world!", baos)
    // LOAD
    writeOpcode(Opcode.LOAD)
    baos.write(0)
    baos.write(0)
    baos.write(127)
    // PRINT REGISTER
    writeOpcode(Opcode.PRINTREGISTER)
    baos.write(0)
    // LOAD
    writeOpcode(Opcode.LOAD)
    baos.write(1)
    baos.write(0)
    baos.write(2)
    // PRINT REGISTER
    writeOpcode(Opcode.PRINTREGISTER)
    baos.write(1)
    // ADD
    writeOpcode(Opcode.ADD)
    baos.write(0)
    baos.write(1)
    baos.write(2)
    // PRINT REGISTER
    writeOpcode(Opcode.PRINTREGISTER)
    baos.write(2)
    // HALT
    writeOpcode(Opcode.HALT)

    File("current").writeBytes(baos.toByteArray())
    return Decode(ByteArrayInputStream(baos.toByteArray()))
}


private fun writeString(str : String, baos : ByteArrayOutputStream) {
    val bytes = str.toByteArray(Charsets.UTF_8)
    val length = bytes.size
    baos.write(length and 0xFF00 shr 16)
    baos.write(length and 0x00FF)
    baos.write(bytes)
}
