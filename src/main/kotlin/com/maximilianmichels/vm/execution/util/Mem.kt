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
    val byte1 = inputStream.read()
    val byte2 = inputStream.read()
    val byte3 = inputStream.read()
    val byte4 = inputStream.read()
    if (byte1 or byte2 or byte3 or byte4 < 0) {
        throw IllegalStateException("Couldn't load int")
    }
    val value = (byte1 shl 24) or (byte2 shl 16) or (byte3 shl 8) or (byte4 shl 0)
    return value
}
