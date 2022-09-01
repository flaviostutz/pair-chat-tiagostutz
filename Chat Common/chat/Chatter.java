/*
 * Created on 24/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public abstract class Chatter {
	
	Map agentsMap = new HashMap();
	
	public abstract void showInfo(String msg);
	public abstract void showMessage(String sender, String destination, String msg, boolean reserved);

	public abstract void refreshState() throws Exception;
	
	public void putAgent(String nick, Agent agent) {
		agentsMap.put(nick, agent);
	}
	
	public void removeAgent(Agent agent) throws Exception {
		
		if(agent!=null) {
			agent.deactivate();
			agentsMap.remove(agent.getNick());
			showInfo("Agent " + agent.getNick() + " removed.");
			refreshState();
		}
		
	}
	
	public Agent getAgent(String nick) {
		return (Agent)agentsMap.get(nick);
	}
	
	public Map getAgents() {
		return agentsMap;
	}
	
	public View getView() {
		return null;
	}
	
}
