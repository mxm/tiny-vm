package com.maximilianmichels.vm.execution.instructions;

import com.maximilianmichels.vm.execution.machine.DefaultExecutionContext
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class InstructionTest {

    @Test
    fun testNoop() {
        val executionCtx = DefaultExecutionContext(3)
        Noop.get().exec(executionCtx)
        assertThat(executionCtx.getRegister(0).value, `is`(0))
        assertThat(executionCtx.getRegister(1).value, `is`(0))
        assertThat(executionCtx.getRegister(2).value, `is`(0))
    }

    @Test
    fun testHalt() {
        val executionCtx = DefaultExecutionContext(3)
        assertThat(executionCtx.running, `is`(true))
        Halt.get().exec(executionCtx)
        assertThat(executionCtx.running, `is`(false))
    }

    @Test
    fun testLoad() {
        val executionCtx = DefaultExecutionContext(3)
        Load(0, 42).exec(executionCtx)
        assertThat(executionCtx.getRegister(0).value, `is`(42))
        assertThat(executionCtx.getRegister(1).value, `is`(0))
        assertThat(executionCtx.getRegister(2).value, `is`(0))
    }

    @Test
    fun testAdd() {
        val executionCtx = DefaultExecutionContext(3)
        Load(0, 42).exec(executionCtx)
        Load(1, 23).exec(executionCtx)
        Add(0, 1, 2).exec(executionCtx)
        assertThat(executionCtx.getRegister(0).value, `is`(42))
        assertThat(executionCtx.getRegister(1).value, `is`(23))
        assertThat(executionCtx.getRegister(2).value, `is`(65))
    }

    @Test
    fun testPrint() {
        val executionCtx = DefaultExecutionContext(3)
        val oldOut = System.out
        val baos = ByteArrayOutputStream()
        try {
            val printStream = PrintStream(baos)
            System.setOut(printStream)
            Print("hello").exec(executionCtx)
            printStream.flush()
        } finally {
            System.setOut(oldOut)
        }
        assertThat(baos.toString(Charsets.UTF_8.name()), `is`("hello\n"))
        assertThat(executionCtx.getRegister(0).value, `is`(0))
        assertThat(executionCtx.getRegister(1).value, `is`(0))
        assertThat(executionCtx.getRegister(2).value, `is`(0))
    }

    @Test
    fun testPrintRegister() {
        val executionCtx = DefaultExecutionContext(3)
        Load(0, 42).exec(executionCtx)
        val oldOut = System.out
        val baos = ByteArrayOutputStream()
        try {
            val printStream = PrintStream(baos)
            System.setOut(printStream)
            PrintRegister(0).exec(executionCtx)
            printStream.flush()
        } finally {
            System.setOut(oldOut)
        }
        assertThat(baos.toString(Charsets.UTF_8.name()), `is`("Register 0: 42\n"))
        assertThat(executionCtx.getRegister(0).value, `is`(42))
        assertThat(executionCtx.getRegister(1).value, `is`(0))
        assertThat(executionCtx.getRegister(2).value, `is`(0))
    }
}
