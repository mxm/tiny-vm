package com.maximilianmichels.vm

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.io.*

class EndToEndTest {

    @Test
    fun testEndToEnd() {
        val programPath = copyResourceToFile("/program.txt")
        val oldOut = System.out
        val byteStream = ByteArrayOutputStream()
        val newOut = PrintStream(byteStream)
        try {
            System.setOut(newOut)
            main(arrayOf(programPath))
            newOut.flush()
        } finally {
            newOut.close()
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
                        "8 Executing Halt\n"
        ))
    }

    private fun copyResourceToFile(path : String) : String {
        val resource = javaClass.getResource(path)
        val isReader = InputStreamReader(resource.openStream())
        val temporaryFile = File.createTempFile("tiny-vm", "end-to-end")
        temporaryFile.deleteOnExit()
        val fileWriter = FileWriter(temporaryFile)

        var value = isReader.read()
        while (value != -1) {
            fileWriter.write(value)
            value = isReader.read()
        }
        fileWriter.close()
        return temporaryFile.absolutePath
    }
}
