package br.cnpem.gef.adapters;

import org.csstudio.csdata.ProcessVariable;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.model.PVariable;
import br.cnpem.gef.propertysource.PVariablePropertySource;

public class PVariableAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		
		PVariable pv =(PVariable) adaptableObject;
		
		if (adapterType == IPropertySource.class)
			return new PVariablePropertySource(pv);
		
		if (adapterType == ProcessVariable.class)
			return new ProcessVariable(pv.getName());
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		
		return new Class[] {IPropertySource.class, ProcessVariable.class};
	}

}
