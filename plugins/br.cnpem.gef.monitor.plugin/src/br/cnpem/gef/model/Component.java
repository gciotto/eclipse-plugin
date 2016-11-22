package br.cnpem.gef.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import br.cnpem.gef.golpe.CnpemPlugin;

public class Component extends Observable implements Comparable<Component>, Serializable, IAdaptable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Place location;
	protected String name, iconAddress;
	protected transient Image iconRoot;
	protected boolean iconChanged, shouldCollapseIcons;

	protected List<Connection> connections;
		
	protected Point screenLocation;
	protected Dimension dimension;
	protected boolean autoScale; 
	
	public Component(Place location, String name) {
		super();
		this.location = location;
		this.name = name;
		//this.iconRoot = "/home/gciotto/eclipse-CSS4_4-LUNA/workspace/br.cnpem.gef.golpe/icon/generic.png";
		this.iconAddress = CnpemPlugin.getDefault().getBundle().getResource("icon/generic.png").getPath(); 
		this.iconRoot = CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.GENERIC_ID);
		this.iconChanged = false;
		this.shouldCollapseIcons = false;
		
		this.connections = new ArrayList<Connection>();
		
		this.screenLocation = new Point();
		this.dimension = new Dimension (200, 150);
		
		this.autoScale = true;
	}
	
	public void setAutoScale(boolean autoscale) {
		this.autoScale = autoscale;
		this.update();
	}
	
	public boolean getAutoScale() {
		return this.autoScale;
	}
	
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public void update(Object arg) {
		this.setChanged();
		this.notifyObservers(arg);
	}
	
	public Dimension getDimensions() {
		return dimension;
	}

	public void setDimensions(Dimension dimension) {
		this.dimension = dimension;
		this.update();
	}

	public Point getScreenLocation() {
		return screenLocation;
	}

	public void setScreenLocation(Point screenLocation) {
		this.screenLocation = screenLocation;
		this.update();
	}

	public void setLocation(Place location) {
		this.location = location;
		this.update();
	}

	public void setName(String name) {
		this.name = name;
		this.update();
	}

	public void setIconRoot(Image iconRoot) {
		this.iconRoot = iconRoot;
		this.update();
	}

	public Place getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}
	
	public Image getIconRoot() {
		
		if (iconRoot == null) 
			if (iconChanged)
				this.iconRoot = new Image(Display.getCurrent(), this.iconAddress);
			else this.iconRoot = CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.GENERIC_ID);
			
		return iconRoot;
	}

	public Point getConstraints() {
		return this.screenLocation;
	}
	
	public void addGolpeConnection(Connection nConn) {
		this.connections.add(nConn);
		this.update();
	}
	
	
	@Override
	public int compareTo(Component o) {
		return this.name.compareTo(o.getName());
	}

	@Override
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public String getIconAddress() {
		return this.iconAddress;
	}

	public void setIconChanged(boolean b) {
		this.autoScale = b;
		update();
	}

	public void setIconAddress(String value) {
		this.iconAddress = value;
		update();	
	}

	public boolean getCollapse() {
		return shouldCollapseIcons;
	}

	public void setCollapse(boolean b) {
		shouldCollapseIcons = b;
		update();
	}
}
