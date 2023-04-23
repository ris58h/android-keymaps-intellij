package ris58h.androidkeymaps.intellij.idc

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import ris58h.androidkeymaps.intellij.idc.parser.IdcParser
import ris58h.androidkeymaps.intellij.idc.psi.COMMENTS
import ris58h.androidkeymaps.intellij.idc.psi.IdcFile
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcParserDefinition : ParserDefinition {
    companion object {
        private val FILE = IFileElementType(IdcLanguage)
    }

    override fun createLexer(project: Project?): Lexer = IdcLexerAdapter()

    override fun createParser(project: Project?): PsiParser = IdcParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode): PsiElement = IdcTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = IdcFile(viewProvider)
}
