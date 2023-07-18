package ris58h.androidkeymaps.intellij.kl

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class KlFileType : LanguageFileType(KlLanguage) {
    override fun getName() = "AndroidKeyLayout"

    override fun getDescription() = "Android Key Layout file"

    override fun getDefaultExtension() = "kl"

    override fun getIcon() = AllIcons.FileTypes.Text

    companion object {
        val INSTANCE = KlFileType()
    }
}
