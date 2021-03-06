package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

/**
 * Class for the opponent's board where user's target tand fire upon
 *  opponent's ships.
 * The board is made up of button type's and uses a 12x11 gridlayout.
 * 
 * @author Parker
 * @version 0.1
 */
public class OppBoard extends JPanel {


	/**
	 *  Opp Board ID.
	 */
	private static final long serialVersionUID = 3950943788408894143L;
	/**
	 * 2-d button array for board.
	 */
	private BattleButton[][] opp;
	/**
	 * listener for board  and fire buttons.
	 */
	private ButtonListener listen;
	/**
	 * Button to press to fire.
	 */
	private JButton fire;
	/**
	 * Whether or not the fire button has been pressed.
	 */
	private boolean fired;
	/**
	 * Opponents name.
	 */
	private JLabel pName;
	/**
	 * Game status message.
	 */
	private JLabel status;
	/**
	 * X-coordinate of guess.
	 */
	private int targetX;
	/**
	 * Y-coordinate of guess.
	 */
	private int targetY;
	/**
	 * Whether or not the board is enabled to fire.
	 */
	private boolean enabled;
	/**
	 * The selected battlebutton.
	 */
	private BattleButton target;

	/**
	 * Constructor for the board, initializes the board creation and 
	 * disables fire until called upon by the GUI. 
	 * 
	 * @param name for the player name
	 * @param tFire button to fire a shot.
	 */
	public OppBoard(final String name, final JButton tFire) {

		fired = false;
		enabled = false;
		opp = new BattleButton[10][10];
		targetX = -1;
		targetY = -1;
		listen = new ButtonListener();
		setLayout(new GridLayout(12, 11));
		pName = new JLabel(name + "'s board", SwingConstants.CENTER);
		status = new JLabel("Select Your Target", 
				SwingConstants.CENTER);
		tFire.setBorder(new MatteBorder(1, 1, 1, 1, Color.BLACK));
		fire = tFire;
		createBoard();
		disableFire();
		setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));


	}

	/**
	 * Creates and formats the user board where ships are placed.
	 */
	public void createBoard() {
		JLabel l;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				//blank square at 0,0
				if (j == 0 && i == 0) {
					add(new JLabel(""));
				} else if (i == 0) {
					l = new JLabel("" + j,
							SwingConstants.CENTER);
					add(l);
					if (j != 10) {
					l.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
					}
				} else if (j == 0) {
					int c = (64 + i);
					String s = Character.toString((char) c);
					l = new JLabel(s,
							SwingConstants.CENTER);
					l.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
					add(l);
				} else {
					opp[i - 1][j - 1] = new BattleButton();
					opp[i - 1][j - 1].addActionListener(listen);
					add(opp[i - 1][j - 1]);

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
		//		fire = new JButton();
		//		fireLogo = new ImageIcon("src/battleship/fireLogo.png");
		//		fire.setIcon(fireLogo);
		//		fire.setBackground(Color.RED);
		//		//fire.addActionListener(listen);

		setBackground(Color.GRAY);
		add(fire);
	}

	/**
	 * Resets target coordinates and enables selection and firing.
	 */
	public void enableFire() {
		enabled = true;
		targetX = -1;
		targetY = -1;
		target = null;
		fired = false;
		fire.setEnabled(true);
	}

	/**
	 * disables target selection and the fire button.
	 */
	public void disableFire() {
		enabled = false;
	}

	/**
	 *  @return user targeted x-coordinate
	 */
	public int getTargetX() {
		return targetX;
	}

	/**
	 *  @return user targeted x-coordinate
	 */
	public int getTargetY() {
		return targetY;
	}

	/**
	 * Whether or not the fired button has been pressed with a target.
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
	public void setFired(final boolean fired) {
		this.fired = fired;
	}

	/**
	 * returns fire button for testing.
	 * @return fire button
	 */
	public JButton getFire() {
		return fire;
	}

	/**
	 * Marks button as hit of miss depending on the boolean hit using the 
	 * respective BattleButton function.
	 * @param hit whether the move was a hit or not
	 * @param x x-coordinate of target
	 * @param y y-coordinate of target
	 */
	public void markSquare(final boolean hit, final int x, final int y) {
		if (!opp[x][y].isHit()) {
			if (hit) {
				opp[x][y].setHit(true);
			} else {
				opp[x][y].setMiss(true);
			}
		}
	}

	/**
	 * Returns a give jbutton for testing.
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return given JButton
	 */
	public JButton getButton(final int x, final int y) {
		return opp[x][y];
	}
	/**
	 * Returns status text.
	 * @return game status
	 */
	public String getStatus() {
		return status.getText();
	}
	/**
	 * Updates the game status displayed on the screen.
	 * @param text the message to be displayed
	 */
	public void updateStatus(final String text) {

		if (text.equals("You lose")) {
			status.setText("You Win!");
		} else {
			status.setText(text);
		}
	}

	/**
	 * Listener for the board and fired button.
	 * @author Parker
	 * @version 0.1
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Responds to button presses. only usable when enabled is true. 
		 * Players must first select a square which will light up as
		 *  yellow on the screen then press the fire
		 * button to fire a shot.
		 */
		@Override
		public void actionPerformed(final ActionEvent e) {
			if (enabled) {
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (e.getSource() == opp[i][j]) {
							if (opp[i][j].isEmpty()) {
								//resets selection
								if (target != null) {
									target.revertColor();
								}
								targetX = i;
								targetY = j;
								target = opp[i][j];

								//Displays selected square in 'A1, B2, C3,..' format
								int c = (i + 65);
								status.setText((char) c + "" + (j + 1) + " selected");

								//highlights selected button
								if (targetX != -1 && targetY != -1) {
									opp[targetX][targetY].setIcon(null);
									opp[targetX][targetY].setBackground(
											Color.YELLOW);
								}
							}
						}
					}

				}
				//}

			}
		}
	}
}
