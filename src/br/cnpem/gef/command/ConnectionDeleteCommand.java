package br.cnpem.gef.command;

import org.eclipse.gef.commands.Command;

import br.cnpem.gef.model.Connection;

public class ConnectionDeleteCommand extends Command {
	
	private Connection connection;
	
	@Override
	public void execute() {
		connection.getRoot().removeConnection(connection);
	}
	
	@Override
	public void undo() {
		connection.getRoot().addConnection(connection);
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean canExecute() {
		return (connection != null);
	}

}
