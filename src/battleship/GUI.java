package battleship;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class GUI extends JFrame implements ActionListener{

	private Board p1;
	private OppBoard p1O;
	private Board p2;
	private OppBoard p2O;

	private JMenuBar menus;
	private JMenu option;
	private JMenuItem newGameItem;
	private JMenuItem closeItem;
	private JMenuItem mainMenuItem;
	
	private JPanel[] holder;

	private SwitchPanel p1Switch;
	private SwitchPanel p2Switch;
	
	private WinPanel end;

	private boolean hasWon;
	private String name1;
	private String name2;
	
	private String winner;
	
	public GUI() {
		createMenus();		
		newGame();
	}

	public void createMenus() {
		//Will work on actionlistener later
		option = new JMenu("Menu");
		newGameItem = new JMenuItem("New Game");
		newGameItem.addActionListener(this);
		mainMenuItem = new JMenuItem("Main Menu");
		mainMenuItem.addActionListener(this);
		closeItem = new JMenuItem("Quit");
		closeItem.addActionListener(this);

		//Menu buttons are dead for now
		menus = new JMenuBar();
		menus.add(option);
		option.add(newGameItem);
		option.add(mainMenuItem);
		option.add(closeItem);
	}

	public void newGame() {	
		getNames();

		p1 = new Board(name1);
		p2 = new Board(name2);

		//Opponent "top" board
		p1O = new OppBoard(name2);
		p2O = new OppBoard(name1);

		p1Switch = new SwitchPanel(name1);
		p2Switch = new SwitchPanel(name2);

		setLayout(new GridLayout(2,1));

		holder = new JPanel[2];

		for(int i =0; i <2; i++) {
			holder[i] = new JPanel();
			add(holder[i]);
		}
		holder[0].setLayout(new GridLayout(1,1));
		holder[1].setLayout(new GridLayout(1,1));
		holder[1].add(p1);
		holder[0].add(p1O);
		
		//for testing switch/win panels
//		holder[1].add(p1Switch);
//		holder[0].add(new WinPanel("Parker"));


		setTitle("Battleship");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);      
		setJMenuBar(menus);
		setSize (1500,1000);
		setVisible(true);
		hasWon = false;
		placeShips();
		playGame();
	}

	public void getNames() {
		name1 = JOptionPane.showInputDialog (null, "What is Player 1's name?");
		try{
			if(name1.equals(""))
				name1 = "Player 1";
		}
		catch(NullPointerException ex) {
			name1 = "Player 1";
		}
		
		name2 = JOptionPane.showInputDialog (null, "What is Player 2's name?");
		try{
			if(name2.equals(""))
			name2 = "Player 2";
		}
		catch(NullPointerException ex) {
			name2 = "Player 2";
		}
	}


	public void playGame() {
		int x,y;
		boolean hit;
		hasWon = false;
		//infinite loop right now need to make a function to determine if game is over
		while(!hasWon) {

			p1O.enableFire();
			while(!p1O.isFired()) {
				try {
					Thread.yield();
				} catch (Exception interruptedEx) {
					// 
				}
			}
			//get Targeted coordinates
			x = p1O.getTargetX();
			y = p1O.getTargetY();
			
			//check user board for hit
			hit = p2.hitMiss(x, y);
			
			//mark square on opponent board
			p1O.markSquare(hit, x, y);
			p1O.updateStatus(p2.getStatus());
			hasWon = p2.isLost();
			if(!hasWon) {
				switchP2();
				p2O.enableFire();
				while(!p2O.isFired()) {
					try {
						Thread.yield();
					} catch (Exception interruptedEx) {
						// do nothing?
					}
				}

				x = p2O.getTargetX();
				y = p2O.getTargetY();
				hit = p1.hitMiss(x, y);
				p2O.markSquare(hit, x, y);
				p2O.updateStatus(p1.getStatus());
				hasWon = p1.isLost();
				if(!hasWon)
					switchP1();
				
				//Googled this solution because loop was not updating. May have to change
				try {
					Thread.yield();
				} catch (Exception interruptedEx) {
					// Log the interruption somewhere.
				}
				

			}
		}
		p1O.disableFire();
		p2O.disableFire();
		if(p1.isLost())
			winner = name2;
		else
			winner = name1;
		winScreen(winner);
	}

	public void winScreen(String winner) {
		end = new WinPanel(winner);
		holder[0].removeAll();
		holder[0].add(end);
		revalidate();
		repaint();
	}
	public void placeShips() {
		//player 1 places ships
		while(!p1.isReady()) {
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// Log the interruption somewhere.
			}
		}

		switchP2();
		while(!p2.isReady()) {
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// 
			}
		}
		switchP1();
	}


	public void switchP2() {
		holder[1].removeAll();
		holder[1].add(p2Switch);
		revalidate();
		repaint();
		
		//waiting for p2 to hit ready
		while(!p2Switch.isReady()) {
			//Googled this solution because loop was not updating
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// 
			}
		}
		p2Switch.setReady(false);
		holder[0].removeAll();
		holder[1].removeAll();
		holder[0].add(p2O);
		holder[1].add(p2);
		revalidate();
		repaint();
	}

	public void switchP1() {
		holder[1].removeAll();
		holder[1].add(p1Switch);
		revalidate();
		repaint();

		while(!p1Switch.isReady()) {
			//Googled this solution because loop was not updating
			try {
				Thread.yield();
			} catch (Exception interruptedEx) {
				// 
			}
		}
		p1Switch.setReady(false);
		holder[0].removeAll();
		holder[1].removeAll();
		holder[0].add(p1O);
		holder[1].add(p1);
		revalidate();
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Need to work on listener for menus
		if(e.getSource() == newGameItem) {
			//dispose current frame, create new

		}

		if(e.getSource() == closeItem) {
			this.dispose();
			revalidate();
			repaint();
		}
	}




	public static void main(String[] args) {
		new GUI();
	}
}

