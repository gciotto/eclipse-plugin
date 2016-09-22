package br.cnpem.gef.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.CompoundSnapToHelper;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.SnapFeedbackPolicy;
import org.eclipse.ui.views.properties.IPropertySource;

import br.cnpem.gef.figure.ComponentFigure;
import br.cnpem.gef.model.Component;
import br.cnpem.gef.model.Connection;
import br.cnpem.gef.model.RootComponent;
import br.cnpem.gef.policy.ConnectionGraphicalNodeEditPolicy;
import br.cnpem.gef.policy.GenericComponentEditPolicy;

public class GenericComponentEditPart extends AbstractGraphicalEditPart implements NodeEditPart, IAdaptable {

	protected GenericComponentObserver _observer;

	public GenericComponentEditPart() {
		super();
		_observer = new GenericComponentObserver();
		
	}

	@Override
	protected IFigure createFigure() {
		return new ComponentFigure();
	}


	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new GenericComponentEditPolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, new ConnectionGraphicalNodeEditPolicy());

		installEditPolicy("Snap Feedback", new SnapFeedbackPolicy());
	}

	@Override
	protected void refreshVisuals() {

		ComponentFigure _figure = (ComponentFigure) getFigure();

		Component _model = (Component) getModel();
		
		_figure.setToolTipMessage(_model.toString());

		_figure.getImageIcon().setImage(_model.getIconRoot() );

		_figure.setComponentName(_model.getName());

		RootEditPart parent = (RootEditPart) getParent();
		
		if (!_model.getAutoScale())
			parent.setLayoutConstraint(this, _figure, new Rectangle(_model.getScreenLocation().x, _model.getScreenLocation().y,
					_model.getDimensions().width, _model.getDimensions().height));
		else {
			
			parent.setLayoutConstraint(this, _figure, new Rectangle(_model.getScreenLocation().x, _model.getScreenLocation().y,
					_figure.getPreferredSize().width, _figure.getPreferredSize().height));
						
		}
	}

	@Override
	public void activate() {

		if (!isActive())
			((Component)getModel()).addObserver(_observer);

		super.activate();
	}

	@Override
	public void deactivate() {

		if (isActive())
			((Component)getModel()).deleteObserver(_observer);

		super.deactivate();
	}

	@Override
	protected List<Connection> getModelSourceConnections() {
		return ((RootComponent)getParent().getModel()).getConnectionsWithAt((Component) getModel(), Connection.EXTREMITY1);
	}

	@Override
	protected List<Connection> getModelTargetConnections() {
		return ((RootComponent)getParent().getModel()).getConnectionsWithAt((Component) getModel(), Connection.EXTREMITY2);
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(ConnectionEditPart arg0) {
		return ((ComponentFigure)getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getSourceConnectionAnchor(Request arg0) {
		return ((ComponentFigure)getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(ConnectionEditPart arg0) {
		return ((ComponentFigure)getFigure()).getConnectionAnchor();
	}

	@Override
	public ConnectionAnchor getTargetConnectionAnchor(Request arg0) {
		return ((ComponentFigure)getFigure()).getConnectionAnchor();
	}


	public class GenericComponentObserver implements Observer {

		@Override
		public void update(Observable arg0, Object arg1) {
			refreshVisuals();
			refreshSourceConnections();
			refreshTargetConnections();
			refreshChildren();
		}
	}

	@Override
	public Object getAdapter(Class key) {
		
		if (key.equals(IPropertySource.class)) {
			return ((Component) getModel()).getAdapter(key);
		}
		
		if (key == SnapToHelper.class) {

			List<SnapToHelper> helpers = new ArrayList<SnapToHelper>();
			if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED))) 
				helpers.add(new SnapToGeometry(this));
			
			if (Boolean.TRUE.equals(getViewer().getProperty(SnapToGrid.PROPERTY_GRID_ENABLED)))
				helpers.add(new SnapToGrid(this));
			
			if(helpers.size() == 0) return null;
			
			else return new CompoundSnapToHelper(helpers.toArray(new SnapToHelper[0]));
		}

		return super.getAdapter(key);
	}

}
