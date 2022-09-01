/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import chat.command.ConnectHandlerCommand;
import chat.command.GetUsersHandlerCommand;
import chat.command.PingHandlerCommand;
import chat.command.PingResponseCommand;
import chat.command.SendServerHandlerCommand;
import chat.protocol.Message;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ServerController implements Controller {
	
	public void process(Message message, Agent agent) throws Exception {
		Command cmd = null;
		
		if(message.getFunction()==Message.FUNCTION_CONNECT) {
			cmd = new ConnectHandlerCommand();
			
		} else if(message.getFunction()==Message.FUNCTION_PING) {
			cmd = new PingHandlerCommand();
			
		} else if(message.getFunction()==Message.RESPONSE_PING) {
			cmd = new PingResponseCommand();

		} else if(message.getFunction()==Message.FUNCTION_GET_USERS) {
			cmd = new GetUsersHandlerCommand();
			
		} else if(message.getFunction()==Message.FUNCTION_SEND_SERVER) {
			cmd = new SendServerHandlerCommand();
			
		}

		if(cmd!=null) cmd.execute(agent, message);
		else System.err.println("Null command");
	}
	
}
