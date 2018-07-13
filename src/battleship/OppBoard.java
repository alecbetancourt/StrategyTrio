package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Class for the opponent's board where user's target tand fire uopn opponent's ships
 * The board is made up of button type's and uses a 12x11 gridlayout.
 * 
 * @author Parker
 * @version 0.1
 */
public class OppBoard extends JPanel {


	private BattleButton[][] opp;
	private ButtonListener listen;
	private JButton fire;
	private boolean fired;
	private JLabel pName;
	private JLabel status;
	private int targetX,targetY;
	private boolean enabled;
	private BattleButton target;
	private ImageIcon fireLogo;

	/**
	 * Constructor for the board, initializes the board creation and 
	 * disables fire until called upon by the GUI. 
	 * 
	 * @param name for the player name
	 */
	public OppBoard(String name) {

		fired = false;
		enabled = false;
		opp = new BattleButton[10][10];
		targetX = targetY = -1;
		listen = new ButtonListener();
		setLayout(new GridLayout(12,11));
		pName = new JLabel(name + "'s board", SwingConstants.CENTER);
		status = new JLabel("Select Your Target", SwingConstants.CENTER);
		
		createBoard();
		disableFire();
		
		
	}
	
	/**
	 * Creates and formats the user board where ships are placed.
	 */
	public void createBoard() {
		for(int i =0; i< 11; i++) {
			for(int j =0; j <11; j++) {
				//blank square at 0,0
				if(j==0 && i== 0)
					add(new JLabel(""));
				//formatting x-axis
				else if(i ==0 ) {
					add(new JLabel("" + (j), SwingConstants.CENTER));
				}
				//formatting y-axis
				else if(j== 0) {
					int c = 64+i;
					String s = Character.toString((char)c);
					add(new JLabel(s,SwingConstants.CENTER));
				}
				else {
					opp[i-1][j-1] = new BattleButton();

					opp[i-1][j-1].addActionListener(listen);
					add(opp[i-1][j-1]);

				}
			}
		}
		add(pName);
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(new JLabel(""));
		add(status);
		fire = new JButton();
		fireLogo = new ImageIcon("src/battleship/fireLogo.png");
		fire.setIcon(fireLogo);
		fire.setBackground(Color.RED);
		fire.addActionListener(listen);
		setBackground(Color.CYAN);
		add(fire);
	}

	/**
	 * Resets target coordinates and enables selection and firing.
	 */
	public void enableFire() {
		enabled = true;
		targetX = targetY = -1;
		target = null;
		fired = false;
		fire.setEnabled(true);
	}

	/**
	 * disables target selection and the fire button
	 */
	public void disableFire() {
		enabled = false;
	}

	/**
	 *  @return user targeted x-coordinate
	 */
	public int getTargetX(){
		return targetX;
	}

	/**
	 *  @return user targeted x-coordinate
	 */
	public int getTargetY(){
		return targetY;
	}

	/**
	 * Whether or not the fired button has been pressed with a target
	 * @return True if user has fired, false if not
	 */
	public boolean isFired() {
		return fired;
	}
	/**
	 * Whether or not the user has properly selected a target and
	 * hit the fired button.
	 * @param fired True if the fired, false if not.
	 */
	public void setFired(boolean fired) {
		this.fired = fired;
	}

	/**
	 * Marks button as hit of miss depending on the boolean hit using the 
	 * respective BattleButton function
	 * @param hit whether the move was a hit or not
	 * @param x x-coordinate of target
	 * @param y y-coordinate of target
	 */
	public void markSquare(boolean hit, int x, int y) {
		if(hit)
			opp[x][y].setHit(true);
		else
			opp[x][y].setMiss(true);
	}
	
	/**
	 * Updates the game status displayed on the screen.
	 * @param text the message to be displayed
	 */
	public void updateStatus(String text) {
		//Easiest way to do this because most messages are taken from opponent board
		if(status.getText().equals("You lose")) {
			status.setText("You Win!");
		}
		else
			status.setText(text);
	}

	/**
	 * Listener for the board and fired button.
	 * @author Parker
	 * @version 0.1
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Responds to button presses. only usable when enabled is true. Players must first
		 * select a square which will light up as yellow on the screen then press the fire
		 * button to fire a shot.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(enabled) {
				if(e.getSource() == fire) {
					if(targetX != -1 && targetY != -1) {
						//Turns button orange on fire, doesnt really show however
						opp[targetX][targetY].setBackground(Color.ORANGE);
						fire.setEnabled(false);
						disableFire();
						target = null;
						//moves to next player turn after this
						setFired(true);
					}
				}
				else{
					for(int i =0; i< 10; i++) {
						for(int j =0; j <10; j++) {
							if(e.getSource() == opp[i][j]) {
								//resets selection
								if(target != null) {
									target.revertColor();
								}
								targetX = i;
								targetY = j;
								target = opp[i][j];
								
								//Displays selected square in 'A1, B2, C3,.." format
								int c = (i+65);
								status.setText((char)c +"" + (j+1) + " selected");
								
								//highlights selected button
								if(targetX != -1 && targetY != -1)
									opp[targetX][targetY].setBackground(Color.YELLOW);
							}
						}

					}
				}

			}
		}
	}
}
