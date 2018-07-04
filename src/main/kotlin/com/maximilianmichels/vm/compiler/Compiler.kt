package com.maximilianmichels.vm.compiler

import com.maximilianmichels.vm.frontend.Parser
import java.io.Closeable
import java.io.OutputStream

class Compiler(val parser : Parser) : Closeable {

    fun compile(out: OutputStream) {
        out.use {
            for (instr in parser) {
                instr.serialize(it)
            }
        }
    }

    override fun close() {
        parser.close()
    }
}
 