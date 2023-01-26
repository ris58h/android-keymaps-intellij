package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import ris58h.androidkeymaps.intellij.kcm.KcmFileType
import ris58h.androidkeymaps.intellij.kcm.KcmLanguage

class KcmFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, KcmLanguage) {
    override fun getFileType(): FileType = KcmFileType

    override fun toString(): String {
        return "Android Key Character Map file"
    }
}