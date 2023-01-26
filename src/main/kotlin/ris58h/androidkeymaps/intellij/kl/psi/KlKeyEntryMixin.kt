//package ris58h.androidkeymaps.intellij.kl.psi
//
//import com.intellij.psi.PsiElement
//import com.intellij.psi.util.PsiTreeUtil
//
//interface KlKeyEntryMixin : UsageEntry, PsiElement {
//    val flags: Array<out KlKeyFlag>?
//        get() = PsiTreeUtil.getChildrenOfType(this, KlKeyFlag::class.java)
//}