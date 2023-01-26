package ris58h.androidkeymaps.intellij.kl

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiFile
import com.intellij.psi.SyntaxTraverser
import ris58h.androidkeymaps.intellij.kl.psi.KlAxisEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlFile
import ris58h.androidkeymaps.intellij.kl.psi.KlKeyEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlLedEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlRkcEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlSensorEntry

class KlDuplicateEntriesInspection : LocalInspectionTool() {
    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor>? {
        if (file !is KlFile) {
            return null
        }

        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)
        checkKlFile(file, problemsHolder)
        return problemsHolder.resultsArray
    }

    private fun checkKlFile(file: KlFile, problemsHolder: ProblemsHolder) {
        val duplicatesProcessor = DuplicatesProcessor(problemsHolder)
        SyntaxTraverser.psiTraverser(file)
            //TODO skip subtrees because we don't need to process every PSI node
            .forEach {
                when (it) {
                    is KlKeyEntry -> duplicatesProcessor.processKeyEntry(it)
                    is KlAxisEntry -> duplicatesProcessor.processAxisEntry(it)
                    is KlLedEntry -> duplicatesProcessor.processLedEntry(it)
                    is KlSensorEntry -> duplicatesProcessor.processSensorEntry(it)
                    is KlRkcEntry -> duplicatesProcessor.processRkcEntry(it)
                }
            }
    }

    private class DuplicatesProcessor(val problemsHolder: ProblemsHolder) {
        val keyScanCodes = mutableSetOf<Long>()
        val keyUsageCodes = mutableSetOf<Long>()
        val axisScanCodes = mutableSetOf<Long>()
        val ledScanCodes = mutableSetOf<Long>()
        val ledUsageCodes = mutableSetOf<Long>()
        val sensorAbsCodes = mutableSetOf<Long>()
        val configs = mutableSetOf<String>()

        fun processKeyEntry(it: KlKeyEntry) {
            val code = it.code
            val keyCodes = if (it.isUsage) keyUsageCodes else keyScanCodes
            if (code != null && !keyCodes.add(code)) {
                val type = if (it.isUsage) "usage" else "scan code"
                problemsHolder.registerProblem(it.codeElement!!, "Duplicate entry for key $type '${it.codeElement!!.text}'")
            }
            val flags = mutableSetOf<String>()
            it.keyFlagList.forEach {
                if (!flags.add(it.text)) {
                    problemsHolder.registerProblem(it, "Duplicate key flag '${it.text}'")
                }
            }
        }

        fun processAxisEntry(it: KlAxisEntry) {
            val code = it.code
            if (code != null && !axisScanCodes.add(code)) {
                problemsHolder.registerProblem(it.codeElement!!, "Duplicate entry for axis scan code '${it.codeElement!!.text}'")
            }
        }

        fun processLedEntry(it: KlLedEntry) {
            val code = it.code
            val ledCodes = if (it.isUsage) ledUsageCodes else ledScanCodes
            if (code != null && !ledCodes.add(code)) {
                val type = if (it.isUsage) "usage" else "scan code"
                problemsHolder.registerProblem(it.codeElement!!, "Duplicate entry for led $type '${it.codeElement!!.text}'")
            }
        }

        fun processSensorEntry(it: KlSensorEntry) {
            val code = it.code
            if (code != null && !sensorAbsCodes.add(code)) {
                problemsHolder.registerProblem(it.codeElement!!, "Duplicate entry for sensor '${it.codeElement!!.text}' abs code")
            }
        }

        fun processRkcEntry(it: KlRkcEntry) {
            val configName = it.configName
            if (configName != null && !configs.add(configName)) {
                problemsHolder.registerProblem(it.configNameElement!!, "Duplicate entry for required kernel config '${configName}'")
            }
        }
    }
}
