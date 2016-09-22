package br.cnpem.gef.figure;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;

import br.cnpem.gef.golpe.CnpemPlugin;

public class PVariableFigure extends Figure {

	private ImageFigure icon;
	private Label name;
	
	public static final int MIN_WIDTH = 250, MIN_HEIGHT = 35;
	
	public PVariableFigure() {
		
		GridLayout layout = new GridLayout(2, false);
		
		icon = new ImageFigure ();
		name = new Label();
		
		icon.setImage(CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.PV_ID));
		
		setPreferredSize(180, 35);
		
		this.setLayoutManager(layout);
		this.add(icon);
		this.add(name);
	}
	
	
	public void setName(String name) {
		this.name.setText(name);
	}
	
	public Label getLabelName() {
		return this.name;
	}
}
