package br.cnpem.gef.model;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import br.cnpem.gef.golpe.CnpemPlugin;

public class BBB extends Host {

	private static final long serialVersionUID = 1L;
	protected static final String ICON_DEFAULT = "/home/gciotto/eclipse-CSS4_4-LUNA/workspace/br.cnpem.gef.golpe/icon/bbb_board.png";
	protected static final String NAME_DEFAULT = "New BBB";
	
	public BBB(Place location, String name) {
		super(location, name);
		//this.iconRoot = "/home/gciotto/eclipse-CSS4_4-LUNA/workspace/br.cnpem.gef.golpe/icon/bbb_board.png";
		
		this.iconAddress = CnpemPlugin.getDefault().getBundle().getResource("icon/bbb.png").getPath(); 
		this.iconRoot = CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.BBB_ID);
		
		this.number_interfaces = 2;
	}
	
	public Image getIconRoot() {
		
		if (iconRoot == null) 
			if (iconChanged)
				this.iconRoot = new Image(Display.getCurrent(), this.iconAddress);
			else this.iconRoot = CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.BBB_ID);
			
		return iconRoot;
	}

}
