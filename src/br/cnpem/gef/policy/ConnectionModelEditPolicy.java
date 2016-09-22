package br.cnpem.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ConnectionEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import br.cnpem.gef.command.ConnectionDeleteCommand;
import br.cnpem.gef.model.Connection;

public class ConnectionModelEditPolicy extends ConnectionEditPolicy {

	@Override
	protected Command getDeleteCommand(GroupRequest arg0) {
		ConnectionDeleteCommand _c = new ConnectionDeleteCommand();
		_c.setConnection((Connection) getHost().getModel());
		return _c;
	}

}
