package br.cnpem.gef.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.model.NetworkInterface;
import br.cnpem.gef.propertysource.NetworkInterfacePropertySource;

public class NetworkInterfaceAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		
		if (adapterType == IPropertySource.class) 
			return new NetworkInterfacePropertySource((NetworkInterface) adaptableObject);
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
