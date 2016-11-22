package br.cnpem.gef.policy;



import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import br.cnpem.gef.command.NetworkInterfaceCreateCommand;
import br.cnpem.gef.command.PVariableCreateCommand;
import br.cnpem.gef.figure.HostFigure;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;
import br.cnpem.gef.model.PVariable;

public class HostXYLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest _request) {

		Command _command = null;

		if (_request.getNewObject() instanceof NetworkInterface) {

			NetworkInterfaceCreateCommand _c = new NetworkInterfaceCreateCommand();
			_c.setNetworkInterface( (NetworkInterface) _request.getNewObject());
			_c.setHostRoot((Host) getHost().getModel());

			_command = _c;
		}
		
		if (_request.getNewObject() instanceof PVariable) {
			
			PVariableCreateCommand _c = new PVariableCreateCommand();
			_c.setPV((PVariable) _request.getNewObject());
			_c.setHostRoot((Host) getHost().getModel());
			
			_command = _c;
		}

		return _command;
	}

	@Override
	protected void showLayoutTargetFeedback(Request request) {

		
		if (request instanceof CreateRequest) {

			CreateRequest _c = (CreateRequest) request;
			HostFigure figure = (HostFigure) getHostFigure();

			if (getHostFigure() instanceof HostFigure ) {

				Host model = (Host) getHost().getModel();
				
				if(_c.getNewObject() instanceof NetworkInterface ||
						_c.getNewObject() instanceof PVariable) {

					if ((_c.getNewObject() instanceof NetworkInterface &&
							model.getNetworkInterfaces().size() < model.getMaxNInterface()) ||
						(_c.getNewObject() instanceof PVariable)) 
						figure.setBackgroundColor(ColorConstants.lightGreen);
					else 
						figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

					figure.setOpaque(true);
				}
				else figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));
			}
			else figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));
		}

	}


	@Override
	protected void eraseLayoutTargetFeedback(Request request) {
		
		if(getHostFigure() instanceof HostFigure) {
			HostFigure figure = (HostFigure) getHostFigure();
			figure.setBackgroundColor(ColorConstants.white);
		}
	}

}
