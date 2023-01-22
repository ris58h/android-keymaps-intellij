package ris58h.androidkeymaps.intellij.idc

import com.intellij.codeInspection.*
import com.intellij.psi.PsiFile
import com.intellij.psi.SyntaxTraverser
import ris58h.androidkeymaps.intellij.idc.psi.IdcFile
import ris58h.androidkeymaps.intellij.idc.psi.IdcTypes

class IdcDuplicateKeyInspection : LocalInspectionTool() {
    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        if (file !is IdcFile) {
            return null
        }

        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)
        checkIdcFile(file, problemsHolder)
        return problemsHolder.resultsArray
    }

    private fun checkIdcFile(file: IdcFile, problemsHolder: ProblemsHolder) {
        val keys = mutableSetOf<String>()
        SyntaxTraverser.astTraverser(file.node)
            .filterTypes { t -> t == IdcTypes.KEY }
            .forEach {
                if (!keys.add(it.text)) {
                    problemsHolder.registerProblem(it.psi, "Duplicate property value for key '${it.text}'")
                }
            }
    }
}