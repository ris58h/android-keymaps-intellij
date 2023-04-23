package ris58h.androidkeymaps.intellij.kcm

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object KcmFileType : LanguageFileType(KcmLanguage) {
    override fun getName(): String = "AndroidKeyCharacterMap"

    override fun getDescription(): String = "Android Key Character Map file"

    override fun getDefaultExtension(): String = "kcm"

    override fun getIcon(): Icon = AllIcons.FileTypes.Text
}
