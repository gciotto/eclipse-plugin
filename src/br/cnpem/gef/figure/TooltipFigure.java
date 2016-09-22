package br.cnpem.gef.figure;

import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.text.FlowPage;
import org.eclipse.draw2d.text.TextFlow;

public class TooltipFigure extends FlowPage {

	private final MarginBorder TOOLTIP_BORDER = new MarginBorder(0, 2, 1, 0);
	private TextFlow message;

	public TooltipFigure() {
		setOpaque(true);
		setBorder(TOOLTIP_BORDER);
		message = new TextFlow();
		message.setText("");
		add(message);
	}

	@Override
	public Dimension getPreferredSize(int w, int h) {
		Dimension d = super.getPreferredSize(-1, -1);
		
		if (d.width > 150)
			d = super.getPreferredSize(350, -1);
		
		return d;
	}

	public void setMessage(String txt) {
		message.setText(txt);
		revalidate();
		repaint();
	}

}
