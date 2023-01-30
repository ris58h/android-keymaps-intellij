package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.psi.PsiElement
import ris58h.androidkeymaps.intellij.strtol

interface CodeEntry : PsiElement {
    val code: Long?
        get() {
            val number = node.findChildByType(KlTypes.NUMBER)
            return if (number == null) null else strtol(number.text)
        }

    val codeElement: PsiElement?
        get() = node.findChildByType(KlTypes.NUMBER)?.psi
}
