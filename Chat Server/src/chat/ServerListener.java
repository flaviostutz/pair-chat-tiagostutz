/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class ServerListener implements Runnable {

	ServerChatter chatter;
	ServerController controller;
	int serverPort;
	
	public ServerListener(ServerChatter chatter, ServerController controller, int serverPort) {
		this.chatter = chatter;
		this.controller = controller;
		this.serverPort = serverPort;
	}
	
	public void run() {
		try {
			
			ServerSocket serverSocket = new ServerSocket(serverPort);
			while(true) {
				chatter.showInfo("Waiting connection...");
				chatter.showInfo("Users online: " + chatter.getAgents());
				Socket socket = serverSocket.accept();
				chatter.showInfo("Handling incoming connection");
				Agent agent = new Agent(socket, controller, chatter);
				Thread ag = new Thread(agent);
				ag.start();
				
			}
			
		} catch (IOException e) {
			chatter.showInfo(e.getMessage());
		}
	}
}
