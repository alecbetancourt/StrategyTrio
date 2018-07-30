package mainmenu;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Screen for changing player names.
 * @author Parker
 *
 */
public class NameDialog extends JPanel implements ActionListener {
	/**
	 * ID for dialog panel.
	 */
	private static final long serialVersionUID = 7808067835476842120L;
	/**
	 * Player 1's name.
	 */
	private String name1;
	/**
	 * Player 2's name.
	 */
	private String name2;
	/**
	 * Labels for prompting user entry.
	 */
	private JLabel text1, text2;
	/**
	 * Fields for users to enter their names.
	 */
	private JTextField field1, field2;
	/**
	 * Button to enter a users name.
	 */
	private JButton enter1, enter2;
	/**
	 * Constraints for formmating.
	 */
	private GridBagConstraints con;

	/**
	 * Constructor for name dialog panel.
	 * @param save button for saving names.
	 * @param name1 player 1's current name.
	 * @param name2 player 2's current name.
	 */
	public NameDialog(final JButton save, final String name1, final String name2) {
		setLayout(new GridBagLayout());
		this.name1 = name1;
		this.name2 = name2;
		text1 = new JLabel("Enter Player 1's name");
		text2 = new JLabel("Enter Player 2's name");
		field1 = new JTextField(10);
		field2 = new JTextField(10);
		enter1 = new JButton("Enter");
		enter1.addActionListener(this);
		enter2 = new JButton("Enter");
		enter2.addActionListener(this);


		con = new GridBagConstraints();
		con.gridx = 0;
		con.gridy = 0;
		con.fill = GridBagConstraints.HORIZONTAL;

		add(text1, con);

		con.gridwidth = 10;
		con.gridx++;
		add(field1, con);

		con.gridwidth = 1;
		con.gridx += 10;
		add(enter1, con);

		con.gridx = 0;
		con.gridy = 1;

		add(text2, con);

		con.gridwidth = 10;
		con.gridx++;
		add(field2, con);

		con.gridwidth = 1;
		con.gridx += 10;
		add(enter2, con);
		con.gridx = 0;
		con.gridy = 2;
		con.gridwidth = 13;
		con.gridheight = 2;
		add(save, con);

	}
	/**
	 * Get player 1's name.
	 * @return player 1's name.
	 */
	public String getName1() {
		return name1;
	}
	/**
	 * Get player 2's name.
	 * @return player 2's name.
	 */
	public String getName2() {
		return name2;
	}
	@Override
	public void actionPerformed(final ActionEvent e) {
		if (e.getSource() == enter1) {
			try {
				name1 = field1.getText();
				if (name1.equals("")) {
					name1 = "Player 1";
				}
			} catch (NullPointerException ex) {
				name1 = "Player 1";
			}
		}
		if (e.getSource() == enter2) {
			try {
				name2 = field2.getText();
				if (name2.equals("")) {
					name2 = "Player 2";
				}
			} catch (NullPointerException ex) {
				name2 = "Player 2";
			}
		}
	}

}

