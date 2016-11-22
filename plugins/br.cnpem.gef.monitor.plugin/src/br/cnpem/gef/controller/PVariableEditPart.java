package br.cnpem.gef.controller;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.figure.PVariableFigure;
import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.PVariable;
import br.cnpem.gef.policy.PVariableDeleteEditPolicy;

public class PVariableEditPart extends AbstractGraphicalEditPart {

	private Observer _observer;
	
	public PVariableEditPart() {
		this._observer = new PVariableObserver();
	}
	
	@Override
	protected IFigure createFigure() {
		return new PVariableFigure();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new PVariableDeleteEditPolicy());
		
	}
	
	@Override
	protected void refreshVisuals() {
		
		PVariableFigure _figure = (PVariableFigure) getFigure();
		PVariable _model = (PVariable) getModel();
		
		HostEditPart parent = (HostEditPart) getParent();
		Host _parent_model = (Host) parent.getModel();
		
		_figure.setName(_model.getName());
		
		int i =  _parent_model.getPVs().indexOf(_model);
		
		if (i >= 0 && !_parent_model.getCollapse()) {
			getFigure().setVisible(true);
			parent.setLayoutConstraint(this, _figure, new Rectangle(new Point(0 , 5 + (_parent_model.getNetworkInterfaces().size() + i) * 35), 
					new Dimension(PVariableFigure.MIN_WIDTH, PVariableFigure.MIN_HEIGHT)));
		}
		else if (_parent_model.getCollapse()) getFigure().setVisible(false);
	}
	
	@Override
	public void activate() {

		if (!isActive())
			((PVariable) getModel()).addObserver(_observer);

		super.activate();
	}

	@Override
	public void deactivate() {

		if (isActive())
			((PVariable) getModel()).deleteObserver(_observer);

		super.deactivate();
	}
	
	public class PVariableObserver implements Observer {

		@Override
		public void update(Observable o, Object arg) {
			refreshVisuals();
		}
		
	}
	
	@Override
	public Object getAdapter(Class key) {
		
		if (key == IPropertySource.class)
			return ((PVariable) getModel()).getAdapter(key);
		
		return super.getAdapter(key);
	}
	
}
