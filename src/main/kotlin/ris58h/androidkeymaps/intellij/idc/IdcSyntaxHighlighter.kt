package ris58h.androidkeymaps.intellij.idc

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcSyntaxHighlighter : SyntaxHighlighterBase() {
    class Factory : SyntaxHighlighterFactory() {
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = IdcSyntaxHighlighter()
    }

    override fun getHighlightingLexer() = IdcLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val tokenColor = tokenColor(tokenType)
        return if (tokenColor == null) TextAttributesKey.EMPTY_ARRAY else arrayOf(tokenColor)
    }

    private fun tokenColor(tokenType: IElementType): TextAttributesKey? {
        if (tokenType == IdcTypes.SEPARATOR) {
            return IdcHighlighterColors.SEPARATOR
        }
        if (tokenType == IdcTypes.KEY) {
            return IdcHighlighterColors.KEY
        }
        if (tokenType == IdcTypes.VALUE) {
            return IdcHighlighterColors.VALUE
        }
        if (tokenType == IdcTypes.COMMENT) {
            return IdcHighlighterColors.COMMENT
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return IdcHighlighterColors.BAD_CHAR
        }
        return null
    }
}