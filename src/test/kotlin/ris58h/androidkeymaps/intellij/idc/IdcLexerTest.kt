package ris58h.androidkeymaps.intellij.idc

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class IdcLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer = IdcLexerAdapter()
    override fun getDirPath(): String = "src/test/testData/idc"
    override fun getPathToTestDataFile(extension: String): String {
        return getDirPath() + "/" + getTestName(true) + extension;
    }
    override fun getExpectedFileExtension(): String = ".tokens.txt"
    private fun doFileTest() {
        doFileTest("idc")
    }

    fun testMissingEOL() {
        doFileTest()
    }

    fun testMissingSeparator() {
        doFileTest()
    }

    fun testMissingSeparatorAndValue() {
        doFileTest()
    }

    fun testMissingValue() {
        doFileTest()
    }

    fun testSimple() {
        doFileTest()
    }
}