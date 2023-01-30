package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.psi.PsiElement
import ris58h.androidkeymaps.intellij.strtol

interface KcmMapEntryMixin : PsiElement {
    val isUsage: Boolean
        get() = node.findChildByType(KcmTypes.USAGE_KEYWORD) != null

    val code: Long?
        get() {
            val number = node.findChildByType(KcmTypes.NUMBER)
            return if (number == null) null else strtol(number.text)
        }

    val codeElement: PsiElement?
        get() = node.findChildByType(KcmTypes.NUMBER)?.psi
}
