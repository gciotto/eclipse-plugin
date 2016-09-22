package br.cnpem.gef.properties;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxLabelProvider;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class ChangeableComboBoxPropertyDescriptor extends PropertyDescriptor {

	private ComboBoxCellEditor cellEditor;
	
	public ChangeableComboBoxPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		cellEditor = new ComboBoxCellEditor();

	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		
		cellEditor = new ComboBoxCellEditor();
		cellEditor.setStyle(SWT.READ_ONLY);
		
		if (getValidator() != null)
			cellEditor.setValidator(getValidator());
				
		return cellEditor;
	}
	
	
	
	public void setItems(String[] items) {
		this.cellEditor.setItems(items);
	}
	
    public ILabelProvider getLabelProvider() {
        if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
        
        String a[] = { "xix", "cco"};
        
		return new ComboBoxLabelProvider(a);
    }

	
}
