/*
 * Created on 26/02/2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package chat;

import java.util.Collection;

/**
 * @author Tiago
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public interface View {
	
	public void setUserList(Collection users);
	public void addMessage(String senderNick, String receiver, String message, boolean reserved);
	public void showInfo(String message);
	
}
