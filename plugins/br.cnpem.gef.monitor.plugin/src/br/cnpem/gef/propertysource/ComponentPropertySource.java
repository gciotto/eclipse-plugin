package br.cnpem.gef.propertysource;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import br.cnpem.gef.golpe.CnpemPlugin;
import br.cnpem.gef.model.Component;
import br.cnpem.gef.properties.CheckBoxPropertyDescriptor;
import br.cnpem.gef.properties.IconPropertyDescriptor;

public class ComponentPropertySource implements IPropertySource {

	private static final String PROPERTY_NAME = "br.cnpem.gef.model.views.name";
	private static final String PROPERTY_ICON = "br.cnpem.gef.model.views.icon";
	private static final String PROPERTY_SCALE = "br.cnpem.gef.model.views.scale";
	private static final String PROPERTY_COLLAPSE = "br.cnpem.gef.model.views.collapse";
	protected static final String ICON_DEFAULT = "/home/gciotto/eclipse-CSS4_4-LUNA/workspace/br.cnpem.gef.golpe/icon/generic.png";
	protected static final String NAME_DEFAULT = "New Generic Component";
	
	protected transient List<IPropertyDescriptor> propertyDescriptors;
	
	protected Component c;
	
	public ComponentPropertySource(Component component) {
		this.c = component;
	}
	
	@Override
	public Object getEditableValue() {
		return this;
	}

	
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		
		if (this.propertyDescriptors == null) {
			
			PropertyDescriptor nameDescriptor = new TextPropertyDescriptor(PROPERTY_NAME, "Name");
			nameDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return c.getName();
				}
				
			});
						
			PropertyDescriptor iconDescriptor = new IconPropertyDescriptor (PROPERTY_ICON, "Icon");
			iconDescriptor.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return c.getIconAddress();
				}
			});
			
			PropertyDescriptor scaleDescriptor = new  CheckBoxPropertyDescriptor(PROPERTY_SCALE, "Auto-scale?");
			
			PropertyDescriptor collapseDescriptor = new  CheckBoxPropertyDescriptor(PROPERTY_COLLAPSE, "Collapse?");
				
			this.propertyDescriptors = new ArrayList<IPropertyDescriptor>();
			this.propertyDescriptors.add(nameDescriptor);
			this.propertyDescriptors.add(iconDescriptor);
			this.propertyDescriptors.add(scaleDescriptor);
			this.propertyDescriptors.add(collapseDescriptor);
		}
		
		IPropertyDescriptor[] result = new IPropertyDescriptor[this.propertyDescriptors.size()];
		
		return this.propertyDescriptors.toArray(result);
	}


	@Override
	public Object getPropertyValue(Object id) {
		
		if (PROPERTY_ICON.equals(id))
			return c.getIconAddress();
		
		if (PROPERTY_NAME.equals(id))
			return c.getName();
		
		if (PROPERTY_SCALE.equals(id))
			return c.getAutoScale();
		
		if (PROPERTY_COLLAPSE.equals(id))
			return c.getCollapse();
				
		return null;
	}


	@Override
	public boolean isPropertySet(Object id) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public void resetPropertyValue(Object id) {
		
		if (PROPERTY_ICON.equals(id)) {
			c.setIconRoot(CnpemPlugin.getDefault().getImageRegistry().get(CnpemPlugin.GENERIC_ID));
			c.setIconAddress(CnpemPlugin.getDefault().getBundle().getResource("icon/generic.png").getPath()); 
			c.setIconChanged(false);
		}
		
		if (PROPERTY_NAME.equals(id))
			c.setName(NAME_DEFAULT);	
		
		if (PROPERTY_SCALE.equals(id))
			c.setAutoScale(true);
		
		if (PROPERTY_COLLAPSE.equals(id))
			c.setCollapse(false);
	}


	@Override
	public void setPropertyValue(Object id, Object value) {
		if (PROPERTY_ICON.equals(id)) {
			c.setIconRoot(new Image(Display.getCurrent(), (String) value));
			c.setIconAddress((String) value);
			c.setIconChanged(true);
		}
		
		if (PROPERTY_NAME.equals(id))
			c.setName((String) value);
		
		if (PROPERTY_SCALE.equals(id))
			c.setAutoScale((boolean) value);
		
		if (PROPERTY_COLLAPSE.equals(id))
			c.setCollapse((boolean) value);
				
	}

}
