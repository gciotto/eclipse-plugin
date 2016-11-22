package br.cnpem.gef.model;

import java.io.Serializable;
import java.util.Observable;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

public class NetworkInterface extends Observable implements Serializable, IAdaptable {

	private static final long serialVersionUID = 1L;

	private String ip_address, plug, name;
	private boolean isDHCP, inUse;
	private Host parent;


	public NetworkInterface(String ip_address, String name, String plug, boolean isDHCP) {
		super();
		this.ip_address = ip_address;
		this.plug = plug;
		this.isDHCP = isDHCP;
		this.name = name;
		this.inUse = false;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
		this.update();
	}
	
	public boolean getInUse() {
		return this.inUse;
	}
	
	public void update (){
		this.setChanged();
		this.notifyObservers();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		update();
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
		update();
	}

	public String getPlug() {
		return plug;
	}

	public void setPlug(String plug) {
		this.plug = plug;
		update();
	}

	public boolean isDHCP() {
		return isDHCP;
	}

	public void setDHCP(boolean isDHCP) {
		this.isDHCP = isDHCP;
		update();
	}

	
	public Host getParent() {
		return parent;
	}

	public void setParent(Host parent) {
		this.parent = parent;
		update();
	}

	@Override
	public String toString() {
		return "IP: " + this.getIp_address() + " Interface: " + this.getName() + " Plug: " + this.getPlug();
	}

	@Override
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

}
