package ris58h.androidkeymaps.intellij.idc

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import ris58h.androidkeymaps.intellij.idc.psi.IdcProperty

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