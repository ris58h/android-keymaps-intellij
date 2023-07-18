package ris58h.androidkeymaps.intellij.kl

import com.intellij.openapi.editor.colors.TextAttributesKey
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
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = KlSyntaxHighlighter()
    }

    override fun getHighlightingLexer() = KlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val tokenColor = tokenColor(tokenType)
        return if (tokenColor == null) TextAttributesKey.EMPTY_ARRAY else arrayOf(tokenColor)
    }

    private fun tokenColor(tokenType: IElementType): TextAttributesKey? {
        if (KlTokenSets.KEYWORDS.contains(tokenType)) {
            return KlHighlightingColors.KEYWORD
        }
        if (tokenType == KlTypes.NUMBER) {
            return KlHighlightingColors.NUMBER
        }
        if (tokenType == KlTypes.LABEL) {
            return KlHighlightingColors.LABEL
        }
        if (tokenType == KlTypes.COMMENT) {
            return KlHighlightingColors.COMMENT
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return KlHighlightingColors.BAD_CHAR
        }
        return null
    }
}
