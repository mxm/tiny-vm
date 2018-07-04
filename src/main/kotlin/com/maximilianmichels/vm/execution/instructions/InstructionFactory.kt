package com.maximilianmichels.vm.execution.instructions

import com.maximilianmichels.vm.execution.machine.Opcode
import com.maximilianmichels.vm.execution.util.readByte
import com.maximilianmichels.vm.execution.util.readInt
import java.io.InputStream

class InstructionFactory {

    fun buildInstruction(opCode: Int, inputStream: InputStream): Instruction {

        return when (opCode) {
            Opcode.HALT.opCode -> Halt.get()
            Opcode.LOAD.opCode -> createLoad(inputStream)
            Opcode.ADD.opCode -> createAdd(inputStream)
            Opcode.PRINT.opCode -> createPrint(inputStream)
            Opcode.PRINTREG.opCode -> createPrintRegister(inputStream)
            Opcode.NOOP.opCode -> Noop.get()
            else -> throw IllegalStateException("Unknown op code $opCode")
        }
    }

    private fun createLoad(inputStream: InputStream): Load {
        return Load(readByte(inputStream), readInt(inputStream))
    }

    private fun createAdd(inputStream: InputStream): Add {
        return Add(readByte(inputStream), readByte(inputStream), readByte(inputStream))
    }

    private fun createPrint(inputStream: InputStream): Print {
        val length : Int = readInt(inputStream)

        val str = ByteArray(length)
        if (inputStream.read(str) != length) {
            throw Exception("Reading string failed")
        }

        return Print(String(str, Charsets.UTF_8))
    }

    private fun createPrintRegister(inputStream: InputStream): PrintRegister {
        val i = readByte(inputStream)

        return PrintRegister(i)
    }

}