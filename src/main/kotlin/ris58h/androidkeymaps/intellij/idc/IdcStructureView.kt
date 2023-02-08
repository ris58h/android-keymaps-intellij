package ris58h.androidkeymaps.intellij.idc

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import ris58h.androidkeymaps.intellij.idc.psi.IdcFile
import ris58h.androidkeymaps.intellij.idc.psi.IdcProperty
import ris58h.androidkeymaps.intellij.idc.psi.impl.IdcPropertyImpl

class IdcStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return IdcStructureViewModel(editor, psiFile)
            }
        }
    }
}

class IdcStructureViewModel(editor: Editor?, psiFile: PsiFile) :
    StructureViewModelBase(psiFile, editor, IdcStructureViewElement(psiFile)),
    StructureViewModel.ElementInfoProvider {

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean {
        return false
    }

    override fun isAlwaysLeaf(element: StructureViewTreeElement): Boolean {
        return element.value is IdcProperty
    }

    override fun getSuitableClasses(): Array<Class<IdcProperty>> {
        return arrayOf(IdcProperty::class.java)
    }
}

class IdcStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
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
        if (myElement is IdcProperty) {
            val key = myElement.key ?: ""
            return PresentationData(key, null, null, null)
        }
        return myElement.presentation ?: PresentationData()
    }

    override fun getChildren(): Array<TreeElement> {
        if (myElement is IdcFile) {
            return PsiTreeUtil.getChildrenOfTypeAsList(myElement, IdcProperty::class.java)
                .map { IdcStructureViewElement(it as IdcPropertyImpl) }
                .toTypedArray()
        }
        return TreeElement.EMPTY_ARRAY
    }
}