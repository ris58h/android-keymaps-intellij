package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.psi.PsiElement

interface KcmKeyEntryMixin : PsiElement {
    val key: String?
        get() = node.findChildByType(LABEL_OR_NUMBER)?.text

    val keyElement: PsiElement?
        get() = node.findChildByType(LABEL_OR_NUMBER)?.psi
}