package ris58h.androidkeymaps.intellij.kl

import com.intellij.testFramework.EditorTestUtil
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class KlDuplicateEntriesInspectionTest : BasePlatformTestCase() {
    override fun getTestDataPath(): String = "src/test/testData/kl"
    private fun doFileTest() {
        val testName = getTestName(true)
        myFixture.configureByFile("$testName.kl")
        EditorTestUtil.checkEditorHighlighting(myFixture, "$testDataPath/$testName.highlights.txt", null)
    }

    override fun setUp() {
        super.setUp()
        myFixture.enableInspections(KlDuplicateEntriesInspection::class.java)
    }

    fun testDuplicateKey() {
        doFileTest()
    }

    fun testDuplicateKeyFlag() {
        doFileTest()
    }

    fun testDuplicateAxis() {
        doFileTest()
    }

    fun testDuplicateLed() {
        doFileTest()
    }

    fun testDuplicateSensor() {
        doFileTest()
    }

    fun testDuplicateKernelConfig() {
        doFileTest()
    }
}