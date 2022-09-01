/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.command;

import java.io.IOException;

import chat.*;
import chat.Agent;
import chat.protocol.*;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PingHandlerCommand extends Command {
	/* (non-Javadoc)
	 * @see chat.Command#execute(chat.UserAgent)
	 */
	public void execute(Agent agent, Message message) throws IOException {
		Message msg = new Message(Message.RESPONSE_PING);
		agent.send(msg);
	}
}
