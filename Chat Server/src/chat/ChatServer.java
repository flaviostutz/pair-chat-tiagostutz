package chat;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JList;
import javax.swing.JMenuBar;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JTextArea;
public class ChatServer extends javax.swing.JFrame {
	private JTextArea console;
	private JList users;
	private JMenuBar jMenuBar1;
	ServerChatter serverChatter = null;
	
	public ChatServer() {
		initGUI();
	}
	/**
	 * Initializes the GUI.
	 * Auto-generated code - any changes you make will disappear.
	 */
	public void initGUI() {
		try {
			preInitGUI();
			users = new JList();
			console = new JTextArea();
			BorderLayout thisLayout = new BorderLayout();
			this.getContentPane().setLayout(thisLayout);
			thisLayout.setHgap(0);
			thisLayout.setVgap(0);
			this.setSize(new java.awt.Dimension(474, 308));
			this.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent evt) {
					ChatServerWindowClosed(evt);
				}
			});
			users.setPreferredSize(new java.awt.Dimension(177, 258));
			this.getContentPane().add(users, BorderLayout.CENTER);
			users.addComponentListener(new ComponentAdapter() {
				public void componentHidden(ComponentEvent evt) {
					usersComponentHidden(evt);
				}
			});
			console.setPreferredSize(new java.awt.Dimension(335, 281));
			this.getContentPane().add(console, BorderLayout.WEST);
			jMenuBar1 = new JMenuBar();
			setJMenuBar(jMenuBar1);
			postInitGUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Add your pre-init code in here
	 */
	public void preInitGUI() {
	}
	/**
	 * Add your post-init code in here
	 */
	public void postInitGUI() {
		serverChatter = new ServerChatter(
			this,
			Util.DEFAULT_SERVER_PORT);
		setTitle("Chat server running");
	}

	/** Auto-generated main method */
	public static void main(String[] args) {
		showGUI();
	}

	/**
	 * Auto-generated code - any changes you make will disappear!!!
	 * This static method creates a new instance of this class and shows
	 * it inside a new JFrame, (unless it is already a JFrame).
	 */
	public static void showGUI() {
		try {
			ChatServer inst = new ChatServer();
			inst.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showInfo(String message) {
		console.setText(message + "\n" + console.getText());
	}

	/** Auto-generated event handler method */
	protected void ChatServerWindowClosed(WindowEvent evt) {
	}

	/** Auto-generated event handler method */
	protected void usersComponentHidden(ComponentEvent evt) {
		dispose();
	}
	
	public void updateUserList() {
		if(serverChatter!=null) users.setListData(serverChatter.getAgents().entrySet().toArray());
	}
}