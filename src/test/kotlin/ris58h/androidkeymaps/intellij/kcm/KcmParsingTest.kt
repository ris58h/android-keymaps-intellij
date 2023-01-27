package ris58h.androidkeymaps.intellij.kcm

import com.intellij.testFramework.ParsingTestCase

class KcmParsingTest : ParsingTestCase("", "kcm", true, KcmParserDefinition()) {
    override fun getTestDataPath() = "src/test/testData/kcm"
    private fun doFileTest() {
        doTest(true)
    }

    fun testType() {
        doFileTest()
    }

    fun testMap() {
        doFileTest()
    }

    fun testKey() {
        doFileTest()
    }
}