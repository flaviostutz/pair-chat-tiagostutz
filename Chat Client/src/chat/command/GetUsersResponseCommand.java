/*
 * Created on 26/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.command;

import chat.Command;
import chat.Agent;
import chat.protocol.Message;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class GetUsersResponseCommand extends Command {

	public void execute(Agent agent, Message message) {
		agent.getChatter().getView().setUserList(message.getParts());
	}
}
