package br.cnpem.gef.model;

import java.io.Serializable;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

public class Connection implements Comparable<Connection>, Serializable, IAdaptable {

	private static final long serialVersionUID = 1L;

	public static int EXTREMITY1 = 1, EXTREMITY2 = 2;
	
	protected Component extremity1, extremity2;
	protected RootComponent rootModel;
	protected int connectionPort;
	
	public int getConnectionPort() {
		return connectionPort;
	}

	public void setConnectionPort(int connectionPort) {
		this.connectionPort = connectionPort;
	}

	protected int host_interface_index;
	
	public void setRoot(RootComponent r) {
		this.rootModel = r;
		connectionPort = 10;
	}

	public RootComponent getRoot() {
		return this.rootModel;
	}


	public Connection() {
		this.extremity1 = null;
		this.extremity2 = null;
	}

	public Connection(Component extremity1, Component extremity2) {
		this.extremity1 = extremity1;
		this.extremity2 = extremity2;
	}

	public Component getExtremity1() {
		return extremity1;
	}

	public void setExtremity1(Component extremity1) {
		this.extremity1 = extremity1;
	}

	public Component getExtremity2() {
		return extremity2;
	}

	public void setExtremity2(Component extremity2) {
		this.extremity2 = extremity2;
	}
	
	public int getHost_interface() {
		return this.host_interface_index;
	}

	public void setHost_interface(int host_interface) {
		this.host_interface_index = host_interface;
	}

	@Override
	public int compareTo(Connection o) {

		if ((o.extremity1 == this.extremity1 && o.extremity2 == this.extremity2) ||
				(o.extremity1 == this.extremity2 && o.extremity2 == this.extremity1) )
			return 1;

		return 0;
	}

	public Host getHost() {
		
		Host h;
		if (this.extremity1 instanceof Host)
			h = (Host) this.extremity1;
		else h = (Host) this.extremity2;
		
		return h;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

}
