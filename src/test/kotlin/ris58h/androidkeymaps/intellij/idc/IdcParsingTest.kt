package ris58h.androidkeymaps.intellij.idc

import com.intellij.testFramework.ParsingTestCase

class IdcParsingTest : ParsingTestCase("", "idc", true, IdcParserDefinition()) {
    override fun getTestDataPath() = "src/test/testData/idc"
    private fun doFileTest() {
        doTest(true)
    }

    fun testHashes() {
        doFileTest()
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