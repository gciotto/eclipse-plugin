package br.cnpem.gef.controller;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import br.cnpem.gef.model.Component;
import br.cnpem.gef.model.Connection;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;
import br.cnpem.gef.model.PVariable;
import br.cnpem.gef.model.RootComponent;


public class ComponentEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart iContext, Object iModel) {
		System.out.println("Called GraphicalPartFactory.createEditPart("
				+ iContext + "," + iModel + ")");
 
		EditPart editPart = null;
		if (iModel instanceof RootComponent)	
			editPart = new RootEditPart();
		
		else if (iModel instanceof Host)
			editPart = new HostEditPart();
		
		else if (iModel instanceof NetworkInterface)
			editPart = new NetworkInterfaceEditPart();
		
		else if (iModel instanceof PVariable)
			editPart = new PVariableEditPart();
		
		else if (iModel instanceof Component) 
			editPart = new GenericComponentEditPart();
 
		else if (iModel instanceof Connection)
			editPart = new ConnectionEditPart();
		
		if (editPart != null)
			editPart.setModel(iModel);
		
		
		return editPart;
	}

}
