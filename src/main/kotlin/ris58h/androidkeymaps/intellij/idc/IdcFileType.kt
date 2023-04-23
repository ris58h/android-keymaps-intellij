package ris58h.androidkeymaps.intellij.idc

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class IdcFileType : LanguageFileType(IdcLanguage) {
    override fun getName(): String = "AndroidInputDeviceConfiguration"

    override fun getDescription(): String = "Android Input Device Configuration file"

    override fun getDefaultExtension(): String = "idc"

    override fun getIcon(): Icon = AllIcons.FileTypes.Text

    companion object {
        @JvmField
        val INSTANCE = IdcFileType()
    }
}
