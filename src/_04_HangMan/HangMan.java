package _04_HangMan;

import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class HangMan {

	static HangMan play;

	JFrame frame;
	JPanel panel;
	JLabel label;
	JLabel label2;
	Stack<String> words = new Stack<String>();
	final int MAXLIVES = 70;
	int lives = MAXLIVES;

	HangMan(JFrame frame, JPanel panel, JLabel label) {
		this.frame = frame;
		this.panel = panel;
		this.label = label;
	}

	public static void main(String[] args) {
		newGame();
	}

	static void newGame() {
		play = new HangMan(new JFrame(), new JPanel(), new JLabel());
		play.gui();
	}

	void gui() {
		label2 = new JLabel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.add(label);
		panel.add(label2);
		label.setText("Choose amount of Rounds");
		frame.pack();
		frame.setVisible(true);
		String wordCountStr = JOptionPane.showInputDialog("Enter the amount of rounds you want.");
		int wordCount = 1;
		try {
			wordCount = Integer.valueOf(wordCountStr);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,
					"There was a problem reading the number, so the game will only be 1 round");
		}
		selectWords(wordCount);
		manageGame();
	}

	void manageGame() {
		if (words.isEmpty() == true) {
			JOptionPane.showMessageDialog(frame, "Something went wrong");
			System.exit(0);
		}
		String toGuess;
		for (int j = 0; j < words.size(); j++) {
			lives = MAXLIVES;
			toGuess = words.pop();
			guessWord(toGuess);
		}
		String playAgain = JOptionPane
				.showInputDialog("You won! Do you want to play again? Type Y for yes or N for no.");
		if (playAgain.equals("Y")) {
			System.out.println("Should play Again");
			newGame();
		} else {
			System.exit(0);
		}
	}

	void guessWord(String word) {
		boolean guessed = false;
		ArrayList<Character> guessedChars = new ArrayList<Character>();
		while (guessed == false) {
			label2.setText("Lives: " + lives);
			String toPrint = "";
			int blankCount = 0;
			for (int j = 0; j < word.length(); j++) {
				if (guessedChars.contains(word.toCharArray()[j])) {
					toPrint = toPrint + word.toCharArray()[j];
				} else if (word.toCharArray()[j] == ' ') {
					toPrint = toPrint + " ";
				} else {
					toPrint = toPrint + "-";
					blankCount++;
				}
			}
			if (blankCount == 0) {
				guessed = true;
				JOptionPane.showMessageDialog(frame,
						"You did it! The word was: " + word + ". Click OK to move on the next round.");
				break;
			}
			label.setText(toPrint);
			String guessedCharStr = JOptionPane.showInputDialog("Guess a letter.");
			char gC;
			if (guessedCharStr.length() == 1) {
				gC = guessedCharStr.toCharArray()[0];
				if (gC != ' ') {
					if (guessedChars.contains(gC)) {
						JOptionPane.showMessageDialog(frame, "You already guessed this letter");
					} else {
						if (word.contains(String.valueOf(gC))) {
							guessedChars.add(gC);
						} else {
							guessedChars.add(gC);
							lives--;
						}
					}
				} else {
					JOptionPane.showMessageDialog(frame, " try again.");
				}
			} else {
				JOptionPane.showMessageDialog(frame, " try again");
			}
			if (lives <= 0) {
				String playAgain = JOptionPane
						.showInputDialog("You Lost. Do you want to play again? Type Y for yes or N for no.");
				if (playAgain.equals("Y")) {
					System.out.println("Should play Again");
					newGame();
				} else {
					System.exit(0);
				}
			}
		}

	}

	void selectWords(int count) {
		for (int j = 0; j < count; j++) {
			String newWord = Utilities.readRandomLineFromFile("dictionary.txt");
			if (words.contains(newWord)) {
				newWord = recursiveWordSelection();
			}
			words.push(newWord);
		}
	}

	String recursiveWordSelection() {

		String word = Utilities.readRandomLineFromFile("dictionary.txt");
		if (words.contains(word)) {
			word = recursiveWordSelection();
			return word;
		} else {
			return word;
		}
	}

}
