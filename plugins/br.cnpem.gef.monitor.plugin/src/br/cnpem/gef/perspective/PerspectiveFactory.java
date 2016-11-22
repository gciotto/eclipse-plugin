package br.cnpem.gef.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import br.cnpem.gef.views.DescriptorView;

public class PerspectiveFactory implements IPerspectiveFactory {

	@Override
	public void createInitialLayout(IPageLayout layout) {
		
		String editor = layout.getEditorArea();

		IFolderLayout left, bottom;
		
		left = layout.createFolder("left", IPageLayout.LEFT, 0.25f, editor);
		
		left.addView(IPageLayout.ID_PROJECT_EXPLORER);
		left.addView(DescriptorView.ID);
		
		bottom = layout.createFolder("bottom",IPageLayout.BOTTOM, 0.66f, editor);		
        bottom.addView(IPageLayout.ID_PROP_SHEET);
        
	}

}
