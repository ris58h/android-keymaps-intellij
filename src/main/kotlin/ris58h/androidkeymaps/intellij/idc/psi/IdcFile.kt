package ris58h.androidkeymaps.intellij.idc.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import ris58h.androidkeymaps.intellij.idc.IdcFileType
import ris58h.androidkeymaps.intellij.idc.IdcLanguage

class IdcFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, IdcLanguage) {
    override fun getFileType(): FileType = IdcFileType

    override fun toString(): String {
        return "Android Input Device Configuration file"
    }
}
