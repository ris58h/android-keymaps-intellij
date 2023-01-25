package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.psi.PsiElement

interface RkcEntryMixin : PsiElement {
    val configName : String?
        get() = node.findChildByType(KlTypes.LABEL)?.text
}