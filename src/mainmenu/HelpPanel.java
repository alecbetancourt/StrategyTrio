package mainmenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * Panel displayed as the help screen.
 * @author Parker
 *
 */
public class HelpPanel extends JPanel implements ActionListener {
	
	/**
	 * ID for help Panel.
	 */
	private static final long serialVersionUID = -6980233742847123020L;
	/**
	 * Message for top of the screen.
	 */
	private JLabel message;
	/**
	 * Button for battlehsip help screen.
	 */
	private JButton bShip;
	/**
	 * Button for chess help screen.
	 */
	private JButton chess;
	/**
	 * Button to exit the help screen.
	 */
	private JButton exit;
	/**
	 * Whether or not the user wants to exit.
	 */
	private boolean exitStatus;
	/**
	 * Icon for help.
	 */
	private ImageIcon helpMenu;
	/**
	 * Icon for main menu.
	 */
	private ImageIcon mainMenu;

	/**
	 * Help message for battleship.
	 */
	private static final String BHELP = "<html> Enter in player names <br/> "
			+ "Select your ship placement by selecting the ship you want to place <br/>"
			+ "then selected the first square where you want the ship to be placed <br/>"
			+ "then selected a square that is the ship's length away to place the ship <br/>"
			+ "in that location.The number next to a ships name is its length. <br/>"
			+ "Aircraft Carrier = 5 <br/>"
			+ "Battleship = 4 <br/>"
			+ "Submarine = 3 <br/>"
			+ "Cruiser = 3 <br/>"
			+ "Destroyer = 2 <br/>"
			+ "After ships have been placed players begin firing at their opponent's ship. <br/>"
			+ "To fire select a square on your upper (opponent's) board and hit fire. <br/>"
			+ "The selected square will turn red with a hit or white with a miss. <br/>"
			+ "When an opponent fires your bottom board will respond likewise. <br/>"
			+ "Your ships at the bottom will turn yellow if they have been hit <br/>"
			+ "and orange if they are one hit away from being sunk. <br/>"
			+ "Once a ship has been completely hit (all squares) the ship is sunk. <br/>"
			+ "After all ships have been sunk the player has lost. </html> ";
	/**
	 * Help message for chess.
	 */
	private static  final String CHELP = "<html> chess rules </html>";
	
	/**
	 * Constraints for GridBag formating.
	 */
	private GridBagConstraints con;
	
	/**
	 * Creates the help panel with buttons.
	 * @param bShipLogo logo for bship button
	 * @param chessLogo logo for chess button
	 */
	public HelpPanel(final ImageIcon bShipLogo, final ImageIcon chessLogo) {
		setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		addButtons();

		bShip.setIcon(bShipLogo);
		chess.setIcon(chessLogo);
		exitStatus = false;
	}
	/**
	 * Get he exit status.
	 * @return True if exit is pressed, false if nt
	 */
	public boolean getExit() {
		return exitStatus;
	}
	
	/**
	 * Add buttons to the panel.
	 */
	public void addButtons() {
		helpMenu = new ImageIcon("src/MainMenu/helpMenuLogo.png");
		message = new JLabel();
		message.setIcon(helpMenu);
		con.gridx = 0;
		con.gridy = 0;
		add(message, con);
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridy++;
		bShip = new JButton();
		bShip.addActionListener(this);
		add(bShip, con);
		
		con.gridy++;
		chess = new JButton();
		chess.addActionListener(this);
		add(chess, con);
			
		con.gridy++;
		mainMenu = new ImageIcon("src/MainMenu/mmLogo.png");
		exit = new JButton();
		exit.setIcon(mainMenu);
		exit.addActionListener(this);
		add(exit, con);
	}

	/**
	 * Remove the buttons from the panel.
	 */
	public void removeButtons() {
		remove(message);
		remove(bShip);
		remove(chess);
		revalidate();
		repaint();
	}
	
	/**
	 * Add the help panel for the corresponding game.
	 * @param str the name of the game user wants help on.
	 */
	public void addHelp(final String str) {
		con.gridx = 0;
		con.gridy = 0;
		if (str.equals("battle")) {
			JLabel h = new JLabel();
			h.setIcon(bShip.getIcon());				
			add(h, con);
			con.gridy++;
			add(new JLabel(BHELP), con);
		}
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == bShip) {
			removeButtons();
			addHelp("battle");
		}
		if (e.getSource() == chess) {
			removeButtons();
			addHelp("chess");
		}
		if (e.getSource() == exit) {
			exitStatus = true;
		}
	}
	
}
