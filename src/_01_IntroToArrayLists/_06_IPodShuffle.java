package _01_IntroToArrayLists;

import java.util.ArrayList;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//Copyright The League of Amazing Programmers, 2015

public class _06_IPodShuffle implements ActionListener {
	ArrayList<Song> songs;
	JFrame frame;
	JPanel panel;
	JButton random;
	JButton stop;
	Song current;
	ArrayList<JButton> songButtons = new ArrayList<JButton>();

	public _06_IPodShuffle() {
		// 1. Use the Song class the play the demo.mp3 file.
		songs = new ArrayList<Song>();
		songs.add(new Song("demo.mp3"));
		songs.add(new Song("PriceCheck.mp3"));
		songs.add(new Song("SchoolShuffle.mp3"));
		run();

		/**
		 * 2. Congratulations on completing the sound check! * Now we want to make an
		 * iPod Shuffle that plays random music. * Create an ArrayList of Songs and a
		 * "Surprise Me!" button that will play a random song when it is clicked. * If
		 * you're really cool, you can stop all the songs, before playing a new one on
		 * subsequent button clicks.
		 */

	}

	void run() {
		frame = new JFrame();
		panel = new JPanel();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.add(panel);
		random = new JButton();
		stop = new JButton();
		random.setText("random");
		stop.setText("stop");
		random.addActionListener(this);
		stop.addActionListener(this);
		panel.add(stop);
		panel.add(random);
		songButtons();
		frame.pack();
	}
	void songButtons() {
		for (int i = 0; i < songs.size(); i++) {
			songButtons.add(new JButton());
			songButtons.get(songButtons.size()-1).setText(songs.get(i).songAddress);
			panel.add(songButtons.get(songButtons.size()-1));
			songButtons.get(songButtons.size()-1).addActionListener(this);
		}
		
	}
	public static void main(String[] args) {
		new _06_IPodShuffle();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton buttonClicked = (JButton) e.getSource();
		if(buttonClicked == random) {
			Random random = new Random();
			current = songs.get(random.nextInt(songs.size()));
			current.play();
		}
		else if(buttonClicked == stop) {
			current.stop();
		}
		else if(songButtons.contains(buttonClicked)) {
			if(current != null) {
				current.stop();
			}
			current = songs.get(songButtons.indexOf(buttonClicked));
			current.play();
		}
	}
}