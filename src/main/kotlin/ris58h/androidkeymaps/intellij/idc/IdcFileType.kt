package ris58h.androidkeymaps.intellij.idc

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class IdcFileType : LanguageFileType(IdcLanguage) {
    override fun getName() = "AndroidInputDeviceConfiguration"

    override fun getDescription() = "Android Input Device Configuration file"

    override fun getDefaultExtension() = "idc"

    override fun getIcon() = AllIcons.FileTypes.Text

    companion object {
        val INSTANCE = IdcFileType()
    }
}
