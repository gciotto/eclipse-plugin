package br.cnpem.gef.wizards;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import br.cnpem.gef.model.RootComponent;

public class DilmaNewFileCreationPage extends WizardNewFileCreationPage {

	public DilmaNewFileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
		setTitle("Create a new DILMA File");
		setDescription("Create a new DILMA file in the selected project or folder.");
	}

	@Override
	protected InputStream getInitialContents() {
		
		RootComponent _root_model = new RootComponent();

		try {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);

			oos.writeObject(_root_model);

			oos.flush();
			oos.close();

			InputStream is = new ByteArrayInputStream(baos.toByteArray());

			return is;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.getInitialContents();
	}

	@Override
	public String getFileExtension() {
		return "dilma";
	}
}
