package ris58h.androidkeymaps.intellij.kl.psi

interface UsageEntry : CodeEntry {
    val isUsage: Boolean
        get() = node.findChildByType(KlTypes.USAGE_KEYWORD) != null
}
