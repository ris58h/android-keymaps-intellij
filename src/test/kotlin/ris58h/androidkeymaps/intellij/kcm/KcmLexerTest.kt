package ris58h.androidkeymaps.intellij.kcm

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class KcmLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer = KcmLexerAdapter()
    override fun getDirPath(): String = "src/test/testData/kcm"
    override fun getPathToTestDataFile(extension: String): String = getDirPath() + "/" + getTestName(true) + extension
    override fun getExpectedFileExtension(): String = ".tokens.txt"
    private fun doFileTest() = doFileTest("kcm")

    fun testType() = doFileTest()

    fun testMap() = doFileTest()

    fun testKey() = doFileTest()

    fun testKeyCharacterLiteral() = doFileTest()
}