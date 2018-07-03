package com.maximilianmichels.vm.execution;

import com.maximilianmichels.vm.execution.machine.Opcode
import com.maximilianmichels.vm.start
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class IntegrationTest {

    @Test
    fun testInMemoryProgram() {
        val decoder = setup()
        val oldOut = System.out
        val byteStream = ByteArrayOutputStream()
        try {
            val newOut = PrintStream(byteStream)
            System.setOut(newOut)
            start(decoder)
            newOut.flush()
        } finally {
            System.setOut(oldOut)
        }
        assertThat(byteStream.toString(Charsets.UTF_8.name()), `is`(
                "0 Executing Print\n" +
                "hello world!\n" +
                "1 Executing Noop\n" +
                "2 Executing Load\n" +
                "3 Executing PrintRegister\n" +
                "Register 0: 128\n" +
                "4 Executing Load\n" +
                "5 Executing PrintRegister\n" +
                "Register 1: 2\n" +
                "6 Executing Add\n" +
                "7 Executing PrintRegister\n" +
                "Register 2: 130\n" +
                "8 Executing Halt\n"))
    }

    private fun setup() : Decoder {
        val tester = Tester()

        // PRINT
        tester.writeOpcode(Opcode.PRINT)
        tester.writeString("hello world!")
        // NOOP
        tester.writeOpcode(Opcode.NOOP)
        // LOAD
        tester.writeOpcode(Opcode.LOAD)
        tester.writeByte(0)
        tester.writeInt(128)
        // PRINT REGISTER
        tester.writeOpcode(Opcode.PRINTREGISTER)
        tester.writeByte(0)
        // LOAD
        tester.writeOpcode(Opcode.LOAD)
        tester.writeByte(1)
        tester.writeInt(2)
        // PRINT REGISTER
        tester.writeOpcode(Opcode.PRINTREGISTER)
        tester.writeByte(1)
        // ADD
        tester.writeOpcode(Opcode.ADD)
        tester.writeByte(0)
        tester.writeByte(1)
        tester.writeByte(2)
        // PRINT REGISTER
        tester.writeOpcode(Opcode.PRINTREGISTER)
        tester.writeByte(2)
        // HALT
        tester.writeOpcode(Opcode.HALT)

        return tester.setupDecoder()
    }

}
