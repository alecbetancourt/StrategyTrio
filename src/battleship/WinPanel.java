package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel displayed when the game is over. Displayed the winning
 * player's name and has a rematch button.
 * 
 * @author Parker
 * @version 0.1
 */
public class WinPanel extends JPanel {
	/**
	 * ID for winpanel.
	 */
	private static final long serialVersionUID = 5517704415744654967L;
	/**
	 * The name of the winning player.
	 */
	private String pName;

	/**
	 * Constraints used for formating.
	 */
	private GridBagConstraints con;
	
	/**
	 * Constructor for the panel. Formats the layout using a gridbag
	 * and adds in the rematch button and message
	 * @param name the winning player name
	 * @param rematchButton for players to press for a rematch.
	 */
	public WinPanel(final String name, final JButton rematchButton) {
	setBackground(Color.GRAY);
	setLayout(new GridBagLayout());
	con = new GridBagConstraints();
	con.gridx = 0;
	con.gridy = 0;
	pName = name;
	add(new JLabel(pName + " has won"), con);
	
	con.gridy++;
	con.ipadx = 100;
	con.ipady = 60;


	add(rematchButton, con);
	}


}
