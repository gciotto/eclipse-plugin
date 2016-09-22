package br.cnpem.gef.model;


import java.io.Serializable;
import java.util.Observable;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

public class PVariable extends Observable implements IAdaptable, Serializable {

	protected String name, address;
	private Host parent;

	public PVariable(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public PVariable() {
		this("Generic Name", "192.168.7.2");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		update();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		update();
	}

	@Override
	public Object getAdapter(Class adapter) {
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}

	public void setParent(Host object) {
		this.parent = object;		
	}
	
	public Host getParent() {
		return parent;
	}
	
	public void update (){
		this.setChanged();
		this.notifyObservers();
	}
	
	@Override
	public String toString() {
		return "PV: " + this.getName();
	}
	
}
