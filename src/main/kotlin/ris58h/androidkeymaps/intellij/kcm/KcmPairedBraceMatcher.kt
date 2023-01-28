package ris58h.androidkeymaps.intellij.kcm

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypes

private val PAIRS = arrayOf(BracePair(KcmTypes.LBRACE, KcmTypes.RBRACE, false))

class KcmPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
        return true
    }

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int): Int = openingBraceOffset
}