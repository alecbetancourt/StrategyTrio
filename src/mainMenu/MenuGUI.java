package mainMenu;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import battleship.*;
import chess.*;


public class MenuGUI extends JFrame implements ActionListener {

	private JPanel screen;
	private JLabel message;
	private JButton bShip;
	private JButton chess;
	private JButton help;
	private JButton exit;
	private GridBagConstraints con;

	private JMenuBar menus;
	private JMenu option;
	private JMenuItem closeItem;

	private HelpPanel hPanel;
	
	private ImageIcon logo;
	private ImageIcon battleLogo;
	private ImageIcon chessLogo;
	private ImageIcon helpLogo;
	private ImageIcon exitLogo;
	
	private battleship.GUI battleGUI;


	public MenuGUI() {
		screen = new JPanel();
		screen.setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		logo = new ImageIcon("src/MainMenu/logo.png");
		battleLogo = new ImageIcon("src/MainMenu/bShipLogo.png");
		chessLogo = new ImageIcon("src/MainMenu/chessLogo.png");
		helpLogo = new ImageIcon("src/MainMenu/helpLogo.png");
		exitLogo = new ImageIcon("src/MainMenu/exitLogo.png");
		
		con.gridx =0;
		con.gridy =0;
		con.ipady = 100;
		message = new JLabel();
		message.setIcon(logo);
		screen.add(message,con);

		addButtons();
		add(screen);
		addMenus();

		setTitle("Strategy Arcade");
		setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);      
		setJMenuBar(menus);
		setSize (1500,1000);
		setVisible(true);
	}

	public void addButtons() {
		con.ipadx = 0;
		con.ipady = 0;
		con.fill = GridBagConstraints.HORIZONTAL;
		con.gridy++;
		
		bShip = new JButton();
		bShip.setIcon(battleLogo);
		bShip.addActionListener(this);
		screen.add(bShip,con);

		con.gridy++;
		chess = new JButton();
		chess.setIcon(chessLogo);
		chess.addActionListener(this);
		screen.add(chess,con);

		con.gridy++;
		help = new JButton();
		help.setIcon(helpLogo);
		help.addActionListener(this);
		screen.add(help, con);
		
		con.gridy++;
		exit = new JButton();
		exit.setIcon(exitLogo);
		exit.addActionListener(this);
		screen.add(exit,con);
	}

	public void addMenus() {
		option = new JMenu("Menu");
		closeItem = new JMenuItem("Quit");
		closeItem.addActionListener(this);

		menus = new JMenuBar();
		menus.add(option);
		option.add(closeItem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit || e.getSource() == closeItem)
			System.exit(0);
		//doesnt work,gui wont update, probably an issue with package/import
		if(e.getSource() == bShip) {
			battleGUI = new battleship.GUI();
			//add(new battleship.GUI());

			this.setContentPane(battleGUI);
			battleGUI.setVisible(true);
			revalidate();
			repaint();
		}
		//Doesnt work, gui wont update, probably an issue with package/import
		if(e.getSource() == chess) {
			removeAll();
			this.setContentPane(new chess.View());
			revalidate();
			repaint();

		}
		if(e.getSource() == help) {
			this.remove(screen);
			hPanel = new HelpPanel(battleLogo, chessLogo);
			add(hPanel);
			revalidate();
			repaint();
			
			//for exit status, right now doesnt work 
//			while(!hPanel.getExit()) {
//				//Googled this solution because loop was not updating
//				try {
//					Thread.yield();
//				} catch (Exception interruptedEx) {
//					// 
//				}
//			}

		}
	}
	public static void main(String[] args) {
		new MenuGUI();
	}
}


