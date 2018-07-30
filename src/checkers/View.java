package checkers;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * JPanel for the chess game.
 * @author Parker
 *
 */
public class View extends JPanel {
	/**
	 * View ID.
	 */
	private static final long serialVersionUID = -2223695223616731728L;
	/**
	 * The model for the chess game.
	 */
	private Model model;
	/**
	 * The board for the game.
	 */
	private JButton[][] board;
	/**
	 * The current move.
	 */
	private Move move;
	/**
	 * Menu item to create new game.
	 */
	private JMenuItem newGame;
    /**
     * Menu item to quit game.
     */
	private JMenuItem quitGame;
	/**
	 * Both of the piece icons.
	 */
	private ImageIcon pieceIconR, pieceIconB;
	/**
	 * Brown used for background tiles.
	 */
	private static final Color BROWN = new Color(205, 133, 63);
	/**
	 * Tan used for background tiles.
	 */
	private static final Color TAN = new Color(210, 180, 140);
	
	/**
	 * Player names.
	 */
	private String name1, name2;
	
	//will likely be deleted in favor of master UI in release 2
	//add new game/quit function in menu options
	/**
	 * Creates the JFrame and chess panel.
	 * @param args for main
	 */
	public static void main(final String[] args) {
		JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitGame;
        JMenuItem newGame;

        JFrame frame = new JFrame("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Creates and adds menu options to file menu which is then added to menu bar
        fileMenu = new JMenu("File");
        quitGame = new JMenuItem("Quit");
        newGame = new JMenuItem("New Game");

        fileMenu.add(newGame);
        fileMenu.add(quitGame);
        menus = new JMenuBar();
        frame.setJMenuBar(menus);
        menus.add(fileMenu);
		
		frame.add(new View(quitGame, newGame, "Player 1", "Player 2"));
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	/**
	 * Creates the board and all pieces/icons.
	 * 
	 * @param pquitGame quit game menu item.
	 * @param pnewGame new game menu item
	 * @param n1 player 1's name.
	 * @param n2 player 2's name.
	 */
	public View(final JMenuItem pquitGame, final JMenuItem pnewGame, final String n1, final String n2) {
		name1 = n1;
		name2 = n2;
		model = new Model(name1, name2);
		board = new JButton[8][8];
		move = new Move();
		newGame = pnewGame;
        quitGame = pquitGame;
		pieceIconR = new ImageIcon("src/checkers/pieceIconR.png");
		pieceIconB = new ImageIcon("src/checkers/pieceIconB.png");
		
		setLayout(new GridLayout(8, 8));
		ButtonListener listener = new ButtonListener();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				board[row][col] = new JButton();
				if ((row + col) % 2 == 0) {
	                board[row][col].setBackground(BROWN);
	            } else {
	                board[row][col].setBackground(TAN);
	            }
				board[row][col].addActionListener(listener);
				add(board[row][col]);
			}
		}
		
		//add listeners to menu items
		quitGame.addActionListener(listener);
        newGame.addActionListener(listener);
		
		updateBoard();
	}
	
	/**
	 * Listener for board buttons.
	 * @author Alec
	 *
	 */
	private class ButtonListener implements ActionListener {
		/**
		 * Respond to button clicks.
		 * @param event what was pressed.
		 */
		public void actionPerformed(final ActionEvent event) {
			Object source = event.getSource();
			for (int row = 0; row < 8; row++) {
				for (int col = 0; col < 8; col++) {
					if (board[row][col] == source) {
						if (move.fromRow == -1 && model.pieceAt(row, col) != null
							&& model.pieceAt(row, col).team().equals(model.
									currentPlayer().team())) {
							System.out.print("Piece selected: " 
									+ model.pieceAt(row, col).team() + " ");
							//System.out.println(model.pieceAt(row, col).type());
							move.fromRow = row;
							move.fromColumn = col;
						} else if (move.fromRow != -1) {
							move.toRow = row;
							move.toColumn = col;
							System.out.println(move);
							if (move.toRow == move.fromRow && move.toColumn 
									== move.fromColumn) {
								move = new Move();
							} else if (model.isValidMove(move)) {
								model.move(move);
								move = new Move();
								System.out.println("Valid " + move);
								if (model.isWinner()) {
									model.wipeBoard();
									JOptionPane.showMessageDialog(null, 
											model.currentPlayer().name() 
											+ " has won!");
								}
								model.nextTurn();
							} else {
								JOptionPane.showMessageDialog(null,
										"This is not a valid move");
							}
						} else {
							JOptionPane.showMessageDialog(null, 
									"This is not a valid selection");
						}
					}
				}
			}
			//reset model if new game option is selected
            if (source == newGame) {    
            	model = new Model(name1, name2);
            }
            
            //quit game if menu option was selected
            if (source == quitGame) {
                //either exit to main menu or exit jvm completely
            	System.exit(0);
            }
			updateBoard();
		}
	}
	
	/**
	 * Update the look of the board after a move.
	 */
	private void updateBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (model.pieceAt(row, col) != null && model.pieceAt(row, col).team() == "RED") {
					board[row][col].setIcon(pieceIconR);
				} else if (model.pieceAt(row, col) != null && model.pieceAt(row, col).team() 
						== "BLACK") {
					board[row][col].setIcon(pieceIconB);
				} else {
					board[row][col].setIcon(null);
				}
			}
		}		
	}
}
