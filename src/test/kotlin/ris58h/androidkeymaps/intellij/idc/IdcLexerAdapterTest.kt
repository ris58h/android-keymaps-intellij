package ris58h.androidkeymaps.intellij.idc

import org.junit.Assert
import org.junit.Test

class IdcLexerAdapterTest {
    @Test
    fun testSimple() {
        doTest("xxx=yyy", arrayOf(
            "IdcTokenType.KEY", "xxx",
            "IdcTokenType.SEPARATOR", "=",
            "IdcTokenType.VALUE", "yyy",
        ))
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
