package br.cnpem.gef.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Host extends Component {

	private static final long serialVersionUID = 1L;
	protected List<NetworkInterface> n_interfaces;
	protected List<PVariable> pvs;
	protected int number_interfaces;
	protected boolean collapseAll;


	public Host(Place location, String name) {
		super(location, name);
		this.n_interfaces = new ArrayList<NetworkInterface>();
		this.pvs = new ArrayList<PVariable>();	
		this.number_interfaces = 1;
		this.collapseAll = false;

	}	
	
	public boolean getCollapseAll() {
		return collapseAll;
	}



	public void setCollapseAll(boolean collapseAll) {
		this.collapseAll = collapseAll;
		update();
	}



	public void setMaxNInterface(int n){
		this.number_interfaces = n;
		update();
	}

	public int getMaxNInterface(){
		return number_interfaces;
	}

	public void removePV(PVariable pv) {
		this.pvs.remove(pv);
		pv.setParent(null);
		update();
	}
	
	public void addPV(PVariable pv) {
		this.pvs.add(pv);
		pv.setParent(this);
		update();
	}
	
	public void addNetworkInterface(NetworkInterface n) {		
		this.n_interfaces.add(n);
		n.setParent(this);
		update();
	}

	public void removeNetworkInterface(NetworkInterface n) {
		this.n_interfaces.remove(n);
		n.setParent(null);
		update();
	}

	public List<NetworkInterface> getNetworkInterfaces () {
		return this.n_interfaces;
	}

	
	@Override
	public String toString() {
		String result = "";
		
		result += "Name: " + this.getName() +"\n";
		result += "Network Interfaces: \n";
		
		for (NetworkInterface i : this.getNetworkInterfaces()) 
			result += i.toString() + "\n";		
						
		return result;
	}

	public List<PVariable> getPVs() {
		return this.pvs;
	}

}
