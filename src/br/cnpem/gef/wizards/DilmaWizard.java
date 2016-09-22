package br.cnpem.gef.wizards;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.FileEditorInput;

public class DilmaWizard extends Wizard implements INewWizard {

	private DilmaNewFileCreationPage page;
    private IStructuredSelection selection;
    private IWorkbench workbench;
	

	@Override
	public void addPages() {
		page = new DilmaNewFileCreationPage("New Dilma file", selection);
		addPage(page);		
	}
	
	@Override
    public void init(IWorkbench workbench, IStructuredSelection selection) {
        this.workbench = workbench;
        this.selection = selection;
    }

	@Override
	public boolean performFinish() {

		IFile file = this.page.createNewFile();

        if (file == null) {
            return false;
        }

        try {
            workbench.getActiveWorkbenchWindow().getActivePage().openEditor(
                    new FileEditorInput(file), "br.cnpem.gef.golpe.GolpeEditor");//$NON-NLS-1$
        } catch (PartInitException e) {
            MessageDialog.openError(null, "Open OPI File error",
                    "Failed to open the newly created OPI File. \n" + e.getMessage());
        }

        return true;
	}

}
