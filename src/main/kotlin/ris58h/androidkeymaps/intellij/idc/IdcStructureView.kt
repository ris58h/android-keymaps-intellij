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
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder =
        object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?) = createModel(psiFile, editor)
        }
}

private fun createModel(psiFile: PsiFile, editor: Editor?): StructureViewModel {
    return object : StructureViewModelBase(psiFile, editor, IdcStructureViewElement(psiFile)),
        StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?) = false
        override fun isAlwaysLeaf(element: StructureViewTreeElement) = element.value is IdcProperty
    }.withSuitableClasses(IdcProperty::class.java)
}

class IdcStructureViewElement(private val myElement: NavigatablePsiElement) : StructureViewTreeElement {
    override fun getValue() = myElement
    override fun navigate(requestFocus: Boolean) = myElement.navigate(requestFocus)
    override fun canNavigate() = myElement.canNavigate()
    override fun canNavigateToSource() = myElement.canNavigateToSource()

    override fun getPresentation(): ItemPresentation {
        return when (myElement) {
            is IdcProperty -> {
                val key = myElement.key ?: ""
                PresentationData(key, null, null, null)
            }
            else -> myElement.presentation ?: PresentationData()
        }
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
