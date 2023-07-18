package ris58h.androidkeymaps.intellij.kcm

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class KcmFileType : LanguageFileType(KcmLanguage) {
    override fun getName() = "AndroidKeyCharacterMap"

    override fun getDescription() = "Android Key Character Map file"

    override fun getDefaultExtension() = "kcm"

    override fun getIcon() = AllIcons.FileTypes.Text

    companion object {
        val INSTANCE = KcmFileType()
    }
}
