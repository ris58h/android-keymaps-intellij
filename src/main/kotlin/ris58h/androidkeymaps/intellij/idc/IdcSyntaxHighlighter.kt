package ris58h.androidkeymaps.intellij.idc

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val SEPARATOR: TextAttributesKey =
            createTextAttributesKey("IDC_SEPARATOR", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val KEY: TextAttributesKey = createTextAttributesKey("IDC_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val VALUE: TextAttributesKey = createTextAttributesKey("IDC_VALUE", DefaultLanguageHighlighterColors.STRING)
        val COMMENT: TextAttributesKey =
            createTextAttributesKey("IDC_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER: TextAttributesKey =
            createTextAttributesKey("IDC_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
        private val KEY_KEYS = arrayOf(KEY)
        private val VALUE_KEYS = arrayOf(VALUE)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer = IdcLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == IdcTypes.SEPARATOR) {
            return SEPARATOR_KEYS
        }
        if (tokenType == IdcTypes.KEY) {
            return KEY_KEYS
        }
        if (tokenType == IdcTypes.VALUE) {
            return VALUE_KEYS
        }
        if (tokenType == IdcTypes.COMMENT) {
            return COMMENT_KEYS
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return BAD_CHAR_KEYS
        }
        return EMPTY_KEYS
    }
}
