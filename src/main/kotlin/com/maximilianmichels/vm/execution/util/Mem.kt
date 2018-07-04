package com.maximilianmichels.vm.execution.util

import java.io.InputStream
import java.io.OutputStream

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

fun writeInt(value : Int, out : OutputStream) {
    out.write(value ushr 24)
    out.write(value ushr 16)
    out.write(value ushr 8)
    out.write(value ushr 0)
}

fun writeString(str: String, out: OutputStream) {
    val bytes = str.toByteArray(Charsets.UTF_8)
    writeInt(bytes.size, out)
    out.write(bytes)
}