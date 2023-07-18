package ris58h.androidkeymaps.intellij.kcm

import com.intellij.openapi.editor.colors.TextAttributesKey
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
        override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) = KcmSyntaxHighlighter()
    }

    override fun getHighlightingLexer() = KcmLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        val tokenColor = tokenColor(tokenType)
        return if (tokenColor == null) TextAttributesKey.EMPTY_ARRAY else arrayOf(tokenColor)
    }

    private fun tokenColor(tokenType: IElementType): TextAttributesKey? {
        if (KcmTokenSets.KEYWORDS.contains(tokenType)) {
            return KcmHighlighterColors.KEYWORD
        }
        if (tokenType == KcmTypes.NUMBER) {
            return KcmHighlighterColors.NUMBER
        }
        if (tokenType == KcmTypes.LABEL) {
            return KcmHighlighterColors.LABEL
        }
        if (tokenType == KcmTypes.COMMENT) {
            return KcmHighlighterColors.COMMENT
        }
        if (tokenType == KcmTypes.LBRACE || tokenType == KcmTypes.RBRACE) {
            return KcmHighlighterColors.BRACES
        }
        if (tokenType == KcmTypes.COMMA) {
            return KcmHighlighterColors.COMMA
        }
        if (tokenType == KcmTypes.PROPERTY_KEY) {
            return KcmHighlighterColors.PROPERTY_KEY
        }
        if (tokenType == KcmTypes.CHARACTER_LITERAL) {
            return KcmHighlighterColors.CHARACTER_LITERAL
        }
        if (tokenType == TokenType.BAD_CHARACTER) {
            return KcmHighlighterColors.BAD_CHAR
        }
        return null
    }
}
