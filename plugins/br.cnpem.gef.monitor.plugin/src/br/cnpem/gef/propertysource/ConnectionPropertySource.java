package br.cnpem.gef.propertysource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.cnpem.gef.model.Connection;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;

public class ConnectionPropertySource implements IPropertySource {

	private Connection c;
	private static final String PROPERTY_PORT = "br.cnpem.gef.model.views.connection.port";
	private static final String PROPERTY_INTERFACE = "br.cnpem.gef.model.views.connection.interface";

	private List<IPropertyDescriptor> propertyDescriptors;
	private ComboBoxPropertyDescriptor interfaceDescriptor;
	
	public ConnectionPropertySource(Connection con) {
		this.c = con;		
	}
	
	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		if (this.propertyDescriptors == null) {

			PropertyDescriptor portDescriptor = new TextPropertyDescriptor(PROPERTY_PORT, "Port");
			portDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return Integer.toString(c.getConnectionPort());
				}

			});

			updateItemsPropertyDescriptor();
			
			this.propertyDescriptors = new ArrayList<IPropertyDescriptor>();
			this.propertyDescriptors.add(portDescriptor);
			this.propertyDescriptors.add(interfaceDescriptor);
		}
		
		IPropertyDescriptor[] result = new IPropertyDescriptor[this.propertyDescriptors.size()];

		return this.propertyDescriptors.toArray(result);// TODO Auto-generated method stub
	}

	public void updateItemsPropertyDescriptor() {
		
		Host h = c.getHost();
	
		String rep[] = new String[h.getNetworkInterfaces().size()]; 
		
		int i = 0;
		for (NetworkInterface n : h.getNetworkInterfaces()) 
				rep[i++] = n.toString();
		
		this.interfaceDescriptor = new ComboBoxPropertyDescriptor(PROPERTY_INTERFACE, "Interface", rep);
	}
	
	@Override
	public Object getPropertyValue(Object id) {
		
		if (PROPERTY_PORT.equals(id)) 
			return Integer.toString(c.getConnectionPort());
		
		if (PROPERTY_INTERFACE.equals(id))
			return c.getHost_interface();
		
		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
		
		if (PROPERTY_PORT.equals(id)) 
			c.setConnectionPort(10);
		
		if (PROPERTY_INTERFACE.equals(id)) 
			c.setHost_interface(0);

	}
	
	@Override
	public void setPropertyValue(Object id, Object value) {
		
		if (PROPERTY_PORT.equals(id)) 
			c.setConnectionPort(Integer.valueOf((String)value));
		
		if (PROPERTY_INTERFACE.equals(id)){
			
			if (c.getHost_interface() < c.getHost().getNetworkInterfaces().size())
				c.getHost().getNetworkInterfaces().get(c.getHost_interface()).setInUse(false);
			
			c.setHost_interface((int) value);
			c.getHost().getNetworkInterfaces().get(c.getHost_interface()).setInUse(true);
			
		}

	}
}
