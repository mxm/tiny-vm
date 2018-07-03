package com.maximilianmichels.vm.execution

import com.maximilianmichels.vm.execution.machine.Opcode
import java.io.DataOutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream

class Tester {

    private val inStream = PipedInputStream()
    private val outStream = DataOutputStream(PipedOutputStream(inStream))

    fun setupDecoder(): Decoder {
        return Decoder(inStream)
    }

    fun writeOpcode(opcode: Opcode) {
        outStream.write(opcode.opCode)
    }

    fun writeByte(byte : Int) {
        outStream.write(byte)
    }

    fun writeInt(int : Int) {
        outStream.writeInt(int)
    }

    fun writeString(str: String) {
        val bytes = str.toByteArray(Charsets.UTF_8)
        outStream.writeInt(bytes.size)
        outStream.write(bytes)
    }

}
 