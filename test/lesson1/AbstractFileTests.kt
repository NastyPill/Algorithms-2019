package lesson1

import java.io.File
import kotlin.test.assertEquals

abstract class AbstractFileTests() {
    protected fun assertFileContent(name: String, expectedContent: String) {
        val content = File(name).readLines().joinToString("\n")
        assertEquals(expectedContent.trim(), content.trim())
    }

    protected fun assertFileContent(name: String, expectedContent: List<String>) {
        var i = 0
        File(name).forEachLine { line ->
            val expectedLine = expectedContent.getOrElse(i++) { "\n" }
            assertEquals(expectedLine, line)
        }
    }

    protected fun assertFilesContent(name1: String, name2: String) {
        assertEquals(File(name1).readLines(), File(name2).readLines())
    }
}
