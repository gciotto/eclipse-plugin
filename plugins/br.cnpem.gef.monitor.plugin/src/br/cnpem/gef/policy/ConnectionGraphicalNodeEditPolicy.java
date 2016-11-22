package br.cnpem.gef.policy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import br.cnpem.gef.command.ConnectionCreateCommand;
import br.cnpem.gef.model.Component;
import br.cnpem.gef.model.Connection;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.RootComponent;
import br.cnpem.gef.model.Switch;

public class ConnectionGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(CreateConnectionRequest arg0) {
		ConnectionCreateCommand _c = (ConnectionCreateCommand) arg0.getStartCommand();
		_c.setTarget((Component) getHost().getModel());
		return _c;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest arg0) {
		ConnectionCreateCommand _c = new ConnectionCreateCommand();
		_c.setSource((Component) getHost().getModel());
		_c.setConnection((Connection) arg0.getNewObject());
		_c.setRootModel((RootComponent) getHost().getParent().getModel());

		arg0.setStartCommand(_c);
		return _c;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void showTargetConnectionFeedback(DropRequest request) {

		super.showTargetConnectionFeedback(request);

		IFigure figure = getHostFigure();
		Object model = getHost().getModel();
		RootComponent root = (RootComponent) getHost().getParent().getModel();

		if (model instanceof Host ) {

			if (root.getConnectionsWith((Component) model).size() < ((Host) model).getNetworkInterfaces().size())

				figure.setBackgroundColor(ColorConstants.lightGreen);
			else 
				figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

		}
		else if (model instanceof Switch) {


			if (root.getConnectionsWith((Component) model).size() < ((Switch)model).getNumberPorts()) 
				figure.setBackgroundColor(ColorConstants.lightGreen);
			else 
				figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

		}

		else figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

		figure.setOpaque(true);
	}

	@Override
	public void showSourceFeedback(Request request) {


		super.showSourceFeedback(request);

		if (request instanceof CreateConnectionRequest) {

			IFigure figure = getHostFigure();
			Object model = getHost().getModel();
			RootComponent root = (RootComponent) getHost().getParent().getModel();

			if (model instanceof Host ) {

				if (root.getConnectionsWith((Component) model).size() < ((Host) model).getNetworkInterfaces().size())

					figure.setBackgroundColor(ColorConstants.lightGreen);
				else 
					figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

			}
			else if (model instanceof Switch) {


				if (root.getConnectionsWith((Component) model).size() < ((Switch)model).getNumberPorts()) 
					figure.setBackgroundColor(ColorConstants.lightGreen);
				else 
					figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

			}

			else figure.setBackgroundColor(new Color(Display.getCurrent(), new RGB(255, 153, 153)));

			figure.setOpaque(true);
		}
	}

	@Override
	public void eraseSourceFeedback(Request request) {
		getHostFigure().setBackgroundColor(ColorConstants.white);
		super.eraseSourceFeedback(request);
	}

	@Override
	protected void eraseTargetConnectionFeedback(DropRequest request) {
		getHostFigure().setBackgroundColor(ColorConstants.white);
		super.eraseTargetConnectionFeedback(request);
	}
}
