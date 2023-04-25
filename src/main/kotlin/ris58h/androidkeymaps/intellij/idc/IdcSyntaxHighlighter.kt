package ris58h.androidkeymaps.intellij.idc

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
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter =
            IdcSyntaxHighlighter()
    }

    companion object {
        private val BAD_CHAR_KEYS = arrayOf(HighlighterColors.BAD_CHARACTER)
        private val SEPARATOR_KEYS = arrayOf(DefaultLanguageHighlighterColors.OPERATION_SIGN)
        private val KEY_KEYS = arrayOf(DefaultLanguageHighlighterColors.KEYWORD)
        private val VALUE_KEYS = arrayOf(DefaultLanguageHighlighterColors.STRING)
        private val COMMENT_KEYS = arrayOf(DefaultLanguageHighlighterColors.LINE_COMMENT)
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