package br.cnpem.gef.propertysource;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.cnpem.gef.model.NetworkInterface;

public class NetworkInterfacePropertySource implements IPropertySource {

	private NetworkInterface n;
	
	private IPropertyDescriptor[] propertyDescriptors;

	private static final String PROPERTY_NAME = "br.cnpem.gef.model.views.networkinterface.name";
	private static final String PROPERTY_PLUG = "br.cnpem.gef.model.views.networkinterface.plug";
	private static final String PROPERTY_DHCP = "br.cnpem.gef.model.views.networkinterface.dhcp";
	private static final String PROPERTY_IP = "br.cnpem.gef.model.views.networkinterface.ip";
	
	public NetworkInterfacePropertySource (NetworkInterface ninterface) {
		this.n = ninterface;
	}
	
	@Override
	public Object getEditableValue() {
		return null;
	}
	
	@Override
	public Object getPropertyValue(Object id) {

		if (PROPERTY_DHCP.equals(id))
			return n.isDHCP();

		if (PROPERTY_IP.equals(id))
			return n.getIp_address();

		if (PROPERTY_NAME.equals(id))
			return n.getName();

		if (PROPERTY_PLUG.equals(id))
			return n.getPlug();

		return null;
	}

	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void resetPropertyValue(Object id) {
		if (PROPERTY_DHCP.equals(id))
			n.setDHCP(false);

		if (PROPERTY_IP.equals(id))
			n.setIp_address("192.168.7.2");

		if (PROPERTY_NAME.equals(id))
			n.setName("eth0");

		if (PROPERTY_PLUG.equals(id))
			n.setPlug("Ethernet");

	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (PROPERTY_DHCP.equals(id))
			n.setDHCP((boolean) value);

		if (PROPERTY_IP.equals(id))
			n.setIp_address((String) value);

		if (PROPERTY_NAME.equals(id))
			n.setName((String) value);

		if (PROPERTY_PLUG.equals(id))
			n.setPlug((String) value);
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		if (this.propertyDescriptors == null) {

			PropertyDescriptor nameDescriptor = new TextPropertyDescriptor(PROPERTY_NAME, "Interface name (e.g. eth0)");
			nameDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return n.getName();
				}

			});

			PropertyDescriptor ipDescriptor = new TextPropertyDescriptor (PROPERTY_IP, "IP Address");
			ipDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return n.getIp_address();
				}
			});

			PropertyDescriptor plugDescriptor = new TextPropertyDescriptor (PROPERTY_PLUG, "Plug");
			plugDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return n.getPlug();
				}
			});


			this.propertyDescriptors = new IPropertyDescriptor[] { nameDescriptor, ipDescriptor, plugDescriptor };
		}

		return this.propertyDescriptors;
	}
}