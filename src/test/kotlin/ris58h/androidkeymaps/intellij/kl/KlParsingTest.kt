package ris58h.androidkeymaps.intellij.kl

import com.intellij.testFramework.ParsingTestCase

class KlParsingTest : ParsingTestCase("", "kl", true, KlParserDefinition()) {
    override fun getTestDataPath() = "src/test/testData/kl"
    private fun doFileTest() {
        doTest(true)
    }

    fun testAxis() {
        doFileTest()
    }

    fun testKey() {
        doFileTest()
    }

    fun testLed() {
        doFileTest()
    }

    fun testSensor() {
        doFileTest()
    }
}