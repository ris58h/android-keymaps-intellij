package ris58h.androidkeymaps.intellij.kl

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.kl.psi.KlAxisEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlFile
import ris58h.androidkeymaps.intellij.kl.psi.KlEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlKeyEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlLedEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlRkcEntry
import ris58h.androidkeymaps.intellij.kl.psi.KlSensorEntry
import ris58h.androidkeymaps.intellij.kl.psi.impl.KlEntryImpl

class KlStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return KlStructureViewModel(editor, psiFile)
            }
        }
    }
}

class KlStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, KlStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is KlEntry
    }

    override fun getSuitableClasses(): Array<Class<KlEntry>> {
        return arrayOf(KlEntry::class.java)
    }
}

class KlStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
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
        if (myElement is KlKeyEntry) {
            val code = myElement.code
            val isUsage = myElement.isUsage
            return PresentationData("map key${if (isUsage) " usage" else "" } $code", null, null, null)
        }
        if (myElement is KlAxisEntry) {
            val code = myElement.code
            return PresentationData("axis $code", null, null, null)
        }
        if (myElement is KlLedEntry) {
            val code = myElement.code
            val isUsage = myElement.isUsage
            return PresentationData("led${if (isUsage) " usage" else "" } $code", null, null, null)
        }
        if (myElement is KlSensorEntry) {
            val code = myElement.code
            return PresentationData("sensor $code", null, null, null)
        }
        if (myElement is KlRkcEntry) {
            val configName = myElement.configName
            return PresentationData("requires_kernel_config $configName", null, null, null)
        }
        return myElement.presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (myElement is KlFile) {
            return PsiTreeUtil.getChildrenOfTypeAsList(myElement, KlEntry::class.java)
                .map { KlStructureViewElement(it as KlEntryImpl) }
                .toTypedArray()
        }
        return TreeElement.EMPTY_ARRAY
    }
}
