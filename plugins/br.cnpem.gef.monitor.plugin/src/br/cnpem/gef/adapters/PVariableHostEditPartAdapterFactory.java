package br.cnpem.gef.adapters;

import org.csstudio.csdata.ProcessVariable;
import org.eclipse.core.runtime.IAdapterFactory;

import br.cnpem.gef.controller.PVariableEditPart;
import br.cnpem.gef.model.PVariable;

public class PVariableHostEditPartAdapterFactory implements IAdapterFactory {

	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		
		PVariableEditPart p = (PVariableEditPart) adaptableObject;
		
		if (adapterType == ProcessVariable.class)
			return ((PVariable) p.getModel()).getAdapter(adapterType);
		
		return null;
	}

	@Override
	public Class[] getAdapterList() {
		return new Class[] {ProcessVariable.class};
	}

}
