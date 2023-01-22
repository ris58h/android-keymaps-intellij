package ris58h.androidkeymaps.intellij.kl

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class KlLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer = KlLexerAdapter()
    override fun getDirPath(): String = "src/test/testData/kl"
    override fun getPathToTestDataFile(extension: String): String {
        return getDirPath() + "/" + getTestName(true) + extension;
    }
    override fun getExpectedFileExtension(): String = ".tokens.txt"
    private fun doFileTest() {
        doFileTest("kl")
    }

    fun testKey() {
        doFileTest()
    }
}