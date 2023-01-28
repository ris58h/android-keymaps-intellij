package ris58h.androidkeymaps.intellij.kl

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.elementType
import ris58h.androidkeymaps.intellij.kl.psi.KlAxisEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlKeyEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlLedEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlRkcEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlSensorEntry

class KlAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == TokenType.WHITE_SPACE
            && element.text.contains('\n')) {
            if (element.parent is KlKeyEntry || element.parent?.parent is KlKeyEntry) {
                annotateNewlineError("Key ", element, holder)
            }
            if (element.parent is KlAxisEntry) {
                annotateNewlineError("Axis ", element, holder)
            }
            if (element.parent is KlLedEntry) {
                annotateNewlineError("Led ", element, holder)
            }
            if (element.parent is KlSensorEntry) {
                annotateNewlineError("Sensor ", element, holder)
            }
            if (element.parent is KlRkcEntry) {
                annotateNewlineError("Kernel config ", element, holder)
            }
        }
    }

    private fun annotateNewlineError(entryName: String, element: PsiElement, holder: AnnotationHolder) {
        holder.newAnnotation(HighlightSeverity.ERROR, "$entryName entry must be placed on a single line")
            .range(element)
            .afterEndOfLine()
            .create()
    }
}