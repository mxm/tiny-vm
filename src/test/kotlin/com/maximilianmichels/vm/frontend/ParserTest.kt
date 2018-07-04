package com.maximilianmichels.vm.frontend

import com.maximilianmichels.vm.execution.instructions.*
import com.maximilianmichels.vm.execution.machine.Opcode
import org.hamcrest.CoreMatchers
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.io.PrintStream

class ParserTest {

    @Test
    fun testNoop() {
        val parser = parserFor(Opcode.NOOP.name)
        assertThat(parser.hasNext(), `is`(true))
        assertThat(parser.next(), `is`(Noop.get() as Instruction))
    }

    @Test
    fun testHalt() {
        val parser = parserFor(Opcode.HALT.name)
        assertThat(parser.hasNext(), `is`(true))
        assertThat(parser.next(), `is`(Halt.get() as Instruction))
    }

    @Test
    fun testLoad() {
        val parser = parserFor(Opcode.LOAD.name + " 0 42")
        assertThat(parser.hasNext(), `is`(true))
        assertThat(parser.next(), `is`(Load(0, 42) as Instruction))
    }

    @Test
    fun testAdd() {
        val parser = parserFor(Opcode.ADD.name + " 0 1 2")
        assertThat(parser.hasNext(), `is`(true))
        assertThat(parser.next(), `is`(Add(0, 1, 2) as Instruction))
    }

    @Test
    fun testPrint() {
        val parser = parserFor(Opcode.PRINT.name + " hello world!")
        assertThat(parser.hasNext(), `is`(true))
        assertThat(parser.next(), `is`(Print("hello world!") as Instruction))
    }

    @Test
    fun testPrintRegister() {
        val parser = parserFor(Opcode.PRINTREG.name + " 2")
        assertThat(parser.hasNext(), `is`(true))
        assertThat(parser.next(), `is`(PrintRegister(2) as Instruction))
    }

    @Test
    fun testComments() {
        val parser = parserFor("# should be ignored")
        assertThat(parser.hasNext(), `is`(false))
    }

    @Test
    fun testMultipleStatements() {
        val sb = StringBuilder()
        sb.appendln("# sophisticated program following")
        sb.appendln("PRINT hello world!")
        sb.appendln("NOOP")
        sb.appendln("LOAD 0 128")
        sb.appendln("PRINTREG 0")
        sb.appendln("LOAD 1 2")
        sb.appendln("PRINTREG 1")
        sb.appendln("ADD 0 1 2")
        sb.appendln("PRINTREG 2")
        sb.appendln("HALT")

        val parser = parserFor(sb.toString())
        assertThat(parser, CoreMatchers.hasItems(
                Print("hello world!"),
                Noop.get(),
                Load(0, 128),
                PrintRegister(0),
                Load(1, 2),
                PrintRegister(1),
                Add(0, 1, 2),
                PrintRegister(2),
                Halt.get()
        ))
    }

    private fun parserFor(stmt : String) : Parser {
        val outStream = PipedOutputStream()
        val inStream = PipedInputStream(outStream)
        val writer = PrintStream(outStream)

        writer.print(stmt)
        outStream.close()

        return Parser(inStream)
    }
}