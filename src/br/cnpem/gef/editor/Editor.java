package br.cnpem.gef.editor;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EventObject;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.PropertySheetPage;

import br.cnpem.gef.controller.ComponentEditPartFactory;
import br.cnpem.gef.golpe.CnpemPlugin;
import br.cnpem.gef.menu.AddNetwotkInterfaceAction;
import br.cnpem.gef.menu.GolpeEditorContextMenuProvider;
import br.cnpem.gef.model.RootComponent;
import br.cnpem.gef.palette.EditorPalette;

public class Editor extends GraphicalEditorWithFlyoutPalette {

	private RootComponent _root_model;
	private IFile file; 
	PropertySheetPage propertyPage;

	public Editor () {
		setEditDomain(new DefaultEditDomain(this));
		_root_model = new RootComponent();

	}

	@Override
	protected void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		getGraphicalViewer().setContents(_root_model);

		MenuManager menu = new MenuManager();

		GEFActionConstants.addStandardActionGroups(menu);
		IAction action;

		action = getActionRegistry().getAction(ActionFactory.DELETE.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);
		action = getActionRegistry().getAction(ActionFactory.UNDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);
		action = getActionRegistry().getAction(ActionFactory.REDO.getId());
		menu.appendToGroup(GEFActionConstants.GROUP_UNDO, action);

		action = getActionRegistry().getAction(AddNetwotkInterfaceAction.ADD_NETWORK_INTERFACE);
		menu.appendToGroup(GEFActionConstants.GROUP_EDIT, action);

		menu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));

		getGraphicalControl().setMenu(menu.createContextMenu(getGraphicalControl()));

		getEditorSite().registerContextMenu(menu, getGraphicalViewer(), false);
	}

	@Override
	protected void configureGraphicalViewer() {
		super.configureGraphicalViewer();
		getGraphicalViewer().setEditPartFactory(new ComponentEditPartFactory());

		//getActionRegistry().registerAction(new ToggleGridAction(getGraphicalViewer())); 
		//getActionRegistry().registerAction(new ToggleSnapToGeometryAction(getGraphicalViewer()));  

		getGraphicalViewer().addDropTargetListener(new TemplateTransferDropTargetListener(getGraphicalViewer()));
		getEditDomain().getPaletteViewer().addDragSourceListener(new TemplateTransferDragSourceListener(getEditDomain().getPaletteViewer()));
	}

	@Override
	protected PaletteRoot getPaletteRoot() {

		return new EditorPalette();
	}



	@Override
	public void doSave(IProgressMonitor monitor) {

		if (file == null)
			return;

		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			oos.writeObject(_root_model);

			oos.flush();
			oos.close();

			InputStream is = new ByteArrayInputStream(baos.toByteArray());

			file.setContents(is, false, false, monitor);


			getCommandStack().markSaveLocation();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@Override
	public void doSaveAs() {
		SaveAsDialog saveAsDialog = new SaveAsDialog(getEditorSite().getShell());

		try {

			int ret = saveAsDialog.open();

			if (ret == Window.OK) {
				
				IPath targetPath = saveAsDialog.getResult();

				IFile targetFile = ResourcesPlugin.getWorkspace().getRoot().getFile(targetPath);

				if (!targetFile.exists()) {
					targetFile.create(null, true, null);
				}

				FileEditorInput editorInput = new FileEditorInput(targetFile);

				setInput(editorInput);

				setPartName(targetFile.getName());
				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);

				oos.writeObject(_root_model);

				oos.flush();
				oos.close();

				InputStream is = new ByteArrayInputStream(baos.toByteArray());

				targetFile.setContents(is, false, false, null);

				getCommandStack().markSaveLocation();

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean isSaveAsAllowed() {
		if (this.isDirty())
			return true;
		return false;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {

		super.init(site, input);

		loadInput(input);

		this.setPartName(file.getName());

	}

	private void loadInput(IEditorInput input){

		if(input instanceof IFileEditorInput) {


			IFileEditorInput fileInput = (IFileEditorInput) input;
			file = fileInput.getFile();

			try {
				System.err.println(file.getFullPath().toString());
				ObjectInputStream in = new ObjectInputStream(file.getContents());
				this._root_model = (RootComponent) in.readObject();

				in.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

	}

	@Override
	public void commandStackChanged(EventObject event) {
		firePropertyChange(PROP_DIRTY);
		super.commandStackChanged(event);
	}

	@Override
	protected void createActions() {
		AddNetwotkInterfaceAction action = new AddNetwotkInterfaceAction(this);
		getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		super.createActions();
	}

}

