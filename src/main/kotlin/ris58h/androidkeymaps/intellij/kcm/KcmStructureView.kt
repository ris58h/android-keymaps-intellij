package ris58h.androidkeymaps.intellij.kcm

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import ris58h.androidkeymaps.intellij.kcm.psi.KcmFile
import ris58h.androidkeymaps.intellij.kcm.psi.KcmEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmKeyEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmMapEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypeEntry
import ris58h.androidkeymaps.intellij.kcm.psi.impl.KcmEntryImpl

class KcmStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile) =
        object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?) = createModel(psiFile, editor)
        }
}

private fun createModel(psiFile: PsiFile, editor: Editor?): StructureViewModel {
    return object : StructureViewModelBase(psiFile, editor, KcmStructureViewElement(psiFile)),
        StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?) = false
        override fun isAlwaysLeaf(element: StructureViewTreeElement) = element.value is KcmEntry
    }.withSuitableClasses(KcmEntry::class.java)
}

class KcmStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue() = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate() = myElement.canNavigate()
    override fun canNavigateToSource() = myElement.canNavigateToSource()

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is KcmMapEntry -> {
                val code = myElement.code ?: ""
                val isUsage = myElement.isUsage
                PresentationData("map key${if (isUsage) " usage" else "" } $code", null, null, null)
            }
            is KcmKeyEntry -> {
                val key = myElement.key ?: ""
                PresentationData("key $key", null, null, null)
            }
            else -> myElement.presentation ?: PresentationData()
        }
    }

    override fun getChildren(): Array<TreeElement> {
        if (myElement is KcmFile) {
            return myElement.entries
                .filter { it !is KcmTypeEntry }
                .map { KcmStructureViewElement(it as KcmEntryImpl) }
                .toTypedArray()
        }
        return TreeElement.EMPTY_ARRAY
    }
}
