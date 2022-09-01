/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import chat.protocol.Message;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class Agent implements Runnable {
	/**
	 * 
	 */
	 Socket socket;
	 ByteArrayOutputStream contents = new ByteArrayOutputStream();
	 Controller controller = null;
	 Chatter chatter = null;
	 int bodySize = -1;
	 String nick;
	 InputStream is;
	 OutputStream os;
	 boolean active = true;
	 long lastPingRequest = 0;
	 boolean pingResponded = true;
	 
	public Agent(Socket socket, Controller controller, Chatter chatter) throws IOException {
		this.socket = socket;
		this.controller = controller;
		this.chatter = chatter;
		nick = null;
		is = socket.getInputStream();
		os = socket.getOutputStream();
	}

	public void run() {
			
		while(socket.isConnected()) {
			
			try {
				if(is.available()>0) {
					chatter.showInfo("Reading " + is.available() + " bytes:");
					
					byte[] buffer = new byte[is.available()];
					int bread = is.read(buffer);
					
					chatter.showInfo(new String(buffer));
					
					process(buffer, bread);
				
				} else {
					Thread.sleep(Util.THREAD_SLEEP);
				}
				
				// PING MECHANICS
				long now = System.currentTimeMillis();
				if(pingResponded)
				if((now-lastPingRequest) >= Util.PING_INTERVAL) {
					Message msg = new Message(Message.FUNCTION_PING);
					send(msg);
					chatter.showInfo("ping " + nick);
					lastPingRequest = now;
					pingResponded = false;
				}
				// ping response
				if(!pingResponded)
				if((now-lastPingRequest) >= Util.PING_TIMEOUT) {
					//ping timeout, shutdown this agent
					break;
				}
				
			} catch (Exception e) {
				chatter.showInfo(e.getMessage());
				break;
			}
			
		} //while
		
		// if the stack reaches here means the socket is closed, so, shutdown this agent and refresh all user's on-line list
		try {
			chatter.removeAgent(this);
		} catch (Exception e1) {
			chatter.showInfo(e1.getMessage());
		}
			
	}
	
	public void confirmPing() {
		pingResponded = true;
		long now = System.currentTimeMillis();
		chatter.showInfo("pong! " + nick + "("+ (now-lastPingRequest) +"ms)");
		lastPingRequest = now;
	}
	
	public void register(String nick) {
		this.nick = nick;
		chatter.putAgent(nick, this);
	}

	public void deactivate() throws IOException {
		if(socket.isConnected()) {
			socket.close();
		}
	}
	
	public String getNick() {
		return nick;
	}
	
	public Chatter getChatter() {
		return chatter;
	}
	
	public String toString() {
		return getNick();
	}
	
	private void process(byte[] buffer, int bytesRead) throws NumberFormatException, IOException {
		if(bytesRead>0)	contents.write(buffer, 0, bytesRead);
		ByteArrayOutputStream bos = null;
		
		if(contents.size()>=Message.SIZE_BYTES) {
			bodySize = Integer.parseInt(contents.toString().substring(0, Message.SIZE_BYTES));

			if(contents.size()>=(Message.SIZE_BYTES + bodySize)) {
				
				//copiar a mensagem atual para um novo stream tirando o pedaço do tamanho da mensagem total
				ByteArrayOutputStream full = new ByteArrayOutputStream();
				if(bodySize>0) full.write(contents.toByteArray(), Message.SIZE_BYTES, bodySize);
				
				//copiar o pedaço de uma possível mensagem posterior para um novo objeto
				bos = new ByteArrayOutputStream();
				if(contents.size()>(Message.SIZE_BYTES + bodySize)) {
					bos.write(contents.toByteArray(), Message.SIZE_BYTES + bodySize, contents.size() - (Message.SIZE_BYTES + bodySize));
				}
				
				//inicializar o conteúdo
				contents = bos;
				
				//executar a ação
				try {
					controller.process(new Message(full.toByteArray()), this);
				} catch (Exception e) {
					chatter.showInfo("Controller: " + e.getMessage());
				}
				
				if(bos.size()>0) process(new byte[0], 0);
				
			}
			
		}
		
	}
	
	public void send(Message msg) throws IOException {
		if(msg==null || os==null) System.err.println("Invalid arguments on Agent.send(Message). msg=" + msg + ", os=" + os);
		else os.write(msg.encode());
	}
	/**
	 * @return Returns the controller.
	 */
	public Controller getController() {
		return controller;
	}
}
