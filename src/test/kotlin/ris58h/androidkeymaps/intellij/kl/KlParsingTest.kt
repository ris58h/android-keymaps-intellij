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

    fun testAxisUnexpectedEOL() {
        doFileTest()
    }

    fun testKey() {
        doFileTest()
    }

    fun testKeyUnexpectedEOL() {
        doFileTest()
    }

    fun testLed() {
        doFileTest()
    }

    fun testLedUnexpectedEOL() {
        doFileTest()
    }

    fun testRequiresKernelConfig() {
        doFileTest()
    }

    fun testRequiresKernelConfigUnexpectedEOL() {
        doFileTest()
    }

    fun testSensor() {
        doFileTest()
    }

    fun testSensorUnexpectedEOL() {
        doFileTest()
    }
}