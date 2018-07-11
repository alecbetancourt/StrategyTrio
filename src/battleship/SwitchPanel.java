package battleship;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/******
 * Panel used between turns so players on the same computer can't see each others board
 * @author Parker
 *
 */
public class SwitchPanel extends JPanel{
	private String pName;
	private JButton ready;
	private boolean isReady;
	private ButtonListener listen;
	private GridBagConstraints con;
	
	public SwitchPanel(String name) {
		isReady = false;
		setBackground(Color.CYAN);
		setLayout(new GridBagLayout());
		con = new GridBagConstraints();
		con.gridx =0;
		con.gridy =0;
		pName = name;
		add(new JLabel(pName + "'s turn"),con);
		
		con.ipadx = 100;
		con.ipady = 60;
		con.gridy++;
		listen = new ButtonListener();
		ready = new JButton("Ready");
		ready.setBackground(Color.BLUE);
		ready.addActionListener(listen);
		add(ready,con);
		
		
	}
	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
	public boolean isReady() {
		return isReady;
	}
	public void setReady(boolean isRready) {
		this.isReady = isRready;
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == ready) {
				isReady = true;
			}
		}
		
	}

}
