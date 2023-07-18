package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.kcm.KcmFileType
import ris58h.androidkeymaps.intellij.kcm.KcmLanguage

class KcmFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, KcmLanguage) {
    override fun getFileType(): FileType = KcmFileType.INSTANCE

    override fun toString(): String = "Android Key Character Map file"

    val entries: List<KcmEntry>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, KcmEntry::class.java)
}