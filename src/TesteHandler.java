import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;


public class TesteHandler extends AbstractHandler {

	
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("teste");
		return null;
	}

}
