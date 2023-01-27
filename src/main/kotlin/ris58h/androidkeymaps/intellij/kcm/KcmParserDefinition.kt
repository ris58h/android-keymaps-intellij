package ris58h.androidkeymaps.intellij.kcm

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
import ris58h.androidkeymaps.intellij.kcm.parser.KcmParser
import ris58h.androidkeymaps.intellij.kcm.psi.COMMENTS
import ris58h.androidkeymaps.intellij.kcm.psi.KcmFile
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypes

class KcmParserDefinition : ParserDefinition {
    companion object {
        private val FILE = IFileElementType(KcmLanguage)
    }

    override fun createLexer(project: Project?): Lexer = KcmLexerAdapter()

    override fun createParser(project: Project?): PsiParser = KcmParser()

    override fun getFileNodeType(): IFileElementType = FILE

    override fun getCommentTokens(): TokenSet = COMMENTS

    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY

    override fun createElement(node: ASTNode?): PsiElement = KcmTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = KcmFile(viewProvider)
}