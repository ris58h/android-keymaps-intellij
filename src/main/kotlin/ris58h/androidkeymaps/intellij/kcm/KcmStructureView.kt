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

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is KcmEntry
    }

    override fun getSuitableClasses(): Array<Class<KcmEntry>> {
        return arrayOf(KcmEntry::class.java)
    }
}

class KcmStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue(): Any {
        return myElement
    }

    override fun navigate(requestFocus: Boolean) {
        myElement.navigate(requestFocus)
    }

    override fun canNavigate(): Boolean {
        return myElement.canNavigate()
    }

    override fun canNavigateToSource(): Boolean {
        return myElement.canNavigateToSource()
    }

    override fun getPresentation(): ItemPresentation {
        if (myElement is KcmMapEntry) {
            val code = myElement.code ?: ""
            val isUsage = myElement.isUsage
            return PresentationData("map key${if (isUsage) " usage" else "" } $code", null, null, null)
        }
        if (myElement is KcmKeyEntry) {
            val key = myElement.key ?: ""
            return PresentationData("key $key", null, null, null)
        }
        return myElement.presentation ?: PresentationData()
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
