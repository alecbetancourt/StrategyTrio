package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/******
 * Panel used between turns so players on the same computer can't see each others board
 * 
 * @author Parker
 * @version 0.1
 */
public class SwitchPanel extends JPanel{
	private String pName;
	private JButton ready;
	private boolean isReady;
	private ButtonListener listen;
	private GridBagConstraints con;
	private ImageIcon rdyIcon;
	
	/**
	 * Constructor for the panel that displays a players name with a message and 
	 * a ready button.
	 * @param name the players name to be displayed
	 */
	public SwitchPanel(String name) {
		isReady = false;
		setBackground(Color.CYAN);
		setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		con.gridx =0;
		con.gridy =0;
		pName = name;
		add(new JLabel(pName + "'s turn"),con);
		
		con.ipadx = 50;
		con.ipady = 30;
		con.gridy++;
		
		
		listen = new ButtonListener();
		ready = new JButton();
		rdyIcon = new ImageIcon("src/battleship/rdyLogo.png");
		ready.setIcon(rdyIcon);
		ready.setBackground(Color.BLUE);
		ready.addActionListener(listen);
		add(ready,con);
		
		
	}
	/**
	 * Get the players name
	 * @return String of player name
	 */
	public String getpName() {
		return pName;
	}

	/**
	 * Set the name to display on the screen
	 * @param pName player's name to set to
	 */
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	/**
	 * Whether or not the player has hit the ready button.
	 * @return True if they hit the button, false if not.
	 */
	public boolean isReady() {
		return isReady;
	}
	/**
	 * Set whether player is ready or not. Used to reset panel before a 
	 * switch normally.
	 * @param isRready True is they are ready, false if not.
	 */
	public void setReady(boolean isRready) {
		this.isReady = isRready;
	}
	
	/**
	 * Listener for the ready button.
	 * @author Parker
	 *
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Responds to button clicks on ready button
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ready) {
				isReady = true;
			}
		}
		
	}

}
