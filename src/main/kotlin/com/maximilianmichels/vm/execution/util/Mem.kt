package com.maximilianmichels.vm.execution.util

import java.io.InputStream

fun readByte(inputStream: InputStream) : Int {
    val byte = inputStream.read()
    if (byte == -1) {
        throw IllegalStateException("Couldn't load byte")
    }
    return byte
}

fun readInt(inputStream : InputStream) : Int {
    val bytes = ByteArray(2)
    if (inputStream.read(bytes) != 2) {
        throw IllegalStateException("Couldn't load int")
    }

    return bytes[1].toInt() or (bytes[0].toInt() shl 16 and 0x00FF)
}
