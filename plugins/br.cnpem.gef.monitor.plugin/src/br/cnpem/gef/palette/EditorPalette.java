package br.cnpem.gef.palette;

import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.CreationToolEntry;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.SelectionToolEntry;

import br.cnpem.gef.golpe.CnpemPlugin;
import br.cnpem.gef.model.BBBFactory;
import br.cnpem.gef.model.BBGFactory;
import br.cnpem.gef.model.ConnectionFactory;
import br.cnpem.gef.model.HostFactory;
import br.cnpem.gef.model.NetworkInterfaceFactory;
import br.cnpem.gef.model.PVItemFactory;
import br.cnpem.gef.model.SwitchFactory;

public class EditorPalette extends PaletteRoot {

	private PaletteGroup _group;

	public EditorPalette (){

		addGroup();
		
		_group.setVisible(true);
				
		addSelectionTool();
		addHostTool();
		
		ConnectionCreationToolEntry entry = new ConnectionCreationToolEntry("Link", "Creates a new link", new ConnectionFactory(), null, null);
	    _group.add(entry);
	}

	private void addSelectionTool() {
		SelectionToolEntry entry = new SelectionToolEntry();
		_group.add(entry);
		setDefaultEntry(entry);
	}

	private void addGroup() {
		_group = new PaletteGroup("New hosts");
		add(_group);
		
	}

	private void addHostTool() {
		
		CreationToolEntry 	entry_bbb = new CombinedTemplateCreationEntry("BeagleBone Black ", "Create a new BBB", new BBBFactory(), CnpemPlugin.getDefault().getImageRegistry().getDescriptor(CnpemPlugin.BBB_ICON_ID), null),
							entry_bbg = new CombinedTemplateCreationEntry("BeagleBone Green ", "Create a new BBG", new BBGFactory(), CnpemPlugin.getDefault().getImageRegistry().getDescriptor(CnpemPlugin.BBG_ICON_ID), null),
							entry_generic = new CombinedTemplateCreationEntry("Generic device", "Create a new device", new HostFactory(), CnpemPlugin.getDefault().getImageRegistry().getDescriptor(CnpemPlugin.GENERIC_ICON_ID), null),
							entry_switch = new CombinedTemplateCreationEntry("Switch", "Create a new switch", new SwitchFactory(), CnpemPlugin.getDefault().getImageRegistry().getDescriptor(CnpemPlugin.SWITCH_ICON_ID), null),
							entry_network_interface = new CombinedTemplateCreationEntry("Network Interface", "Create a new network interface", new NetworkInterfaceFactory(), null, null),
							entry_pv = new CombinedTemplateCreationEntry("PV", "Create a new PV", new PVItemFactory(), null, null);
		
		_group.add(entry_bbb);
		_group.add(entry_bbg);
		_group.add(entry_generic);
		_group.add(entry_switch);
		_group.add(entry_network_interface);
		_group.add(entry_pv);
		
	}

}
