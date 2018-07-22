package chess;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ChessGUI {
	public ChessGUI(){
		JMenuBar menus;
        JMenu fileMenu;
        JMenuItem quitGame;
        JMenuItem newGame;

        JFrame frame = new JFrame("Chess");
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
		
		frame.add(new View(quitGame, newGame));
	    frame.pack();
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
}
