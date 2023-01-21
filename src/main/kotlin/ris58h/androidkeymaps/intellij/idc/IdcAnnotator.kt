package ris58h.androidkeymaps.intellij.idc

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType != IdcTypes.VALUE) {
            return
        }

        val value = element.text

        if (value.contains("\\") || value.contains("\"")) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Found reserved character '\\' or '\"' in property value.")
                .range(element)
                .create()
        }
    }
}