import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineExam {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton updateProfileButton;
    private JButton startExamButton;
    private JButton logoutButton;

    private boolean loggedIn;
    private String username;

    public OnlineExam() {
        frame = new JFrame("Online Examination");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 1));

        loginButton = new JButton("Login");
        updateProfileButton = new JButton("Update Profile and Password");
        startExamButton = new JButton("Start Exam");
        logoutButton = new JButton("Logout");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!loggedIn) {
                    handleLogin();
                } else {
                    JOptionPane.showMessageDialog(frame, "Already logged in as " + username);
                }
            }
        });

        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedIn) {
                    handleUpdateProfile();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please login first.");
                }
            }
        });

        startExamButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedIn) {
                    startExam();
                } else {
                    JOptionPane.showMessageDialog(frame, "Please login first.");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loggedIn) {
                    logout();
                } else {
                    JOptionPane.showMessageDialog(frame, "You are not logged in.");
                }
            }
        });

        mainPanel.add(loginButton);
        mainPanel.add(updateProfileButton);
        mainPanel.add(startExamButton);
        mainPanel.add(logoutButton);

        frame.getContentPane().add(mainPanel);
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    private void handleLogin() {
        String enteredUsername = JOptionPane.showInputDialog(frame, "Enter username:");
        String enteredPassword = JOptionPane.showInputDialog(frame, "Enter password:");

        // Implement your login logic here
        // You can replace the code below with your own authentication mechanism

        if (enteredUsername.equals("admin") && enteredPassword.equals("password")) {
            loggedIn = true;
            username = enteredUsername;
            JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + username + "!");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password. Login failed.");
        }
    }

    private void handleUpdateProfile() {
        // Implement your logic for updating profile and password here
        JOptionPane.showMessageDialog(frame, "Profile and password update functionality not implemented yet.");
    }

    private void startExam() {
        // Implement your logic for starting the exam, displaying questions, and handling answers

        // Mock Demo Exam
        String[] questions = {
                "Question 1: What is the capital of France?",
                "Question 2: What is the largest planet in our solar system?",
                "Question 3: Who painted the Mona Lisa?",
                "Question 4: Which country won the FIFA World Cup in 2018?",
                "Question 5: What is the chemical symbol for Gold?"
        };

        String[][] choices = {
                {"A. London", "B. Paris", "C. Rome", "D. Madrid"},
                {"A. Jupiter", "B. Saturn", "C. Mars", "D. Earth"},
                {"A. Vincent van Gogh", "B. Pablo Picasso", "C. Leonardo da Vinci", "D. Michelangelo"},
                {"A. Germany", "B. Brazil", "C. France", "D. Argentina"},
                {"A. Au", "B. Ag", "C. Cu", "D. Pt"}
        };

        int[] correctAnswers = {1, 0, 2, 2, 0};

        int totalQuestions = questions.length;
        int[] score = {0};
        int[] currentQuestion = {0};

        // Create exam frame
        JFrame examFrame = new JFrame("Exam");
        examFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        examFrame.setSize(400, 300);

        JPanel examPanel = new JPanel();
        examPanel.setLayout(new BorderLayout());

        JLabel questionLabel = new JLabel();
        JButton submitButton = new JButton("Submit");

        JRadioButton[] choiceButtons = new JRadioButton[4];
        ButtonGroup choiceGroup = new ButtonGroup();

        JPanel choicesPanel = new JPanel();
        choicesPanel.setLayout(new GridLayout(4, 1));

        for (int i = 0; i < 4; i++) {
            choiceButtons[i] = new JRadioButton();
            choiceGroup.add(choiceButtons[i]);
            choicesPanel.add(choiceButtons[i]);
        }

        ActionListener submitButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if an answer is selected
                if (choiceGroup.getSelection() == null) {
                    JOptionPane.showMessageDialog(examFrame, "Please select an answer.");
                    return;
                }

                // Check if the selected answer is correct
                int selectedAnswer = -1;
                for (int i = 0; i < 4; i++) {
                    if (choiceButtons[i].isSelected()) {
                        selectedAnswer = i;
                        break;
                    }
                }

                if (selectedAnswer == correctAnswers[currentQuestion[0]]) {
                    score[0]++;
                }

                // Clear selection for next question
                choiceGroup.clearSelection();

                // Show next question or end the exam
                currentQuestion[0]++;
                if (currentQuestion[0] < totalQuestions) {
                    questionLabel.setText(questions[currentQuestion[0]]);
                    for (int i = 0; i < 4; i++) {
                        choiceButtons[i].setText(choices[currentQuestion[0]][i]);
                    }
                } else {
                    // End of exam
                    JOptionPane.showMessageDialog(examFrame, "Exam completed!\nYour score: " + score[0] + "/" + totalQuestions);
                    examFrame.dispose();
                }
            }
        };

        submitButton.addActionListener(submitButtonListener);

        examPanel.add(questionLabel, BorderLayout.NORTH);
        examPanel.add(choicesPanel, BorderLayout.CENTER);
        examPanel.add(submitButton, BorderLayout.SOUTH);

        questionLabel.setText(questions[currentQuestion[0]]);
        for (int i = 0; i < 4; i++) {
            choiceButtons[i].setText(choices[currentQuestion[0]][i]);
        }

        examFrame.getContentPane().add(examPanel);
        examFrame.setVisible(true);
    }

    private void logout() {
        loggedIn = false;
        username = "";
        JOptionPane.showMessageDialog(frame, "Logout successful!");
    }

    public static void main(String[] args) {
        OnlineExam onlineExam = new OnlineExam();
        onlineExam.showGUI();
    }
}
