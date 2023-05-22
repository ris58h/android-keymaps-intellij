package ris58h.androidkeymaps.intellij.kcm

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.kcm.psi.KcmFile
import ris58h.androidkeymaps.intellij.kcm.psi.KcmEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmKeyEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmMapEntry
import ris58h.androidkeymaps.intellij.kcm.psi.KcmTypeEntry
import ris58h.androidkeymaps.intellij.kcm.psi.impl.KcmEntryImpl

class KcmStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return KcmStructureViewModel(editor, psiFile)
            }
        }
    }
}

class KcmStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, KcmStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false
    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean = element.value is KcmEntry
    override fun getSuitableClasses(): Array<Class<KcmEntry>> = arrayOf(KcmEntry::class.java)
}

class KcmStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue(): Any = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate(): Boolean = myElement.canNavigate()
    override fun canNavigateToSource(): Boolean = myElement.canNavigateToSource()

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
            return PsiTreeUtil.getChildrenOfTypeAsList(myElement, KcmEntry::class.java)
                .filter { it !is KcmTypeEntry }
                .map { KcmStructureViewElement(it as KcmEntryImpl) }
                .toTypedArray()
        }
        return TreeElement.EMPTY_ARRAY
    }
}
