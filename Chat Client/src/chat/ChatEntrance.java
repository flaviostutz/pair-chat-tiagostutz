package chat;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ChatEntrance extends javax.swing.JFrame {
	private JTextField serverIp;
	private JLabel jLabel3;
	private JLabel jLabel2;
	private JTextField nick;
	private JLabel jLabel1;
	private JButton bt;
	private JMenuBar jMenuBar1;
	public ChatEntrance() {
		initGUI();
	}
	/**
	 * Initializes the GUI.
	 * Auto-generated code - any changes you make will disappear.
	 */
	public void initGUI() {
		try {
			preInitGUI();
			jLabel1 = new JLabel();
			bt = new JButton();
			nick = new JTextField();
			jLabel2 = new JLabel();
			jLabel3 = new JLabel();
			serverIp = new JTextField();
			this.getContentPane().setLayout(null);
			this.setSize(new java.awt.Dimension(266, 231));
			this.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent evt) {
					ChatEntranceMouseReleased(evt);
				}
			});
			this.addComponentListener(new ComponentAdapter() {
				public void componentHidden(ComponentEvent evt) {
					rootComponentHidden(evt);
				}
			});
			jLabel1.setText("Chat");
			jLabel1.setFont(new java.awt.Font("", 1, 24));
			jLabel1.setPreferredSize(new java.awt.Dimension(60, 20));
			jLabel1.setBounds(new java.awt.Rectangle(26, 25, 60, 20));
			this.getContentPane().add(jLabel1);
			bt.setText("Connect");
			bt.setPreferredSize(new java.awt.Dimension(135, 44));
			bt.setBounds(new java.awt.Rectangle(66, 127, 135, 44));
			this.getContentPane().add(bt);
			bt.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					btMouseClicked(evt);
				}
			});
			nick.setPreferredSize(new java.awt.Dimension(86, 17));
			nick.setBounds(new java.awt.Rectangle(117, 60, 86, 17));
			this.getContentPane().add(nick);
			jLabel2.setText("Nick:");
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setPreferredSize(new java.awt.Dimension(60, 20));
			jLabel2.setBounds(new java.awt.Rectangle(53, 59, 60, 20));
			this.getContentPane().add(jLabel2);
			jLabel3.setText("Server IP:");
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setPreferredSize(new java.awt.Dimension(60, 20));
			jLabel3.setBounds(new java.awt.Rectangle(52, 80, 60, 20));
			this.getContentPane().add(jLabel3);
			serverIp.setPreferredSize(new java.awt.Dimension(86, 17));
			serverIp.setBounds(new java.awt.Rectangle(117, 81, 86, 17));
			this.getContentPane().add(serverIp);
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
			ChatEntrance inst = new ChatEntrance();
			inst.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** Auto-generated event handler method */
	protected void btMouseClicked(MouseEvent evt) {
		Chat.showGUI(serverIp.getText(), Util.DEFAULT_SERVER_PORT, nick
			.getText());
	}

	/** Auto-generated event handler method */
	protected void ChatEntranceMouseReleased(MouseEvent evt) {
	}

	/** Auto-generated event handler method */
	protected void ChatEntranceWindowClosed(WindowEvent evt) {
	}

	/** Auto-generated event handler method */
	protected void rootWindowClosed(WindowEvent evt) {
		//TODO add your handler code here
	}

	/** Auto-generated event handler method */
	protected void rootComponentHidden(ComponentEvent evt) {
		dispose();
	}
}