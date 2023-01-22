package ris58h.androidkeymaps.intellij.kl

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

object KlFileType : LanguageFileType(KlLanguage) {
    override fun getName(): String = "AndroidKeyLayout"

    override fun getDisplayName(): String = "Android Key Layout"

    override fun getDescription(): String = "Android Key Layout file"

    override fun getDefaultExtension(): String = "kl"

//    TODO
    override fun getIcon(): Icon? = null
}
