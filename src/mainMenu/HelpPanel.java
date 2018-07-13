package mainMenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel implements ActionListener{
	private JLabel message;
	private JButton bShip;
	private JButton chess;
	private JButton exit;
	private boolean exitStatus;

	//Html tags necessary for Jlabel formating, \n doesnt work.
	private final String bHelp = "<html><center> Enter in player names <br/> "
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
			+ "After all ships have been sunk the player has lost.</center> </html> ";
	private final String cHelp = "<html> </html>";
	
	private GridBagConstraints con;
	
	public HelpPanel() {
		setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		addButtons();
		exitStatus = false;
	}
	public boolean getExit() {
		return exitStatus;
	}
	public void addButtons() {
		message = new JLabel("Help Menu");
		con.gridx =0;
		con.gridy =0;
		add(message,con);
		con.ipadx = 300;
		con.ipady = 200;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridy++;
		bShip = new JButton("Battleship");
		bShip.addActionListener(this);
		add(bShip,con);
		
		con.gridy++;
		chess = new JButton("Chess");
		chess.addActionListener(this);
		add(chess,con);
				
		con.gridy++;
		exit = new JButton("Main Menu");
		exit.addActionListener(this);
		add(exit,con);
	}

	public void removeButtons() {
		remove(message);
		remove(bShip);
		remove(chess);
		revalidate();
		repaint();
	}
	
	public void addHelp(String str) {
		con.gridx =0;
		con.gridy =0;
		if(str.equals("battle")) {
			add(new JLabel("Battle Ship Rules"),con);
			con.gridy++;
			add(new JLabel(bHelp),con);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bShip) {
			removeButtons();
			addHelp("battle");
		}
		if(e.getSource() == chess) {
			removeButtons();
			addHelp("chess");
		}
		if(e.getSource() == exit) {
			exitStatus = true;
		}
	}
	
}
