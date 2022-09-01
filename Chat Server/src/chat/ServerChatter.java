/*
 * Created on 24/02/2004
 *
 */
package chat;

import java.util.HashMap;
import java.util.Iterator;

import chat.protocol.Message;
import chat.protocol.MessageFactory;

/**
 * @author Tiago
 *
 */
public class ServerChatter extends Chatter {

	public ChatServer server;
	ServerController serverController;

	public ServerChatter(ChatServer server, int serverPort) {
		this.server = server;
		this.serverController = new ServerController();
		
		agentsMap = new HashMap();
		server.showInfo("Initializing ServerListener");
		Runnable t = new ServerListener(this, serverController, serverPort);
		Thread listener = new Thread(t);
		listener.start();
	}

	public void showInfo(String msg) {
		server.showInfo(msg);
	}

	public void showMessage(String sender, String destination, String msg, boolean reserved) {
		showInfo(msg + " (sender==" + sender + ", destination==" + destination + ", reserved==" + reserved + ")");
		
	}

	public void refreshState() throws Exception {
		try {
			Message msg = MessageFactory.getUsersResponse(this);
			//refresh all client's user list
			Iterator i = getAgents().keySet().iterator();
			while(i.hasNext()) { String nick = i.next().toString();
				Agent ag = getAgent(nick);
				ag.send(msg);
			}
			
			server.updateUserList();
		} catch (Exception e) {
			showInfo(e.getMessage());
		}
	}

	
}
