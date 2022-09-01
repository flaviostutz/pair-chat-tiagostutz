package chat;

import java.awt.GridBagLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JCheckBox;
import javax.swing.ListSelectionModel;

public class Chat extends javax.swing.JFrame implements View {
	private JCheckBox reserved;
	private JButton exit;
	private JButton refresh;
	private JTextField chatLine;
	private JTextPane chatScreen;
	private JList userList;
	ClientChatter chatter;
	String nick, serverIp;

	public Chat(String serverIp, int serverPort, String nick)
		throws UnknownHostException, IOException {
		this.nick = nick;
		chatter = new ClientChatter(this, serverIp, serverPort, nick);
		initGUI();
		setTitle(nick);
	}
	/**
	 * Initializes the GUI.
	 * Auto-generated code - any changes you make will disappear.
	 */
	public void initGUI() {
		try {
			preInitGUI();
			chatScreen = new JTextPane();
			userList = new JList();
			chatLine = new JTextField();
			refresh = new JButton();
			exit = new JButton();
			reserved = new JCheckBox();
			this.getContentPane().setLayout(null);
			this.setSize(new java.awt.Dimension(483, 352));
			this.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					ChatMouseClicked(evt);
				}
			});
			this.addComponentListener(new ComponentAdapter() {
				public void componentHidden(ComponentEvent evt) {
					rootComponentHidden(evt);
				}
			});
			chatScreen.setPreferredSize(new java.awt.Dimension(346, 287));
			chatScreen.setBounds(new java.awt.Rectangle(0, 0, 346, 287));
			this.getContentPane().add(chatScreen);
			GridBagLayout userListLayout = new GridBagLayout();
			userList.setLayout(userListLayout);
			userListLayout.columnWidths = null;
			userListLayout.rowHeights = null;
			userListLayout.columnWeights = null;
			userListLayout.rowWeights = null;
			userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			userList.setPreferredSize(new java.awt.Dimension(123, 243));
			userList.setBounds(new java.awt.Rectangle(349, 0, 123, 243));
			this.getContentPane().add(userList);
			chatLine.setPreferredSize(new java.awt.Dimension(474, 19));
			chatLine.setBounds(new java.awt.Rectangle(0, 289, 474, 19));
			this.getContentPane().add(chatLine);
			chatLine.addKeyListener(new KeyAdapter() {
				public void keyTyped(KeyEvent evt) {
					chatLineKeyTyped(evt);
				}
			});
			refresh.setText("Refresh");
			refresh.setHorizontalAlignment(SwingConstants.CENTER);
			refresh.setVerticalTextPosition(SwingConstants.CENTER);
			refresh.setMargin(new Insets(2, 5, 2, 5));
			refresh.setFont(new java.awt.Font("", 0, 10));
			refresh.setPreferredSize(new java.awt.Dimension(62, 20));
			refresh.setBounds(new java.awt.Rectangle(349, 268, 62, 20));
			this.getContentPane().add(refresh);
			refresh.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					refreshMouseClicked(evt);
				}
			});
			exit.setText("Exit");
			exit.setMargin(new Insets(2, 5, 2, 5));
			exit.setFont(new java.awt.Font("", 0, 10));
			exit.setPreferredSize(new java.awt.Dimension(59, 19));
			exit.setBounds(new java.awt.Rectangle(415, 269, 59, 19));
			this.getContentPane().add(exit);
			exit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					exitMouseClicked(evt);
				}
			});
			reserved.setText("private");
			reserved.setPreferredSize(new java.awt.Dimension(117, 20));
			reserved.setBounds(new java.awt.Rectangle(353, 246, 117, 20));
			this.getContentPane().add(reserved);
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
		try {
			addMessage(null, null, "Retrieving users...", false);
			chatter.getUsersList();
		} catch (IOException e) {
			addMessage(
				null,
				null,
				"Error retrieving users. " + e.getMessage(),
				false);
		}
	}

	/** Auto-generated main method */
	public static void main(String[] args) {
		showGUI(args[0], Integer.parseInt(args[1]), args[2]);
	}

	/**
	 * Auto-generated code - any changes you make will disappear!!!
	 * This static method creates a new instance of this class and shows
	 * it inside a new JFrame, (unless it is already a JFrame).
	 */
	public static void showGUI(String serverIP, int serverPort, String nick) {
		try {
			Chat inst = new Chat(serverIP, serverPort, nick);
			inst.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Auto-generated event handler method */
	protected void ChatMouseClicked(MouseEvent evt) {

	}

	/** Auto-generated event handler method */
	protected void exitMouseClicked(MouseEvent evt) {
		hide();
	}
	/* (non-Javadoc)
	 * @see chat.Client#setUserList(java.util.Collection)
	 */
	public void setUserList(Collection users) {
		userList.setListData(users.toArray());
	}
	/* (non-Javadoc)
	 * @see chat.Client#addMessage(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void addMessage(
		String senderNick,
		String receiver,
		String message,
		boolean reserved) {
		if (senderNick == null)
			senderNick = "System";
		if (receiver == null || receiver.equals(""))
			receiver = "All";
		chatScreen.setText(senderNick
			+ " says to "
			+ receiver
			+ (reserved ? " (private)" : "")
			+ ": "
			+ message
			+ "\n"
			+ chatScreen.getText());

	}
	/* (non-Javadoc)
	 * @see chat.Client#showInfo(java.lang.String)
	 */
	public void showInfo(String message) {
		System.out.println(message);

	}

	/** Auto-generated event handler method */
	protected void chatLineKeyTyped(KeyEvent evt) {
		try {
			if (evt.getKeyChar() == '\n') {
				String toNick = null;
				Object obj = userList.getSelectedValue();
				if (obj != null)
					toNick = obj.toString();
				chatter.sendMessage(
					chatLine.getText().replaceAll("\n", ""),
					toNick,
					reserved.isSelected());
				chatLine.setText("");
			}
		} catch (IOException e) {
			showInfo(e.getMessage());
		}

	}

	/** Auto-generated event handler method */
	protected void refreshMouseClicked(MouseEvent evt) {
		try {
			chatter.getUsersList();
		} catch (IOException e) {
			showInfo(e.getMessage());
		}
	}

	/** Auto-generated event handler method */
	protected void rootComponentHidden(ComponentEvent evt) {
		Agent agent = chatter.getAgent(nick);
		try {
			chatter.removeAgent(agent);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}