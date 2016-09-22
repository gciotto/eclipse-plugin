package br.cnpem.gef.views;


import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import br.cnpem.gef.contentproviders.TreeContentProvider;
import br.cnpem.gef.contentproviders.TreeLabelProvider;
import br.cnpem.gef.controller.HostEditPart;
import br.cnpem.gef.editor.Editor;

public class DescriptorView extends ViewPart {

	public static String ID = "br.cnpem.gef.golpe.view.summary";
	
	private ISelectionListener listener;
	private TreeViewer viewer;

	public DescriptorView() {
		
		listener = new ISelectionListener() {

			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {

				if  (part instanceof Editor ) {
					System.out.println(selection);

					if (selection instanceof StructuredSelection) {

						StructuredSelection s = (StructuredSelection)selection;

						if (s.getFirstElement() instanceof HostEditPart) {
							
							HostEditPart h = (HostEditPart) s.getFirstElement();
							
							viewer.setContentProvider(new TreeContentProvider(viewer));

							viewer.setLabelProvider(new TreeLabelProvider());

							viewer.setInput(h.getModel());

							viewer.expandAll();
						}
					}
				}

			}
		};

	}

	@Override
	public void createPartControl(Composite parent) {

		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(listener);

		viewer = new TreeViewer(parent);
		
		getSite().setSelectionProvider(viewer);
		
		final MenuManager menu = new MenuManager();
        menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
        viewer.getTree().setMenu(menu.createContextMenu(viewer.getTree()));
        getSite().registerContextMenu(menu, viewer);

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {

		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(listener);
		super.dispose();
	}

}
