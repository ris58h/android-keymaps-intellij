package ris58h.androidkeymaps.intellij.kl

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import ris58h.androidkeymaps.intellij.kl.psi.KlAxisEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlFile
import ris58h.androidkeymaps.intellij.kl.psi.KlEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlKeyEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlLedEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlRkcEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlSensorEntry
import ris58h.androidkeymaps.intellij.kl.psi.impl.KlEntryImpl

class KlStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder =
        object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?) = createModel(psiFile, editor)
        }
}

private fun createModel(psiFile: PsiFile, editor: Editor?): StructureViewModel {
    return object : StructureViewModelBase(psiFile, editor, KlStructureViewElement(psiFile)),
        StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?) = false
        override fun isAlwaysLeaf(element: StructureViewTreeElement) = element.value is KlEntry
    }.withSuitableClasses(KlEntry::class.java)
}

class KlStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue() = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate() = myElement.canNavigate()
    override fun canNavigateToSource() = myElement.canNavigateToSource()

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is KlKeyEntry -> {
                val code = myElement.code ?: ""
                val isUsage = myElement.isUsage
                PresentationData("key${if (isUsage) " usage" else "" } $code", null, null, null)
            }
            is KlAxisEntry -> {
                val code = myElement.code ?: ""
                PresentationData("axis $code", null, null, null)
            }
            is KlLedEntry -> {
                val code = myElement.code ?: ""
                val isUsage = myElement.isUsage
                PresentationData("led${if (isUsage) " usage" else "" } $code", null, null, null)
            }
            is KlSensorEntry -> {
                val code = myElement.code ?: ""
                PresentationData("sensor $code", null, null, null)
            }
            is KlRkcEntry -> {
                val configName = myElement.configName ?: ""
                PresentationData("requires_kernel_config $configName", null, null, null)
            }
            else -> myElement.presentation ?: PresentationData()
        }
    }

    override fun getChildren(): Array<TreeElement> {
        if (myElement is KlFile) {
            return myElement.entries
                .map { KlStructureViewElement(it as KlEntryImpl) }
                .toTypedArray()
        }
        return TreeElement.EMPTY_ARRAY
    }
}
