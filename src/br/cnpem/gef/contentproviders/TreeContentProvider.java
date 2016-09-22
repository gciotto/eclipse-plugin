package br.cnpem.gef.contentproviders;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.NetworkInterface;
import br.cnpem.gef.model.PVariable;
import br.cnpem.gef.model.RootComponent;

public class TreeContentProvider implements ITreeContentProvider {
	
	private TreeViewer viewer;
	private Observer _observer;
	
	private boolean selected_root;
	
	public TreeContentProvider(TreeViewer v) {
		this.viewer = v;
		this.selected_root = false;
		_observer = new ContentProviderObserver();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
		selected_root = false;
		
		if (oldInput instanceof Host) {
			((Host) oldInput).deleteObserver(_observer);
			
			for (NetworkInterface n : ((Host) oldInput).getNetworkInterfaces())
				n.deleteObserver(_observer);
			
			for (PVariable n : ((Host) oldInput).getPVs())
				n.deleteObserver(_observer);
		}
		
		if (newInput instanceof Host) {
			((Host) newInput).addObserver(_observer);
			
			for (NetworkInterface n : ((Host) newInput).getNetworkInterfaces())
				n.addObserver(_observer);
			
			for (PVariable pv : ((Host) newInput).getPVs())
				pv.addObserver(_observer);
			
		}
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		
		if (parentElement instanceof Host)
			if (selected_root) {
				
				List<Object> l = new ArrayList<Object>();
				
				Host h = (Host) parentElement;
						
				if (!h.getNetworkInterfaces().isEmpty())
					l.add(h.getNetworkInterfaces());
				
				if (!h.getPVs().isEmpty())
					l.add(h.getPVs());
										
				return l.toArray();
			}
			else {
				selected_root = true;
				return new Object[] {parentElement};
			}
		
		if (parentElement instanceof List) 
			return ((List) parentElement).toArray();

		return null;
	}

	@Override
	public Object getParent(Object element) {
		
		if (element instanceof NetworkInterface)
			return ((NetworkInterface) element).getParent();
		
		if (element instanceof PVariable)
			return ((PVariable) element).getParent();
		
		return null;
	}

	
	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Host)
			return !(((Host) element).getNetworkInterfaces().isEmpty() && ((Host) element).getPVs().isEmpty());
		
		if (element instanceof List)
			return !((List) element).isEmpty();
			
		return false;
	}
	
	public class ContentProviderObserver implements Observer {

		@Override
		public void update(Observable arg0, Object arg1) {
			System.out.println("agora vai " + arg0);
			if (arg0 instanceof Host)
				selected_root = false;
			viewer.refresh(arg0);
			viewer.expandAll();
		}
		
	}

}
