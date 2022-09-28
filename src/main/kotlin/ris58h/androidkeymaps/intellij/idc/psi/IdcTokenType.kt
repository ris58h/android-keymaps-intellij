package ris58h.androidkeymaps.intellij.idc.psi

import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.idc.IdcLanguage

class IdcTokenType(debugName: String): IElementType(debugName, IdcLanguage) {
    override fun toString(): String = "IdcTokenType." + super.toString()
}