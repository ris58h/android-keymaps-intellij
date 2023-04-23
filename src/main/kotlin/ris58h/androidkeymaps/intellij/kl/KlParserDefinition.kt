package ris58h.androidkeymaps.intellij.kl

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
import ris58h.androidkeymaps.intellij.kl.parser.KlParser
import ris58h.androidkeymaps.intellij.kl.psi.COMMENTS
import ris58h.androidkeymaps.intellij.kl.psi.KlFile
import ris58h.androidkeymaps.intellij.kl.psi.KlTypes

class KlParserDefinition : ParserDefinition {
    companion object {
        private val FILE = IFileElementType(KlLanguage)
    }

    override fun createLexer(project: Project?): Lexer = KlLexerAdapter()

    override fun createParser(project: Project?): PsiParser = KlParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode): PsiElement = KlTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = KlFile(viewProvider)
}
