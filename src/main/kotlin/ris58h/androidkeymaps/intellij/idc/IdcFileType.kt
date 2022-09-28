package ris58h.androidkeymaps.intellij.idc

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object IdcFileType : LanguageFileType(IdcLanguage) {
    override fun getName(): String = "AndroidInputDeviceConfiguration"

    override fun getDisplayName(): String = "Android Input Device Configuration"

    override fun getDescription(): String = "Android Input Device Configuration file"

    override fun getDefaultExtension(): String = "idc"

    override fun getIcon(): Icon? = null
}
