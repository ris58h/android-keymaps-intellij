package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.kl.KlFileType
import ris58h.androidkeymaps.intellij.kl.KlLanguage

class KlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, KlLanguage) {
    override fun getFileType(): FileType = KlFileType.INSTANCE

    override fun toString(): String = "Android Key Layout file"

    val entries: List<KlEntry>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, KlEntry::class.java)
}