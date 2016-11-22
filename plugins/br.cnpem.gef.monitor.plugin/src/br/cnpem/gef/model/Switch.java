package br.cnpem.gef.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import br.cnpem.gef.golpe.CnpemPlugin;

public class Switch extends Component {

	private static final long serialVersionUID = 1L;
	protected int numberPorts;
	
	public Switch(Place location, String name) {
		super(location, name);

		//this.iconRoot = "/home/gciotto/eclipse-CSS4_4-LUNA/workspace/br.cnpem.gef.golpe/icon/switch.png";

		this.iconAddress = CnpemPlugin.getDefault().getBundle().getResource("icon/switch_2.png").getPath(); 
		this.iconRoot = CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.SWITCH_ID);

		this.numberPorts = 8;
	}

	@Override
	public Image getIconRoot() {
		
		if (iconRoot == null) 
			if (iconChanged)
				this.iconRoot = new Image(Display.getCurrent(), this.iconAddress);
			else this.iconRoot = CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.SWITCH_ID);

		return iconRoot;
	}


	public int getNumberPorts() {
		return numberPorts;
	}

	public void setNumberPorts(int numberPorts) {
		this.numberPorts = numberPorts;
		update();
	}


	@Override
	public String toString() {
		
		return "Switch: " + this.name;
	}


}
