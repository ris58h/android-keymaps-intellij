package ris58h.androidkeymaps.intellij.idc

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import ris58h.androidkeymaps.intellij.idc.parser.IdcParser
import ris58h.androidkeymaps.intellij.idc.psi.IdcFile
import ris58h.androidkeymaps.intellij.idc.psi.IdcTokenSets
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = IdcLexerAdapter()

    override fun createParser(project: Project?) = IdcParser()

    override fun getFileNodeType() = FILE

    override fun getCommentTokens() = IdcTokenSets.COMMENTS

    override fun getStringLiteralElements() = TokenSet.EMPTY

    override fun createElement(node: ASTNode) = IdcTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider) = IdcFile(viewProvider)
}

private val FILE = IFileElementType(IdcLanguage)
