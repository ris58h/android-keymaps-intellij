package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.kcm.KcmLanguage

class KcmTokenType(debugName: String): IElementType(debugName, KcmLanguage) {
    override fun toString(): String = "KcmTokenType." + super.toString()
}