package br.cnpem.gef.propertysource;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.cnpem.gef.model.Switch;

public class SwitchPropertySource extends ComponentPropertySource {

	protected transient TextPropertyDescriptor nPortsDescriptor;
	protected static final String PROPERTY_NPORTS = "br.cnpem.gef.model.views.switch.num_ports";
	
	public SwitchPropertySource(Switch component) {
		super(component);
	}
	
	private Switch getSwitch() {
		return (Switch) c;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		super.getPropertyDescriptors();

		if (nPortsDescriptor == null) {

			nPortsDescriptor = new TextPropertyDescriptor(PROPERTY_NPORTS, "Number of network interfaces");

			nPortsDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return Integer.toString(getSwitch().getNumberPorts());
				}

			});

			this.propertyDescriptors.add(nPortsDescriptor);
		}

		IPropertyDescriptor[] result = new IPropertyDescriptor[this.propertyDescriptors.size()];

		return this.propertyDescriptors.toArray(result);

	}

	@Override
	public void setPropertyValue(Object id, Object value) {

		if (PROPERTY_NPORTS.equals(id))
			 getSwitch().setNumberPorts(Integer.valueOf((String)value));

		super.setPropertyValue(id, value);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (PROPERTY_NPORTS.equals(id))
			return Integer.toString(getSwitch().getNumberPorts());

		return super.getPropertyValue(id);
	}

	@Override
	public void resetPropertyValue(Object id) {

		if (PROPERTY_NPORTS.equals(id))
			getSwitch().setNumberPorts(16);

		super.resetPropertyValue(id);
	}
	
}
