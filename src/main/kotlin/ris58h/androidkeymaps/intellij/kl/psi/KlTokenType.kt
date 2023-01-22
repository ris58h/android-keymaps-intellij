package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.kl.KlLanguage

class KlTokenType(debugName: String): IElementType(debugName, KlLanguage) {
    override fun toString(): String = "KlTokenType." + super.toString()
}