package br.cnpem.gef.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.model.Connection;
import br.cnpem.gef.propertysource.ConnectionPropertySource;

public class ConnectionAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		
		if (adapterType == IPropertySource.class) 
			return new ConnectionPropertySource((Connection) adaptableObject);
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
