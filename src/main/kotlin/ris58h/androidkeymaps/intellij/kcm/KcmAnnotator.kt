package ris58h.androidkeymaps.intellij.kcm

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.elementType
import ris58h.androidkeymaps.intellij.kcm.psi.KcmKeyProperty
import ris58h.androidkeymaps.intellij.kcm.psi.KcmMapEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypeDeclaration

class KcmAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == TokenType.WHITE_SPACE
            && element.text.contains('\n')
        ) {
            if (element.parent is KcmTypeDeclaration) {
                annotateNewlineError("Type declaration", element, holder)
            }
            if (element.parent is KcmMapEntry) {
                annotateNewlineError("Map", element, holder)
            }
            if (element.parent is KcmKeyProperty) {
                annotateNewlineError("Key property", element, holder)
            }
        }
    }

    private fun annotateNewlineError(name: String, element: PsiElement, holder: AnnotationHolder) {
        holder.newAnnotation(HighlightSeverity.ERROR, "$name entry must be placed on a single line")
            .range(element)
            .afterEndOfLine()
            .create()
    }
}