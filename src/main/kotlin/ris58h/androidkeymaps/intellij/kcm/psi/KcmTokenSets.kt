package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.psi.tree.TokenSet

object KcmTokenSets {
    val COMMENTS: TokenSet = TokenSet.create(KcmTypes.COMMENT)
    val LABEL_OR_NUMBER: TokenSet = TokenSet.create(KcmTypes.LABEL, KcmTypes.NUMBER)
    val KEYWORDS = setOf(
        KcmTypes.TYPE_KEYWORD,
        KcmTypes.MAP_KEYWORD,
        KcmTypes.KEY_KEYWORD,
        KcmTypes.USAGE_KEYWORD,
        KcmTypes.NONE_KEYWORD,
        KcmTypes.FALLBACK_KEYWORD,
        KcmTypes.REPLACE_KEYWORD,
    )
}
