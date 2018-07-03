package com.maximilianmichels.vm.execution;

import com.maximilianmichels.vm.execution.instructions.*
import com.maximilianmichels.vm.execution.machine.Opcode
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.io.DataOutputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream

class DecoderTest {

    private val tester = Tester()

    @Test
    fun testNoop() {
        val decoder = tester.setupDecoder()
        tester.writeOpcode(Opcode.NOOP)
        assertThat(decoder.readInstruction(), `is`(Noop.get() as Instruction))
    }

    @Test
    fun testLoad() {
        val decoder = tester.setupDecoder()
        val registerNo = 0
        val value = 127
        tester.writeOpcode(Opcode.LOAD)
        tester.writeByte(registerNo)
        tester.writeInt(127)
        assertThat(decoder.readInstruction(), `is`(Load(registerNo, value) as Instruction))
    }

    @Test
    fun testLoadWithMultiByte() {
        val decoder = tester.setupDecoder()
        val registerNo = 1
        val value = 128
        tester.writeOpcode(Opcode.LOAD)
        tester.writeByte(registerNo)
        tester.writeInt(value)
        assertThat(decoder.readInstruction(), `is`(Load(registerNo, value) as Instruction))
    }

    @Test
    fun testAdd() {
        val decoder = tester.setupDecoder()
        val register1 = 2
        val register2 = 5
        val registerTarget = 1
        tester.writeOpcode(Opcode.ADD)
        tester.writeByte(register1)
        tester.writeByte(register2)
        tester.writeByte(registerTarget)
        assertThat(decoder.readInstruction(), `is`(Add(register1, register2, registerTarget) as Instruction))
    }

    @Test
    fun testPrint() {
        val decoder = tester.setupDecoder()
        val string = "hello world!"
        tester.writeOpcode(Opcode.PRINT)
        tester.writeString(string)
        assertThat(decoder.readInstruction(), `is`(Print(string) as Instruction))
    }

    @Test
    fun testPrintRegister() {
        val decoder = tester.setupDecoder()
        tester.writeOpcode(Opcode.PRINTREGISTER)
        tester.writeByte(1)
        assertThat(decoder.readInstruction(), `is`(PrintRegister(1) as Instruction))
    }

    @Test
    fun testHalt() {
        val decoder = tester.setupDecoder()
        tester.writeOpcode(Opcode.HALT)
        assertThat(decoder.readInstruction(), `is`(Halt.get() as Instruction))
    }

}
