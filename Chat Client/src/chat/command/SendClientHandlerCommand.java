/*
 * Created on 29/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.command;

import chat.Agent;
import chat.Command;
import chat.protocol.Message;
import chat.protocol.MessagePart;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendClientHandlerCommand extends Command {
	/* (non-Javadoc)
	 * @see chat.Command#execute(chat.Agent, chat.protocol.Message)
	 */
	public void execute(Agent agent, Message message) throws Exception {
		// message, senderNick, receiverNick, private
		MessagePart[] parts = message.getArrayParts();// incoming message
		String msg = parts[0].toString(); 
		String sender = parts[1].toString();
		String receiver = parts[2].toString();
		boolean reserved = Boolean.valueOf(parts[3].toString()).booleanValue();
		
		agent.getChatter().showMessage(sender, receiver, msg, reserved);
	}
}
