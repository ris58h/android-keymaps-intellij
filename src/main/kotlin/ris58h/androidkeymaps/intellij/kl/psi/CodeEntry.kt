package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.psi.PsiElement

interface CodeEntry : PsiElement {
    val code: Long?
        get() {
            val number = node.findChildByType(KlTypes.NUMBER)
            return if (number == null) null else strtol(number.text)
        }
}

private fun strtol(str: String): Long? {
    return if (str.startsWith("0x") || str.startsWith("0X")) {
        try {
            str.substring(2).toLong(16)
        } catch (e: NumberFormatException) {
            null
        }
    } else {
        try {
            val radix = if (str.startsWith("0")) 8 else 10
            str.toLong(radix)
        } catch (e: NumberFormatException) {
            null
        }
    }
}
