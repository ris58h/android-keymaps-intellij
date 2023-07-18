package ris58h.androidkeymaps.intellij.kcm

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import ris58h.androidkeymaps.intellij.kcm.parser.KcmParser
import ris58h.androidkeymaps.intellij.kcm.psi.KcmFile
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTokenSets
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypes

class KcmParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = KcmLexerAdapter()

    override fun createParser(project: Project?) = KcmParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = KcmTokenSets.COMMENTS

    override fun getStringLiteralElements() = TokenSet.EMPTY

    override fun createElement(node: ASTNode) = KcmTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = KcmFile(viewProvider)
}

private val FILE = IFileElementType(KcmLanguage)
