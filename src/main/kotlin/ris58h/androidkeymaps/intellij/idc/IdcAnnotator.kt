package ris58h.androidkeymaps.intellij.idc

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import com.intellij.psi.util.elementType
import ris58h.androidkeymaps.intellij.idc.psi.IdcProperty
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

private val RESERVED_CHARS = setOf(' ', '\\', '\"')

class IdcAnnotator : Annotator, DumbAware {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.elementType == IdcTypes.VALUE) {
            val value = element.text
            val reservedChars = reservedChars(value)
            if (reservedChars.isNotEmpty()) {
                holder.newAnnotation(HighlightSeverity.ERROR, errorMessage(reservedChars))
                    .range(element)
                    .create()
            }
        }

        if (element.elementType == TokenType.WHITE_SPACE
            && element.parent is IdcProperty
            && element.text.contains('\n')) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Property must be placed on a single line")
                .range(element)
                .afterEndOfLine()
                .create()
        }
    }

    private fun reservedChars(value: String): Set<Char> {
        val result = mutableSetOf<Char>()
        value.forEach {
            if (RESERVED_CHARS.contains(it)) {
                result.add(it)
            }
        }
        return result
    }

    private fun errorMessage(reservedChars: Set<Char>): String {
        return if (reservedChars.size == 1) {
            "Reserved character '${reservedChars.first()}' in property value."
        } else {
            val charsDescription = reservedChars.joinToString { "'$it'" }
            "Reserved characters $charsDescription in property value."
        }
    }
}