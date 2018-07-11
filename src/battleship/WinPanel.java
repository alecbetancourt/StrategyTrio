package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;





//Panel displayed after a player has won, will improve look later
public class WinPanel extends JPanel{
	private String pName;
	private JButton rematchButton;
	private boolean rematch;
	private ButtonListener listen;
	private GridBagConstraints con;
	
	public WinPanel(String name) {
	rematch = false;
	setBackground(Color.CYAN);
	//setLayout(new GridLayout(2,1));
	setLayout(new GridBagLayout());
	con = new GridBagConstraints();
	con.gridx =0;
	con.gridy =0;
	pName = name;
	add(new JLabel(pName + " has won"), con);
	
	con.gridy++;
	con.ipadx = 100;
	con.ipady = 60;
	listen = new ButtonListener();
	rematchButton = new JButton("Rematch?");
	rematchButton.addActionListener(listen);
	rematchButton.setBackground(Color.BLUE);
	add(rematchButton,con);
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
