/*
 * Created on 26/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import chat.command.GetUsersResponseCommand;
import chat.command.PingHandlerCommand;
import chat.command.PingResponseCommand;
import chat.command.SendClientHandlerCommand;
import chat.protocol.Message;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ClientController implements Controller {

	public void process(Message message, Agent agent) throws Exception {
		Command cmd = null;
		
		if(message.getFunction()==Message.RESPONSE_GET_USERS) {
			cmd = new GetUsersResponseCommand();
			
		} else if(message.getFunction()==Message.FUNCTION_PING) {
			cmd = new PingHandlerCommand();
			
		} else if(message.getFunction()==Message.RESPONSE_PING) {
			cmd = new PingResponseCommand();

		} else if(message.getFunction()==Message.FUNCTION_SEND_CLIENT) {
			cmd = new SendClientHandlerCommand();
			
		}
		
		if(cmd!=null) cmd.execute(agent, message);
		else System.err.println("Null command");
	}
	
}
