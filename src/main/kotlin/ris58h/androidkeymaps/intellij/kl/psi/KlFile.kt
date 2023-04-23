package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import ris58h.androidkeymaps.intellij.kl.KlFileType
import ris58h.androidkeymaps.intellij.kl.KlLanguage

class KlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, KlLanguage) {
    override fun getFileType(): FileType = KlFileType.INSTANCE

    override fun toString(): String {
        return "Android Key Layout file"
    }
}