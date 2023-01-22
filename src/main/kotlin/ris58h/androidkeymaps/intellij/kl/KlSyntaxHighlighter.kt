package ris58h.androidkeymaps.intellij.kl

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.kl.psi.KlTypes

class KlSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val NUMBER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val IDENTIFIER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val COMMENT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer = KlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == KlTypes.KEY_KEYWORD || tokenType == KlTypes.USAGE_KEYWORD) {
            return KEYWORD_KEYS
        }
        if (tokenType == KlTypes.NUMBER) {
            return NUMBER_KEYS
        }
        if (tokenType == KlTypes.IDENTIFIER) {
            return IDENTIFIER_KEYS
        }
        if (tokenType == KlTypes.COMMENT) {
            return COMMENT_KEYS
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return BAD_CHAR_KEYS
        }
        return EMPTY_KEYS
    }
}
