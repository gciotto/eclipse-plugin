package br.cnpem.gef.policy;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ComponentEditPolicy;
import org.eclipse.gef.requests.GroupRequest;

import br.cnpem.gef.command.NetworkInterfaceDeleteCommand;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;

public class NetworkInterfaceDeleteEditPolicy extends ComponentEditPolicy {

	@Override
	protected Command createDeleteCommand(GroupRequest request) {
		
		NetworkInterfaceDeleteCommand _c = new NetworkInterfaceDeleteCommand();
		
		_c.setNetworkInterface((NetworkInterface) getHost().getModel());
		_c.setHostRoot((Host) getHost().getParent().getModel());
		
		return _c;
	}
	
}
