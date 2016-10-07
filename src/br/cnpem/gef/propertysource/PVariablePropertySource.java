package br.cnpem.gef.propertysource;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.cnpem.gef.model.PVariable;
import br.cnpem.gef.properties.PVPropertyDescriptor;

public class PVariablePropertySource implements IPropertySource {

	private PVariable pv;
	private IPropertyDescriptor[] propertyDescriptors;

	private static final String PROPERTY_NAME = "br.cnpem.gef.model.views.pv.name";
	
	public PVariablePropertySource(PVariable pv) {
		this.pv = pv;
	}
	
	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		if (this.propertyDescriptors == null) {

			PropertyDescriptor nameDescriptor = new PVPropertyDescriptor(PROPERTY_NAME, "Name:");
			nameDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return pv.getName();
				}

			});

			this.propertyDescriptors = new IPropertyDescriptor[] { nameDescriptor };
		}

		return this.propertyDescriptors;
	}

	@Override
	public Object getPropertyValue(Object id) {
		
		if (id.equals(PROPERTY_NAME))
			return pv.getName();
		
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
		
		if (id.equals(PROPERTY_NAME))
			pv.setName("Generic Name");
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		
		if (id.equals(PROPERTY_NAME))
			pv.setName((String) value);

	}

}
