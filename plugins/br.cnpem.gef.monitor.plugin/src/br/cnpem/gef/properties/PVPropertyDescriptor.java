package br.cnpem.gef.properties;

import org.csstudio.autocomplete.ui.AutoCompleteTypes;
import org.csstudio.autocomplete.ui.AutoCompleteUIHelper;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

public class PVPropertyDescriptor extends PropertyDescriptor {

	public PVPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		
		CellEditor editor = AutoCompleteUIHelper.createAutoCompleteTextCellEditor(
                parent, AutoCompleteTypes.PV);
		
		if (getValidator() != null)
			editor.setValidator(getValidator());
		
		return editor;
	}
	
}
