package ris58h.androidkeymaps.intellij.idc

import com.intellij.testFramework.EditorTestUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class IdcDuplicateKeyInspectionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData/idc"
    private fun doFileTest() {
        val testName = getTestName(true)
        myFixture.configureByFile("$testName.idc")
        EditorTestUtil.checkEditorHighlighting(myFixture, "$testDataPath/$testName.highlights.txt", null)
    }

    override fun setUp() {
        super.setUp()
        myFixture.enableInspections(IdcDuplicateKeyInspection::class.java)
    }

    fun testDuplicateKey() {
        doFileTest()
    }
}