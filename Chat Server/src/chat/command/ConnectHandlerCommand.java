/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.command;

import java.util.Collection;

import chat.*;
import chat.Agent;
import chat.protocol.*;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ConnectHandlerCommand extends Command {
	/* (non-Javadoc)
	 * @see chat.Command#execute(chat.UserAgent)
	 */
	public void execute(Agent agent, Message message) throws Exception {
		Collection parts = message.getParts();
		MessagePart part = (MessagePart)parts.iterator().next();
		agent.getChatter().showInfo("Registering new user: " + new String(part.getContents()));
		//register the agent to this nickname
		agent.register(new String(part.getContents()));
		
		agent.getChatter().refreshState();
	}
}
