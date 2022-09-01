/*
 * Created on 01/03/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat.protocol;

import java.util.Iterator;

import chat.Chatter;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MessageFactory {
	
	public static Message getUsersResponse(Chatter chatter) {
		Message msg = new Message(Message.RESPONSE_GET_USERS);
		Iterator i = chatter.getAgents().keySet().iterator();
		while(i.hasNext()) { String nick = i.next().toString();
			msg.addPart(new MessagePart(nick));
		}
		
		return msg;
	}
	
}
