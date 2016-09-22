package br.cnpem.gef.model;

import org.eclipse.gef.requests.CreationFactory;

public class ConnectionFactory implements CreationFactory {

	@Override
	public Object getNewObject() {
		return new Connection();
	}

	@Override
	public Object getObjectType() {
		return Connection.class;
	}

}
