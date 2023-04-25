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
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTokenSets
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypes

class KcmSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
            KcmSyntaxHighlighter()
    }

    companion object {
        private val BAD_CHAR_KEYS = arrayOf(HighlighterColors.BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        private val NUMBER_KEYS = arrayOf(DefaultLanguageHighlighterColors.NUMBER)
        private val LABEL_KEYS = arrayOf(DefaultLanguageHighlighterColors.STRING)
        private val COMMENT_KEYS = arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val BRACES_KEYS = arrayOf(DefaultLanguageHighlighterColors.BRACES)
        private val COMMA_KEYS = arrayOf(DefaultLanguageHighlighterColors.COMMA)
        private val PROPERTY_KEY_KEYS = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        private val CHARACTER_LITERAL_KEYS = arrayOf(DefaultLanguageHighlighterColors.STRING)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer = KcmLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (KcmTokenSets.KEYWORDS.contains(tokenType)) {
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
