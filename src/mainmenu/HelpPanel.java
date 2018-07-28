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
	 * Button for tic tac toe help.
	 */
	private JButton tic;
	/**
	 * button for connect4 help.
	 */
	private JButton connect;
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
	private static final String CHHELP = "<html> chess rules </html>";
	/**
	 * Help message for tic tac toe.
	 */
	private static final String THELP = "<html> Player 1 is X and Player 2 is O <br/>"
			+ "Player 1 selects a square to mark X then player 2 selects a square to mark O. <br/>"
			+ "This continues until one player has gotten three of their symbols in a row <br/>"
			+ "then that player has won. </html>";
	/**
	 * Help message for connect4.
	 */
	private static final String COHELP = "<html>Player 1 uses the black tokens and Player 2 uses red tokens.<br/>"
			+ "Select the corresponding button at the bottom of the screen to the column you <br/>"
			+ "want to place your token. The token will be placed at the next avalaible slot in that <br/>"
			+ "column or it won't be placed at all if the column is full.The game continues until the<br/> "
			+ "board is full with no winner or a player has 4 tokens placed in  a row. The game <br/> "
			+ "can be won with 4 in a row either vertically, horizontally, or diagonally. </html>";
	/**
	 * Constraints for GridBag formating.
	 */
	private GridBagConstraints con;
	
	/**
	 * Creates the help panel with buttons.
	 * @param bShipLogo logo for bship button
	 * @param chessLogo logo for chess button
	 * @param ticLogo logo for tic button
	 * @param connectLogo logo for connect4 button.
	 * @param ret Jbutton to return to main menu.
	 */
	public HelpPanel(final ImageIcon bShipLogo, final ImageIcon chessLogo, final ImageIcon ticLogo,
			final ImageIcon connectLogo, final JButton ret) {
		setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		exit = ret;
		addButtons();

		bShip.setIcon(bShipLogo);
		chess.setIcon(chessLogo);
		tic.setIcon(ticLogo);
		connect.setIcon(connectLogo);
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
		tic = new JButton();
		tic.addActionListener(this);
		add(tic, con);
		
		con.gridy++;
		connect = new JButton();
		connect.addActionListener(this);
		add(connect, con);
			
		con.gridy++;
		add(exit, con);
	}

	/**
	 * Remove the buttons from the panel.
	 */
	public void removeButtons() {
		remove(message);
		remove(bShip);
		remove(chess);
		remove(tic);
		remove(connect);
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
		} else if (str.equals("chess")) {
			JLabel h = new JLabel();
			h.setIcon(chess.getIcon());				
			add(h, con);
			con.gridy++;
			add(new JLabel(CHHELP), con);
		} else if (str.equals("tic")) {
			JLabel h = new JLabel();
			h.setIcon(tic.getIcon());				
			add(h, con);
			con.gridy++;
			add(new JLabel(THELP), con);
		} else if (str.equals("connect")) {
			JLabel h = new JLabel();
			h.setIcon(connect.getIcon());				
			add(h, con);
			con.gridy++;
			add(new JLabel(COHELP), con);
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
		if (e.getSource() == tic) {
			removeButtons();
			addHelp("tic");
		}
		if (e.getSource() == connect) {
			removeButtons();
			addHelp("connect");
		}
		//Remove exit button, pass in as param to construct have it set to mainmenu action listen
		if (e.getSource() == exit) {
			
			new MenuGUI();
		}
	}
	
}
