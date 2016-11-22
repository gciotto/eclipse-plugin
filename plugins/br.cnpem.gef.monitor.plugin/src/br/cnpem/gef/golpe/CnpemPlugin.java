package br.cnpem.gef.golpe;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class CnpemPlugin extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "br.cnpem.gef.monitor.plugin"; //$NON-NLS-1$
	public static final String BBB_ID = "br.cnpem.gef.golpe.icons.bbb",
							   BBB_ICON_ID = "br.cnpem.gef.golpe.icons.bbb_icon",
							   BBG_ICON_ID = "br.cnpem.gef.golpe.icons.bbg_icon",
							   BBG_ID = "br.cnpem.gef.golpe.icons.bbg",
							   GENERIC_ID = "br.cnpem.gef.golpe.icons.generic",
							   GENERIC_ICON_ID = "br.cnpem.gef.golpe.icons.generic_icon",
							   SWITCH_ID = "br.cnpem.gef.golpe.icons.switch",
							   SWITCH_ICON_ID = "br.cnpem.gef.golpe.icons.switch_icon",
							   PV_ID = "br.cnpem.gef.golpe.icons.pv";

	// The shared instance
	private static CnpemPlugin plugin;
	
	/**
	 * The constructor
	 */
	public CnpemPlugin() {
	}

	public void addToImageRegistry(ImageRegistry registry, String ID, String path_string) {
		
		Bundle bundle = Platform.getBundle(PLUGIN_ID);
        IPath path = new Path(path_string);
        URL url = FileLocator.find(bundle, path, null);
        ImageDescriptor desc = ImageDescriptor.createFromURL(url);
        registry.put(ID, desc);
	}
	
	@Override
	protected void initializeImageRegistry(ImageRegistry registry) {
		
		addToImageRegistry(registry, BBB_ID, "icon/bbb_board.png");
		addToImageRegistry(registry, BBG_ID, "icon/bbg_board.png");
		addToImageRegistry(registry, GENERIC_ID, "icon/generic.png");
		addToImageRegistry(registry, SWITCH_ID, "icon/switch_2.png");
		addToImageRegistry(registry, BBB_ICON_ID, "icon/bbb_icon.png");
		addToImageRegistry(registry, BBG_ICON_ID, "icon/bbg_icon.png");
		addToImageRegistry(registry, GENERIC_ICON_ID, "icon/linux_icon.png");
		addToImageRegistry(registry, SWITCH_ICON_ID, "icon/switch_icon.png");
		addToImageRegistry(registry, PV_ID, "icon/pv.png");
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		initializeImageRegistry(this.getImageRegistry());
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static CnpemPlugin getDefault() {
		return plugin;
	}

}
