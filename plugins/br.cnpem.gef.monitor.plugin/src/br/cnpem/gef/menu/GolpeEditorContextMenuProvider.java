package br.cnpem.gef.menu;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.actions.ActionFactory;

public class GolpeEditorContextMenuProvider extends ContextMenuProvider {

	private ActionRegistry actionRegistry;

	public GolpeEditorContextMenuProvider(EditPartViewer viewer, ActionRegistry actionRegistry) {
		super(viewer);
		setActionRegistry(actionRegistry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
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
	}

	private ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	private void setActionRegistry(final ActionRegistry actionRegistry) {
		this.actionRegistry = actionRegistry;
	}
}
