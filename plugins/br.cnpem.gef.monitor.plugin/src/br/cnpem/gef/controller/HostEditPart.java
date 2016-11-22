package br.cnpem.gef.controller;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;

import br.cnpem.gef.figure.HostFigure;
import br.cnpem.gef.figure.NetworkInterfaceFigure;
import br.cnpem.gef.figure.PVariableFigure;
import br.cnpem.gef.model.Component;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.policy.HostXYLayoutEditPolicy;

public class HostEditPart extends GenericComponentEditPart {
	
	@Override
	public IFigure getContentPane() {
		return ((HostFigure) getFigure()).interface_container;
	}
	
	@Override
	protected IFigure createFigure() {
		return new HostFigure();
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new HostXYLayoutEditPolicy());
	}
	
	@Override
	public List getModelChildren() {
		List<Object> l = new ArrayList<Object>();
	
		Host h = (Host) getModel();
		
		l.addAll(h.getNetworkInterfaces());
		l.addAll(h.getPVs());
		
		return l;
	}
	
	@Override
	protected void refreshVisuals() {
		
		super.refreshVisuals();
		
		Host _model = (Host) getModel();
		
		RootEditPart parent = (RootEditPart) getParent();
		
		HostFigure _figure = (HostFigure) getFigure();
		
		if (_model.getAutoScale()) {
			
			int height = 70, 
				width = _figure.getPreferredSize().width;
			
			if (!_model.getCollapse()) {
			
				height += (_model.getNetworkInterfaces().size() + _model.getPVs().size())*35;
				
				if (!_model.getNetworkInterfaces().isEmpty())
					width = Math.max(width, NetworkInterfaceFigure.MIN_WIDTH + 30);
				
				if (!_model.getPVs().isEmpty())
					width = Math.max(width, PVariableFigure.MIN_WIDTH + 30);
			}
			
			parent.setLayoutConstraint(this, _figure, new Rectangle(_model.getScreenLocation().x, _model.getScreenLocation().y,
					width, height));
			
			_model.getDimensions().width = width;
			_model.getDimensions().height = height;
		}
		
		for (Object a : this.getChildren()) {
			if (a instanceof NetworkInterfaceEditPart)
				((NetworkInterfaceEditPart) a).refreshVisuals();
			else if (a instanceof PVariableEditPart)
				((PVariableEditPart) a).refreshVisuals();
		}
		
	}
}


