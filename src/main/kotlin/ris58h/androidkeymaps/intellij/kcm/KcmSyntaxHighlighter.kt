package ris58h.androidkeymaps.intellij.kcm

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypes

class KcmSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
            KcmSyntaxHighlighter()
    }

    companion object {
        val KEYWORD: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val NUMBER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val LABEL: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_LABEL", DefaultLanguageHighlighterColors.STRING)
        val COMMENT: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BRACES: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val COMMA: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val PROPERTY_KEY: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_PROPERTY_KEY", DefaultLanguageHighlighterColors.KEYWORD)
        val CHARACTER_LITERAL: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_CHARACTER_LITERAL", DefaultLanguageHighlighterColors.STRING)
        val BAD_CHARACTER: TextAttributesKey =
            TextAttributesKey.createTextAttributesKey("KCM_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val LABEL_KEYS = arrayOf(LABEL)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val COMMA_KEYS = arrayOf(COMMA)
        private val PROPERTY_KEY_KEYS = arrayOf(PROPERTY_KEY)
        private val CHARACTER_LITERAL_KEYS = arrayOf(CHARACTER_LITERAL)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()

        private val KEYWORD_TYPES = setOf(
            KcmTypes.TYPE_KEYWORD,
            KcmTypes.MAP_KEYWORD,
            KcmTypes.KEY_KEYWORD,
            KcmTypes.USAGE_KEYWORD,
            KcmTypes.NONE_KEYWORD,
            KcmTypes.FALLBACK_KEYWORD,
            KcmTypes.REPLACE_KEYWORD,
        )
    }

    override fun getHighlightingLexer(): Lexer = KcmLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (KEYWORD_TYPES.contains(tokenType)) {
            return KEYWORD_KEYS
        }
        if (tokenType == KcmTypes.NUMBER) {
            return NUMBER_KEYS
        }
        if (tokenType == KcmTypes.LABEL) {
            return LABEL_KEYS
        }
        if (tokenType == KcmTypes.COMMENT) {
            return COMMENT_KEYS
        }
        if (tokenType == KcmTypes.LBRACE || tokenType == KcmTypes.RBRACE) {
            return BRACES_KEYS
        }
        if (tokenType == KcmTypes.COMMA) {
            return COMMA_KEYS
        }
        if (tokenType == KcmTypes.PROPERTY_KEY) {
            return PROPERTY_KEY_KEYS
        }
        if (tokenType == KcmTypes.CHARACTER_LITERAL) {
            return CHARACTER_LITERAL_KEYS
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return BAD_CHAR_KEYS
        }
        return EMPTY_KEYS
    }
}
