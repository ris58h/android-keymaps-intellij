package ris58h.androidkeymaps.intellij.idc.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.idc.IdcFileType
import ris58h.androidkeymaps.intellij.idc.IdcLanguage

class IdcFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, IdcLanguage) {
    override fun getFileType(): FileType = IdcFileType.INSTANCE

    override fun toString(): String = "Android Input Device Configuration file"

    val properties: List<IdcProperty>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, IdcProperty::class.java)
}
