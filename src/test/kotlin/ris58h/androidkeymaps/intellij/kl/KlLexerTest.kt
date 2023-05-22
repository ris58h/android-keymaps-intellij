package ris58h.androidkeymaps.intellij.kl

import com.intellij.lexer.Lexer
import com.intellij.testFramework.LexerTestCase

class KlLexerTest : LexerTestCase() {
    override fun createLexer(): Lexer = KlLexerAdapter()
    override fun getDirPath(): String = "src/test/testData/kl"
    override fun getPathToTestDataFile(extension: String): String = getDirPath() + "/" + getTestName(true) + extension
    override fun getExpectedFileExtension(): String = ".tokens.txt"
    private fun doFileTest() = doFileTest("kl")

    fun testAxis() = doFileTest()

    fun testAxisUnexpectedEOL() = doFileTest()

    fun testKey() = doFileTest()

    fun testKeyUnexpectedEOL() = doFileTest()

    fun testLed() = doFileTest()

    fun testLedUnexpectedEOL() = doFileTest()

    fun testRequiresKernelConfig() = doFileTest()

    fun testRequiresKernelConfigUnexpectedEOL() = doFileTest()

    fun testSensor() = doFileTest()

    fun testSensorUnexpectedEOL() = doFileTest()
}