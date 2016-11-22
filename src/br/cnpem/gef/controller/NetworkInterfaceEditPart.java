package br.cnpem.gef.controller;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.figure.NetworkInterfaceFigure;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;
import br.cnpem.gef.policy.NetworkInterfaceDeleteEditPolicy;

public class NetworkInterfaceEditPart extends AbstractGraphicalEditPart {

	NetworkInterfaceObserver _observer;
	
	public NetworkInterfaceEditPart() {
		super();
		_observer = new NetworkInterfaceObserver();
	}
	
	@Override
	protected IFigure createFigure() {
		IFigure f = new NetworkInterfaceFigure();
		return f;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new NetworkInterfaceDeleteEditPolicy());
	}
	
	@Override
	protected void refreshVisuals() {
		
		NetworkInterfaceFigure _figure = (NetworkInterfaceFigure) getFigure();
		
		NetworkInterface _model = (NetworkInterface) getModel();
		
		_figure.setAddress(_model.getIp_address());
		_figure.setName(_model.getName());
		_figure.setIcon(NetworkInterfaceFigure.ETHERNET);
		
		HostEditPart parent = (HostEditPart) getParent();
		Host parent_model = (Host) parent.getModel();
				
		int i = parent_model.getNetworkInterfaces().indexOf(this.getModel());
		
		
		if (i >= 0 && !parent_model.getCollapse()) {
			getFigure().setVisible(true);
			parent.setLayoutConstraint(this, _figure, new Rectangle(new Point(0 , 5 + i * 35), 
					new Dimension(NetworkInterfaceFigure.MIN_WIDTH, NetworkInterfaceFigure.MIN_HEIGHT)));
		}
		else  {
			getFigure().setVisible(false);
		}
			
	}
	
	@Override
	public void activate() {

		if (!isActive())
			((NetworkInterface)getModel()).addObserver(_observer);

		super.activate();
	}

	@Override
	public void deactivate() {

		if (isActive())
			((NetworkInterface)getModel()).deleteObserver(_observer);

		super.deactivate();
	}
	
	public class NetworkInterfaceObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			refreshVisuals();
		}
		
	}
	
	@Override
	public Object getAdapter(Class key) {
		if (key.equals(IPropertySource.class)) 
			return ((NetworkInterface) getModel()).getAdapter(key);
		
		return super.getAdapter(key);
	}


}
