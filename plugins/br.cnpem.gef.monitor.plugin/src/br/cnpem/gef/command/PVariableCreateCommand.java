package br.cnpem.gef.command;

import org.eclipse.gef.commands.Command;

import br.cnpem.gef.model.Host;
import br.cnpem.gef.model.PVariable;

public class PVariableCreateCommand extends Command {
	
	private PVariable _pv;
	private Host _root;
	
	@Override
	public void undo() {
		this._root.removePV(this._pv);
	}
	
	@Override
	public void execute() {
		this._root.addPV(this._pv);
	}

	public PVariable getPV() {
		return _pv;
	}

	public void setPV(PVariable _pv) {
		this._pv = _pv;
	}

	public Host getHostRoot() {
		return _root;
	}

	public void setHostRoot(Host _root) {
		this._root = _root;
	}
	
}
