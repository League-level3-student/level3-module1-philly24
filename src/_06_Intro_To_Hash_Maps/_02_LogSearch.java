package _06_Intro_To_Hash_Maps;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.MalformedInputException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class _02_LogSearch implements ActionListener {
	JFrame frame;
	JPanel panel;
	JButton add;
	JButton search;
	JButton view;
	JButton remove;
	HashMap<Integer, String> hash = new HashMap<Integer, String>();

	/*
	 * Crate a HashMap of Integers for the keys and Strings for the values. Create a
	 * GUI with three buttons. Button 1: Add Entry When this button is clicked, use
	 * an input dialog to ask the user to enter an ID number. After an ID is
	 * entered, use another input dialog to ask the user to enter a name. Add this
	 * information as a new entry to your HashMap.
	 * 
	 * Button 2: Search by ID When this button is clicked, use an input dialog to
	 * ask the user to enter an ID number. If that ID exists, display that name to
	 * the user. Otherwise, tell the user that that entry does not exist.
	 * 
	 * Button 3: View List When this button is clicked, display the entire list in a
	 * message dialog in the following format: ID: 123 Name: Harry Howard ID: 245
	 * Name: Polly Powers ID: 433 Name: Oliver Ortega etc...
	 * 
	 * When this is complete, add a fourth button to your window. Button 4: Remove
	 * Entry When this button is clicked, prompt the user to enter an ID using an
	 * input dialog. If this ID exists in the HashMap, remove it. Otherwise, notify
	 * the user that the ID is not in the list.
	 *
	 */
	void gui() {
		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		panel = new JPanel();
		frame.add(panel);
		add = new JButton();
		add.addActionListener(this);
		add.setText("add entry");
		search = new JButton();
		search.setText("search by ID");
		search.addActionListener(this);
		view = new JButton();
		view.setText("view list");
		view.addActionListener(this);
		remove = new JButton();
		remove.setText("remove entry");
		remove.addActionListener(this);
		panel.add(add);
		panel.add(search);
		panel.add(remove);
		panel.add(view);
		frame.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton buttonPressed = (JButton) e.getSource();
		if (add == buttonPressed) {
			String num = JOptionPane.showInputDialog("enter an ID Number");
			int ID = Integer.parseInt(num);
			String name = JOptionPane.showInputDialog("please enter a name");
			hash.put(ID, name);
		} else if (search == buttonPressed) {
			String search = JOptionPane.showInputDialog("enter a ID number");
			int searchNum = Integer.parseInt(search);
			if (hash.get(searchNum) == null) {

				JOptionPane.showMessageDialog(null, "does not exist");
			} else {
				System.out.println(hash.get(searchNum));
				JOptionPane.showMessageDialog(null, "the name is " + hash.get(searchNum));
			}

		} else if (view == buttonPressed) {
			JOptionPane.showMessageDialog(null, "ID: " + hash.keySet() + "  Name: " + hash.values());
		} else if (remove == buttonPressed) {
			String remove = JOptionPane.showInputDialog("enter the ID you want to remove ");
			int removeNum = Integer.parseInt(remove);
			if (hash.get(removeNum) == null) {
				JOptionPane.showMessageDialog(null, "the ID does not exist");
			}
			else {
				hash.remove(removeNum);
			}
		}
	}

	public static void main(String[] args) {
		_02_LogSearch run = new _02_LogSearch();
		run.gui();
	}
}
