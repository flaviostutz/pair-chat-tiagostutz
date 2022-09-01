/*
 * Created on 24/02/2004
 *
 */
package chat;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.protocol.Message;

/**
 * @author Tiago
 *
 */
public class ClientChatter extends Chatter {
	
	String serverIP, nick;
	View client;
	Socket socket;
	Agent agent;
	
	public ClientChatter(View client, String serverIP, int serverPort, String nick) throws UnknownHostException, IOException {
		this.serverIP = serverIP;
		this.nick = nick;
		this.client = client;
		
		socket = new Socket(serverIP, serverPort);

		client.showInfo("Connecting to " + serverIP + " on port " + serverPort);
		agent = new Agent(socket, new ClientController(), this);
		agent.register(nick);
		Thread tagent = new Thread(agent);
		tagent.start();
	
		Message msg = new Message(Message.FUNCTION_CONNECT);
		msg.addPart(nick);
		
		agent.send(msg);
		
	}

	public void sendMessage(String message, String toNick, boolean restrict) throws IOException {
		Message msg = new Message(Message.FUNCTION_SEND_SERVER);
		msg.addPart(message);//message text
		msg.addPart(toNick);//destination
		msg.addPart(restrict);//restrict
		agent.send(msg);
	}
	
	public void getUsersList() throws IOException {
		Message msg = new Message(Message.FUNCTION_GET_USERS);
		agent.send(msg);
	}

	/**
	 * @return Returns the nick.
	 */
	public String getNick() {
		return nick;
	}
	/**
	 * @return Returns the serverIP.
	 */
	public String getServerIP() {
		return serverIP;
	}

	public void showInfo(String msg) {
		client.showInfo(msg);
	}
	public void showMessage(String sender, String destination, String msg, boolean reserved) {
		client.addMessage(sender, destination, msg, reserved);
	}
	/**
	 * @return Returns the client.
	 */
	public View getView() {
		return client;
	}

	public void refreshState() throws Exception {
		// do nothing
	}
}
