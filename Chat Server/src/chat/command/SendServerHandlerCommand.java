/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.command;

import java.io.IOException;
import java.util.Iterator;

import chat.Agent;
import chat.Chatter;
import chat.Command;
import chat.protocol.Message;
import chat.protocol.MessagePart;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class SendServerHandlerCommand extends Command {
	/* (non-Javadoc)
	 * @see chat.Command#execute(chat.UserAgent)
	 */
	public void execute(Agent agent, Message message) {
		// message, destinationNick, privateMsg - SEND SERVER
		
		MessagePart[] parts = message.getArrayParts();// incoming message
		
		String txt = parts[0].toString(); //message
		String sender = agent.getNick(); //senderNick
		String destination = parts[1].toString(); //destinationNick
		boolean reserved = Boolean.valueOf(parts[2].toString()).booleanValue(); //privateMsg
		
		try {
			// locate destination agent and send the message
			if(reserved) { // do not send to everyone if destination is specified
				if(destination.equals("")) {
					//send to everyone
					sendToEveryone(agent.getChatter(), sender, destination, txt, reserved);
				} else {
					//send only yo destination
					sendToAgent(agent.getChatter(), sender, destination, destination, txt, reserved);
				}
			} else {
				//send to everyone
				sendToEveryone(agent.getChatter(), sender, destination, txt, reserved);
			}
		} catch (IOException e) {
			agent.getChatter().showInfo(e.getMessage());
		}
	}
	
	public void sendToEveryone(Chatter chatter, String sender, String receiver, String message, boolean reserved) throws IOException {
		Iterator i = chatter.getAgents().keySet().iterator();
		while(i.hasNext()) { 
			String nick = (String)i.next();
			sendToAgent(chatter, sender, receiver, nick, message, reserved);
		}
		
	}
	
	public void sendToAgent(Chatter chatter, String sender, String receiver, String agentId, String message, boolean reserved) throws IOException {
		// message, senderNick, receiverNick, private - SEND CLIENT
		Message msg = new Message(Message.FUNCTION_SEND_CLIENT); // send the message to desired clients
		msg.addPart(message); 
		msg.addPart(sender);
		msg.addPart(receiver);
		msg.addPart(reserved);
		
		Agent ag = chatter.getAgent(agentId);
		ag.send(msg);
	}
}
