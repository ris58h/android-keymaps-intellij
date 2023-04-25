package ris58h.androidkeymaps.intellij.kl.psi

import com.intellij.psi.tree.TokenSet

object KlTokenSets {
    val COMMENTS: TokenSet = TokenSet.create(KlTypes.COMMENT)
    val KEYWORDS = setOf(
        KlTypes.KEY_KEYWORD,
        KlTypes.USAGE_KEYWORD,
        KlTypes.AXIS_KEYWORD,
        KlTypes.INVERT_KEYWORD,
        KlTypes.SPLIT_KEYWORD,
        KlTypes.FLAT_KEYWORD,
        KlTypes.LED_KEYWORD,
        KlTypes.SENSOR_KEYWORD,
        KlTypes.RKC_KEYWORD,
    )
}
