package br.cnpem.gef.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.contentproviders.TreeContentProvider;
import br.cnpem.gef.model.Component;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.Switch;
import br.cnpem.gef.propertysource.ComponentPropertySource;
import br.cnpem.gef.propertysource.HostPropertySource;
import br.cnpem.gef.propertysource.SwitchPropertySource;

public class ComponentAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		
		if (adapterType == IPropertySource.class) {
			if (adaptableObject instanceof Host)
				return new HostPropertySource((Host) adaptableObject);
			
			else if (adaptableObject instanceof Switch)
				return new SwitchPropertySource((Switch) adaptableObject);
		
			else return new ComponentPropertySource((Component) adaptableObject);
		}

		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {IPropertySource.class};
	}

}
