package ris58h.androidkeymaps.intellij.kcm.psi

import com.intellij.psi.tree.TokenSet

val COMMENTS: TokenSet = TokenSet.create(KcmTypes.COMMENT)

val LABEL_OR_NUMBER: TokenSet = TokenSet.create(KcmTypes.LABEL, KcmTypes.NUMBER)