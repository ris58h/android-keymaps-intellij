package ris58h.androidkeymaps.intellij.kcm

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.kcm.psi.KcmKeyPropertiesBlock

class KcmFoldingBuilder : FoldingBuilderEx() {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        return PsiTreeUtil.findChildrenOfType(root, KcmKeyPropertiesBlock::class.java)
            .map { FoldingDescriptor(it.node, it.textRange, null, "{...}") }
            .toTypedArray()
    }

    override fun getPlaceholderText(node: ASTNode) = null

    override fun isCollapsedByDefault(node: ASTNode) = false
}