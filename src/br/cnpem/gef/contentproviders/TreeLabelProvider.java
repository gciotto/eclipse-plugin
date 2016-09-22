package br.cnpem.gef.contentproviders;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;

import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;
import br.cnpem.gef.model.PVariable;

public class TreeLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		
		if (element instanceof Host)
			return ((Host) element).getName();
		
		if (element instanceof List) {
			List e = (List) element;
			if (!e.isEmpty() && e.get(0).getClass() == NetworkInterface.class) 
				return "Network interfaces";
			
			if (!e.isEmpty() && e.get(0).getClass() == PVariable.class) 
				return "PVs";
		}
		
		
		return element.toString();
	}

}
