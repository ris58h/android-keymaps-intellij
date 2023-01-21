package ris58h.androidkeymaps.intellij.idc

import com.intellij.testFramework.ParsingTestCase

class IdcParsingTest : ParsingTestCase("", "idc", true, IdcParserDefinition()) {
    override fun getTestDataPath() = "src/test/testData/idc"

    fun testSimple() {
        doTest(true)
    }

    fun testMissingValue() {
        doTest(true)
    }
}