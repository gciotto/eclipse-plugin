package br.cnpem.gef.propertysource;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.cnpem.gef.model.Host;

public class HostPropertySource extends ComponentPropertySource {

	protected static final String PROPERTY_INTERFACE = "br.cnpem.gef.model.views.interface";
	private transient PropertyDescriptor netinterfaceDescriptor; 
	
	public HostPropertySource(Host host) {
		super(host);
	}
	
	private Host getHost() {
		return (Host) c;
	}
	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		super.getPropertyDescriptors();

		if (netinterfaceDescriptor == null) {

			netinterfaceDescriptor = new TextPropertyDescriptor(PROPERTY_INTERFACE, "Number of network interfaces");
			
			netinterfaceDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return Integer.toString(getHost().getMaxNInterface());
				}
				
			});

			this.propertyDescriptors.add(netinterfaceDescriptor);
		}

		IPropertyDescriptor[] result = new IPropertyDescriptor[this.propertyDescriptors.size()];

		return this.propertyDescriptors.toArray(result);

	}

	@Override
	public void setPropertyValue(Object id, Object value) {

		if (PROPERTY_INTERFACE.equals(id))
			getHost().setMaxNInterface(Integer.valueOf((String) value));

		super.setPropertyValue(id, value);
	}

	@Override
	public Object getPropertyValue(Object id) {

		if (PROPERTY_INTERFACE.equals(id))
			return Integer.toString(getHost().getMaxNInterface());

		return super.getPropertyValue(id);
	}

}
