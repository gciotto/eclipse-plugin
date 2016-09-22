package br.cnpem.gef.model;

import org.eclipse.gef.requests.CreationFactory;

public class PVItemFactory implements CreationFactory {

	@Override
	public Object getNewObject() {
		return new PVariable();
	}

	@Override
	public Object getObjectType() {
		return PVariable.class;
	}

}
