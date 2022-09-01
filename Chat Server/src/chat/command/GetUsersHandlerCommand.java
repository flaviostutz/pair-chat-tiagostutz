/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.command;

import chat.Agent;
import chat.Command;
import chat.protocol.Message;
import chat.protocol.MessageFactory;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GetUsersHandlerCommand extends Command {
	/* (non-Javadoc)
	 * @see chat.Command#execute(chat.UserAgent)
	 */
	public void execute(Agent agent, Message message) throws Exception {
		Message msg = MessageFactory.getUsersResponse(agent.getChatter());
		agent.send(msg);
	}
}
