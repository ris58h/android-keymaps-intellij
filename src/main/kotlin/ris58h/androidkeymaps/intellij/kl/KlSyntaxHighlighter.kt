package ris58h.androidkeymaps.intellij.kl

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
import ris58h.androidkeymaps.intellij.kl.psi.KlTokenSets
import ris58h.androidkeymaps.intellij.kl.psi.KlTypes

class KlSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
            KlSyntaxHighlighter()
    }

    companion object {
        private val BAD_CHAR_KEYS = arrayOf(HighlighterColors.BAD_CHARACTER)
        private val KEYWORD_KEYS = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        private val NUMBER_KEYS = arrayOf(DefaultLanguageHighlighterColors.NUMBER)
        private val LABEL_KEYS = arrayOf(DefaultLanguageHighlighterColors.STRING)
        private val COMMENT_KEYS = arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
        private val EMPTY_KEYS = arrayOf<TextAttributesKey>()
    }

    override fun getHighlightingLexer(): Lexer = KlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (KlTokenSets.KEYWORDS.contains(tokenType)) {
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
