package ris58h.androidkeymaps.intellij.idc.psi

import com.intellij.psi.PsiElement

interface IdcPropertyMixin : PsiElement {
    val key: String?
        get() = node.findChildByType(IdcTypes.KEY)?.text
}