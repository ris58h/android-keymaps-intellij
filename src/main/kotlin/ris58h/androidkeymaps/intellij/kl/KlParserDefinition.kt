package ris58h.androidkeymaps.intellij.kl

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import ris58h.androidkeymaps.intellij.kl.parser.KlParser
import ris58h.androidkeymaps.intellij.kl.psi.KlFile
import ris58h.androidkeymaps.intellij.kl.psi.KlTokenSets
import ris58h.androidkeymaps.intellij.kl.psi.KlTypes

class KlParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = KlLexerAdapter()

    override fun createParser(project: Project?) = KlParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = KlTokenSets.COMMENTS

    override fun getStringLiteralElements() = TokenSet.EMPTY

    override fun createElement(node: ASTNode) = KlTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = KlFile(viewProvider)
}

private val FILE = IFileElementType(KlLanguage)
