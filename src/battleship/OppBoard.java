package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
	public void testSwitch() {
		
	}
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
		fire = new JButton("FIRE!");
		fire.setBackground(Color.RED);
		fire.addActionListener(listen);
		setBackground(Color.CYAN);
		add(fire);
	}

	public void enableFire() {
		enabled = true;
		targetX = targetY = -1;
		target = null;
		fired = false;
		fire.setEnabled(true);
	}

	public void disableFire() {
		enabled = false;

	}

	public int getTargetX(){
		return targetX;
	}

	public int getTargetY(){
		return targetY;
	}

	public boolean isFired() {
		return fired;
	}

	public void setFired(boolean fired) {
		this.fired = fired;
	}

	public void markSquare(boolean hit, int x, int y) {
		if(hit)
			hit(x,y);
		else
			miss(x,y);
	}
	public void hit(int x, int y) {
		opp[x][y].setHit(true);
		opp[x][y].setBackground(Color.RED);
	}

	public void miss(int x, int y) {
		opp[x][y].setMiss(true);
		opp[x][y].setBackground(Color.WHITE);
	}
	
	public void updateStatus(String text) {
		if(status.getText().equals("You lose")) {
			//doesnt work probably something with getText
			status.setText("You Win!");
		}
		else
			status.setText(text);
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(enabled) {
				if(e.getSource() == fire) {
					if(targetX != -1 && targetY != -1) {
						opp[targetX][targetY].setBackground(Color.ORANGE);
						fire.setEnabled(false);
						disableFire();
						target = null;
						setFired(true);
					}
				}
				else{
					for(int i =0; i< 10; i++) {

						for(int j =0; j <10; j++) {
							if(e.getSource() == opp[i][j]) {
								if(target != null) {
									target.revertColor();
								}
								targetX = i;
								targetY = j;
								target = opp[i][j];
								int c = (i+65);
								status.setText((char)c +"" + (j+1) + " selected");

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
