package ris58h.androidkeymaps.intellij.idc

import org.junit.Assert
import org.junit.Test

class IdcLexerAdapterTest {
    @Test
    fun testSingleLineWithoutSpaces() {
        doTest(
            "xxx=yyy",
            arrayOf(
                "IdcTokenType.KEY", "xxx",
                "IdcTokenType.SEPARATOR", "=",
                "IdcTokenType.VALUE", "yyy",
            )
        )
    }

    @Test
    fun testMultipleLinesWithoutSpaces() {
        doTest(
            """
                xxx=yyy
                aaa=bbb
            """.trimIndent(),
            arrayOf(
                "IdcTokenType.KEY", "xxx",
                "IdcTokenType.SEPARATOR", "=",
                "IdcTokenType.VALUE", "yyy",
                "WHITE_SPACE", "\n",
                "IdcTokenType.KEY", "aaa",
                "IdcTokenType.SEPARATOR", "=",
                "IdcTokenType.VALUE", "bbb",
            )
        )
    }

    @Test
    fun testSingleLineWithSpaces() {
        doTest(
            "xxx = yyy",
            arrayOf(
                "IdcTokenType.KEY", "xxx",
                "WHITE_SPACE", " ",
                "IdcTokenType.SEPARATOR", "=",
                "WHITE_SPACE", " ",
                "IdcTokenType.VALUE", "yyy",
            )
        )
    }

    @Test
    fun testMultipleLinesWithSpaces() {
        doTest(
            """
                xxx = yyy
                aaa = bbb
            """.trimIndent(),
            arrayOf(
                "IdcTokenType.KEY", "xxx",
                "WHITE_SPACE", " ",
                "IdcTokenType.SEPARATOR", "=",
                "WHITE_SPACE", " ",
                "IdcTokenType.VALUE", "yyy",
                "WHITE_SPACE", "\n",
                "IdcTokenType.KEY", "aaa",
                "WHITE_SPACE", " ",
                "IdcTokenType.SEPARATOR", "=",
                "WHITE_SPACE", " ",
                "IdcTokenType.VALUE", "bbb",
            )
        )
    }

    private fun doTest(text: String, expectedTokens: Array<String>) {
        val lexer = IdcLexerAdapter()
        lexer.start(text)
        var idx = 0
        while (lexer.tokenType != null) {
            if (idx >= expectedTokens.size) Assert.fail("Too many tokens")
            val expectedTokenType = expectedTokens[idx++]
            val expectedTokenText = expectedTokens[idx++]
            val tokenType = lexer.tokenType.toString()
            val tokenText = lexer.bufferSequence.subSequence(lexer.tokenStart, lexer.tokenEnd).toString()
            Assert.assertEquals(tokenText, expectedTokenType, tokenType)
            Assert.assertEquals("Token type: $expectedTokenType", expectedTokenText, tokenText)
            lexer.advance()
        }
        if (idx < expectedTokens.size) Assert.fail("Not enough tokens")
    }
}
