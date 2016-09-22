package br.cnpem.gef.menu;

import java.util.List;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import br.cnpem.gef.command.NetworkInterfaceCreateCommand;
import br.cnpem.gef.controller.GenericComponentEditPart;
import br.cnpem.gef.controller.HostEditPart;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;

public class AddNetwotkInterfaceAction extends SelectionAction {

    public static final String ADD_NETWORK_INTERFACE = "AddNetworkInterface";
    Request request;
    
	public AddNetwotkInterfaceAction(IWorkbenchPart part) {
		super(part);
		setId(ADD_NETWORK_INTERFACE);
		setText("Add new Network Interface");
	}

	@Override
	public void run() {
		List<GenericComponentEditPart> editParts = getSelectedObjects();
        CompoundCommand compoundCommand = new CompoundCommand();
        for(GenericComponentEditPart nodeEditPart : editParts) {
        	if (nodeEditPart instanceof HostEditPart) {
        		NetworkInterfaceCreateCommand command = new NetworkInterfaceCreateCommand();
        		command.setHostRoot((Host) nodeEditPart.getModel());
        		command.setNetworkInterface(new NetworkInterface("192.168.7.2", "usb0", "USB", false));
        		compoundCommand.add(command);
        	}
        }
  
        execute(compoundCommand);
	}
	
	@Override
	protected boolean calculateEnabled() {
		if(getSelectedObjects().isEmpty())
            return false;

		for (Object selectedObject : getSelectedObjects())
			if (selectedObject instanceof HostEditPart)
				if (((Host)((HostEditPart) selectedObject).getModel()).getNetworkInterfaces().size() < 
						((Host)((HostEditPart) selectedObject).getModel()).getMaxNInterface())
				return true;
		
		return false;
	}

}
