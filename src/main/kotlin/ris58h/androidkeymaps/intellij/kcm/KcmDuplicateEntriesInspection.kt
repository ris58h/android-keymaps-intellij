package ris58h.androidkeymaps.intellij.kcm

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiFile
import com.intellij.psi.SyntaxTraverser
import ris58h.androidkeymaps.intellij.kcm.psi.*

class KcmDuplicateEntriesInspection : LocalInspectionTool() {
    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        if (file !is KcmFile) {
            return null
        }

        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)
        checkKcmFile(file, problemsHolder)
        return problemsHolder.resultsArray
    }

    private fun checkKcmFile(file: KcmFile, problemsHolder: ProblemsHolder) {
        val duplicatesProcessor = DuplicatesProcessor(problemsHolder)
        SyntaxTraverser.psiTraverser(file)
            .forEach {
                when (it) {
                    is KcmTypeDeclaration -> duplicatesProcessor.processTypeDeclaration(it)
                    is KcmMapEntry -> duplicatesProcessor.processMapEntry(it)
                    is KcmKeyEntry -> duplicatesProcessor.processKeyEntry(it)
                }
            }
    }

    private class DuplicatesProcessor(val problemsHolder: ProblemsHolder) {
        var hasTypeDeclaration = false
        val keyScanCodes = mutableSetOf<Long>()
        val keyUsageCodes = mutableSetOf<Long>()
        val keys = mutableSetOf<String>()

        fun processTypeDeclaration(it: KcmTypeDeclaration) {
            if (hasTypeDeclaration) {
                problemsHolder.registerProblem(it, "Duplicate keyboard 'type' declaration")
            } else {
                hasTypeDeclaration = true
            }
        }

        fun processMapEntry(it: KcmMapEntry) {
            val code = it.code
            val keyCodes = if (it.isUsage) keyUsageCodes else keyScanCodes
            if (code != null && !keyCodes.add(code)) {
                val type = if (it.isUsage) "usage" else "scan code"
                problemsHolder.registerProblem(it.codeElement!!, "Duplicate entry for key $type '${it.codeElement!!.text}'")
            }
        }

        fun processKeyEntry(it: KcmKeyEntry) {
            val key = it.key
            if (key != null && !keys.add(key)) {
                problemsHolder.registerProblem(it.keyElement!!, "Duplicate entry for key '$key'")
            }
            it.keyPropertiesBlock?.keyPropertyList?.let { processKeyProperties(it) }
        }

        private fun processKeyProperties(properties: List<KcmKeyProperty>) {
            var hasLabel = false
            var hasNumber = false
            val modifierCombinations = mutableSetOf<String>()
            properties.flatMap { it.keyPropertyKeys.keyPropertyKeyList }.forEach {
                when (it.text) {
                    null -> {}
                    "label" -> {
                        //TODO duplicate property 'label: none' is actually valid
                        if (hasLabel) {
                            problemsHolder.registerProblem(it, "Duplicate label for key")
                        } else {
                            hasLabel = true
                        }
                    }
                    "number" -> {
                        //TODO duplicate property 'number: none' is actually valid
                        if (hasNumber) {
                            problemsHolder.registerProblem(it, "Duplicate label for number")
                        } else {
                            hasNumber = true
                        }
                    }
                    else -> {
                        val modifiers = it.text.split('+')
                        if (hasDuplicates(modifiers)) {
                            problemsHolder.registerProblem(it, "Duplicate modifier combination '${it.text}'")
                        }
                        val modifierCombination = modifiers.sorted().joinToString("+")
                        if (!modifierCombinations.add(modifierCombination)) {
                            problemsHolder.registerProblem(it, "Duplicate key behavior for modifier")
                        }
                    }
                }
            }
        }
    }
}

private fun <T> hasDuplicates(list: List<T>): Boolean {
    val set = mutableSetOf<T>()
    for (it in list) {
        if (!set.add(it)) return true
    }
    return false
}
