package br.cnpem.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import br.cnpem.gef.command.PVariableDeleteCommand;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.PVariable;

public class PVariableDeleteEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest deleteRequest) {
		PVariableDeleteCommand c = new PVariableDeleteCommand();
		
		c.setHostRoot((Host) getHost().getParent().getModel());
		c.setPV((PVariable) getHost().getModel());
		
		return c;
	}
	
}
