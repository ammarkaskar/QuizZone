import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Quiz implements ActionListener {
	// ActionListener to handle button clicking events.
	// now we will create array of strings to hold the questions

	String questions[] = { "Which company created java?", "Which year was java created?",
			"What was java originally called?", "Who is credited with creating java?" };

	// Now create a 2D array of strings to hold all different possibility of the
	// answers for every questions
	String options[][] = { { "Sun microsystems", "Microsoft", "Starbucks", "Alphabet" },
			{ "1989", "1996", "1972", "1492" }, { "Apple", "Latte", "Oak", "Koffing" },
			{ "Steve Jobs", "Elon Musk", "James Gosling", "Bill Gates" } };

	// now create a array of characters or strings to hold all the different correct
	// answers.
	char answers[] = { 'A', 'B', 'C', 'C' };

	char guess;
	char answer;
	int index;
	int correct_guesses = 0;
	int total_questions = questions.length; // In case we want to add more questions(questions.length) it adjusts
											// dynamically.
	int result;
	int seconds = 10;

	JFrame frame = new JFrame();
	// This will display after calculating the results
	JTextField textfield = new JTextField();// it will hold the current question.
	JTextArea textarea = new JTextArea();
	JButton buttonA = new JButton();
	JButton buttonB = new JButton();
	JButton buttonC = new JButton();
	JButton buttonD = new JButton();

	JLabel answer_labelA = new JLabel();
	JLabel answer_labelB = new JLabel();
	JLabel answer_labelC = new JLabel();
	JLabel answer_labelD = new JLabel();

	JLabel time_label = new JLabel();
	JLabel seconds_left = new JLabel();

	JTextField number_right = new JTextField();
	JTextField percentage = new JTextField();

	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {// Now we want actionperformed method to execute every 1 sec
			// After each sec we will decrement our second variable
			seconds--;
			seconds_left.setText(String.valueOf(seconds));
			// when the timer reaches zero.
			if (seconds <= 0) {
				displayAnswer();// it will display answer, disable all buttons 
				// Now to start timer go to nextquestion method
			}
		}

	});

	public Quiz() {// Now within the constructer we design the components we have above

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(650, 650);
		frame.getContentPane().setBackground(new Color(135, 206, 250));
		frame.setLayout(null);
		frame.setResizable(false);

		// setBounds will decide where the textfield will be
		textfield.setBounds(0, 0, 650, 50);
		textfield.setBackground(new Color(25, 25, 25));
		textfield.setForeground(new Color(25, 255, 0));
		textfield.setFont(new Font("Roboto Mono", Font.BOLD, 30));
		textfield.setBorder(BorderFactory.createBevelBorder(1));
		textfield.setHorizontalAlignment(JTextField.CENTER);
		textfield.setEditable(false);

		textarea.setBounds(0, 50, 650, 50);
		// when text goes off the screen or off the text area it will wrap around nxt
		// line
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setBackground(new Color(25, 25, 25));
		textarea.setForeground(new Color(25, 255, 0));
		textarea.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 25));
		textarea.setBorder(BorderFactory.createBevelBorder(1));
		textarea.setEditable(false);

		buttonA.setBounds(0, 100, 100, 100);
		buttonA.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));
		buttonA.setBackground(new Color(75, 0, 130));
		buttonA.setFocusable(false); // prevents button highlighting
		buttonA.addActionListener(this);
		buttonA.setForeground(new Color(0, 0, 0));
		buttonA.setText("A");

		buttonB.setBounds(0, 200, 100, 100);
		buttonB.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));
		buttonB.setBackground(new Color(75, 0, 130));
		buttonB.setFocusable(false); // prevents button highlighting
		buttonB.addActionListener(this);
		buttonB.setForeground(new Color(0, 0, 0));
		buttonB.setText("B");

		buttonC.setBounds(0, 300, 100, 100);
		buttonC.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));
		buttonC.setBackground(new Color(75, 0, 130));
		buttonC.setFocusable(false); // prevents button highlighting
		buttonC.addActionListener(this);
		buttonC.setForeground(new Color(0, 0, 0));
		buttonC.setText("C");

		buttonD.setBounds(0, 400, 100, 100);
		buttonD.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));
		buttonD.setBackground(new Color(75, 0, 130));
		buttonD.setFocusable(false); // prevents button highlighting
		buttonD.addActionListener(this);
		buttonD.setForeground(new Color(0, 0, 0));
		buttonD.setText("D");

		answer_labelA.setBounds(125, 100, 500, 100);
		answer_labelA.setBackground(new Color(50, 50, 50));
		answer_labelA.setForeground(new Color(0, 0, 0));
		answer_labelA.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));

		answer_labelB.setBounds(125, 200, 500, 100);
		answer_labelB.setBackground(new Color(50, 50, 50));
		answer_labelB.setForeground(new Color(0, 0, 0));
		answer_labelB.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));

		answer_labelC.setBounds(125, 300, 500, 100);
		answer_labelC.setBackground(new Color(50, 50, 50));
		answer_labelC.setForeground(new Color(0, 0, 0));
		answer_labelC.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));

		answer_labelD.setBounds(125, 400, 500, 100);
		answer_labelD.setBackground(new Color(50, 50, 50));
		answer_labelD.setForeground(new Color(0, 0, 0));
		answer_labelD.setFont(new Font("Palatino Linotype Italic", Font.PLAIN, 35));

		seconds_left.setBounds(535, 510, 100, 100);
		seconds_left.setBackground(new Color(25, 25, 25));
		seconds_left.setForeground(new Color(255, 0, 0));
		seconds_left.setFont(new Font("Roboto Mono", Font.BOLD, 60));
		seconds_left.setBorder(BorderFactory.createBevelBorder(1));
		seconds_left.setOpaque(true);
		seconds_left.setHorizontalAlignment(JTextField.CENTER);
		seconds_left.setText(String.valueOf(seconds));

		time_label.setBounds(535, 475, 100, 25);
		time_label.setBackground(new Color(50, 50, 50));
		time_label.setForeground(new Color(255, 0, 0));
		time_label.setFont(new Font("Palatino Linotype Italic ", Font.BOLD, 20));
		time_label.setHorizontalAlignment(JTextField.CENTER);
		time_label.setText(":<TIMER>:");

		// numbers of question that are right
		number_right.setBounds(225, 225, 200, 100);
		number_right.setBackground(new Color(25, 25, 25));
		number_right.setForeground(new Color(25, 225, 0));
		number_right.setFont(new Font("Roboto mono", Font.BOLD, 50));
		number_right.setBorder(BorderFactory.createBevelBorder(1));
		number_right.setHorizontalAlignment(JTextField.CENTER);
		number_right.setEditable(false);

		percentage.setBounds(225, 325, 200, 100);
		percentage.setBackground(new Color(25, 25, 25));
		percentage.setForeground(new Color(25, 255, 0));
		percentage.setFont(new Font("Roboto mono", Font.BOLD, 50));
		percentage.setBorder(BorderFactory.createBevelBorder(1));
		percentage.setHorizontalAlignment(JTextField.CENTER);
		percentage.setEditable(false);

		/*
		 * frame.add(number_right); frame.add(percentage);
		 */
		frame.add(time_label);
		frame.add(seconds_left);
		frame.add(answer_labelA);
		frame.add(answer_labelB);
		frame.add(answer_labelC);
		frame.add(answer_labelD);
		frame.add(buttonA);
		frame.add(buttonB);
		frame.add(buttonC);
		frame.add(buttonD);
		frame.add(textarea);
		frame.add(textfield);
		frame.setVisible(true);

		// now we call next question. This is the next phase of the project
		nextQuestion();

	}

	public void nextQuestion() {
		if (index >= total_questions) {// Now index is currently set to 0

			results();
		} else {
			textfield.setText("Question " + (index + 1));// (0+1) it will change txtfield to Q.1, the nxt time we call
															// nxt ques this index will increase it gonna be Q.2

			textarea.setText(questions[index]); // This will display the question being asked
			answer_labelA.setText(options[index][0]);// here is the 2D array. 1st element(0) of the first array([index])
			answer_labelB.setText(options[index][1]);
			answer_labelC.setText(options[index][2]);
			answer_labelD.setText(options[index][3]);
			timer.start();// to stop timer after a question go to displayanswer method

		}

	}

	@Override
	// anything related to button clicking is going to go within this
	// actionperformed method.

	public void actionPerformed(ActionEvent e) {// It triggers when button is clicked
		// when button is clicked it some get answer answer
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		// To determine which button is being pressed and to check it is the matching
		// answer or not.
		if (e.getSource() == buttonA) {// If somebody clicks buttonA
			answer = 'A';// assign answer character 'A"
			if (answer == answers[index]) {// answer is = to array of answers at particular index.
				correct_guesses++; // increase correct_guesses by 1
			}
		}

		if (e.getSource() == buttonB) {
			answer = 'B';
			if (answer == answers[index]) {
				correct_guesses++;
			}

		}

		if (e.getSource() == buttonC) {
			answer = 'C';
			if (answer == answers[index]) {
				correct_guesses++;
			}

		}

		if (e.getSource() == buttonD) {
			answer = 'D';
			if (answer == answers[index]) {
				correct_guesses++;

			}
		}
		displayAnswer();// call display answer method
	}

	public void displayAnswer() {// It is going to execute answer if somebody clicks button
		
		timer.stop();// timer stops and displays the right answer and move on to next question
		
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		if (answers[index] != 'A')
			answer_labelA.setForeground(new Color(255, 0, 0));

		if (answers[index] != 'B')
			answer_labelB.setForeground(new Color(255, 0, 0));

		if (answers[index] != 'C')
			answer_labelC.setForeground(new Color(255, 0, 0));

		if (answers[index] != 'D')
			answer_labelD.setForeground(new Color(255, 0, 0));
		// the incorrect answers are now red to revert this back into original and move
		// on to next question after a seconds delay

		Timer pause = new Timer(2000, new ActionListener() {// when timer reaches 2000ms=2sec we will performed whatever
															// inside actionperform method
			// and flip the colors back and move on to next question.

			@Override
			public void actionPerformed(ActionEvent e) {
				answer_labelA.setForeground(new Color(0, 0, 0));
				answer_labelB.setForeground(new Color(0, 0, 0));
				answer_labelC.setForeground(new Color(0, 0, 0));
				answer_labelD.setForeground(new Color(0, 0, 0));

				answer = ' ';// answers reset
				seconds = 10;
				seconds_left.setText(String.valueOf(seconds));
				// buttons are re-enalbled
				buttonA.setEnabled(true);
				buttonB.setEnabled(true);
				buttonC.setEnabled(true);
				buttonD.setEnabled(true);
				index++; // increase index to move to next Question
				// then call nextquestion Method

				nextQuestion();

			}

		});// Close Timer initiation
			// The Action method within the timer will execute every two sec. but it should
			// activatate once
		pause.setRepeats(false);// This will only execute once whatever inside actionperformed method
		pause.start();// start the timer

	}

	public void results() {
		// disabled buttons after attempting the quiz
		buttonA.setEnabled(false);
		buttonB.setEnabled(false);
		buttonC.setEnabled(false);
		buttonD.setEnabled(false);

		result = (int) ((correct_guesses / (double) total_questions) * 100);// if two or 3 questions are right & don't
																			// want decimal values so cast (int)

		textfield.setText("********RESULTS********");
		// clear out everything
		textarea.setText("");
		answer_labelA.setText("");
		answer_labelB.setText("");
		answer_labelC.setText("");
		answer_labelD.setText("");

		number_right.setText("{" + correct_guesses + "/" + total_questions + "}");
		percentage.setText(result + "%");

		// Now add all this to frame

		frame.add(percentage);
		frame.add(number_right);

	}
}
