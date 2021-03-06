package com.maximilianmichels.vm.execution

import com.maximilianmichels.vm.execution.instructions.Instruction
import com.maximilianmichels.vm.execution.instructions.InstructionFactory
import java.io.Closeable
import java.io.InputStream

class Decoder (private val inputStream: InputStream) : Closeable {

    private val factory : InstructionFactory = InstructionFactory()

    fun readInstruction() : Instruction {

        val nextByte = inputStream.read()
        if (nextByte == -1) {
            throw IllegalStateException("EOF seen before HALT")
        }

        return factory.buildInstruction(nextByte, inputStream)
    }

    override fun close() {
        inputStream.close()
    }
}
