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
        val LABEL: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_LABEL", DefaultLanguageHighlighterColors.STRING)
        val COMMENT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val LABEL_KEYS = arrayOf(LABEL)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()

        private val KEYWORD_TYPES = setOf(
            KlTypes.KEY_KEYWORD,
            KlTypes.USAGE_KEYWORD,
            KlTypes.AXIS_KEYWORD,
            KlTypes.INVERT_KEYWORD,
            KlTypes.SPLIT_KEYWORD,
            KlTypes.FLAT_KEYWORD,
            KlTypes.LED_KEYWORD,
            KlTypes.SENSOR_KEYWORD,
            KlTypes.RKC_KEYWORD,
        )
    }

    override fun getHighlightingLexer(): Lexer = KlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (KEYWORD_TYPES.contains(tokenType)) {
            return KEYWORD_KEYS
        }
        if (tokenType == KlTypes.NUMBER) {
            return NUMBER_KEYS
        }
        if (tokenType == KlTypes.LABEL) {
            return LABEL_KEYS
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
