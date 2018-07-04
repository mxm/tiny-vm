package com.maximilianmichels.vm.frontend

import com.maximilianmichels.vm.execution.instructions.*
import com.maximilianmichels.vm.execution.machine.Opcode
import java.io.*

class Parser (inputStream: InputStream) : Iterator<Instruction>, Iterable<Instruction>, Closeable {

    private val reader = BufferedReader(InputStreamReader(inputStream))
    private var currentLine : String? = null

    override fun iterator(): Iterator<Instruction> {
        return this
    }

    override fun hasNext(): Boolean {
        currentLine = reader.readLine()
        if (currentLine == null) {
            return false;
        }
        currentLine = currentLine!!.trim()
        if (currentLine!!.startsWith("#")) {
            currentLine = null
            return hasNext()
        }
        return true;
    }

    override fun next(): Instruction {
        if (currentLine == null) {
            throw EOFException()
        }
        val words = currentLine!!.split(" ")
        val instr = words[0]
        val args = words.subList(1, words.size)
        return when (instr) {
            Opcode.NOOP.name -> Noop.get()
            Opcode.HALT.name -> Halt.get()
            Opcode.LOAD.name -> load(args)
            Opcode.ADD.name -> add(args)
            Opcode.PRINT.name -> print(args)
            Opcode.PRINTREG.name -> printRegister(args)
            else -> throw Exception("Invalid argument")
        }
    }

    override fun close() {
        reader.close()
    }

    private fun load(args : List<String>) : Load {
        if (args.size != 2) {
            throw IllegalArgumentException("Invalid number of arguments for LOAD: $args")
        }
        val registerNo = args[0].toInt()
        val value = args[1].toInt()
        return Load(registerNo, value)
    }

    private fun add(args : List<String>) : Add {
        if (args.size != 3) {
            throw IllegalArgumentException("Invalid number of arguments for ADD: $args")
        }
        val registerNo1 = args[0].toInt()
        val registerNo2 = args[1].toInt()
        val registerNo3 = args[2].toInt()
        return Add(registerNo1, registerNo2, registerNo3)
    }

    private fun print(args : List<String>) : Print {
        if (args.size == 0) {
            throw IllegalArgumentException("Invalid number of arguments for PRINT: $args")
        }
        return Print(args.joinToString(" "))
    }

    private fun printRegister(args : List<String>) : PrintRegister {
        if (args.size != 1) {
            throw IllegalArgumentException("Invalid number of arguments for PRINT: $args")
        }
        val registerNo = args[0].toInt()
        return PrintRegister(registerNo)
    }
}