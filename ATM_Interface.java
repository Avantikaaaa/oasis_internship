import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ATM {
    private String userId;
    private String userPin;
    private double balance;

    private JFrame frame;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton transactionsButton;
    private JButton withdrawButton;
    private JButton depositButton;
    private JButton transferButton;
    private JButton quitButton;

    public ATM(String userId, String userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;

        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 1));

        loginButton = new JButton("Login");
        transactionsButton = new JButton("Transactions History");
        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        transferButton = new JButton("Transfer");
        quitButton = new JButton("Quit");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        transactionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransactionHistory();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleWithdraw();
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDeposit();
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleTransfer();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quit();
            }
        });

        mainPanel.add(loginButton);
        mainPanel.add(transactionsButton);
        mainPanel.add(withdrawButton);
        mainPanel.add(depositButton);
        mainPanel.add(transferButton);
        mainPanel.add(quitButton);

        frame.getContentPane().add(mainPanel);
    }

    public void showGUI() {
        frame.setVisible(true);
    }

    private void handleLogin() {
        String enteredUserId = JOptionPane.showInputDialog(frame, "Enter User ID:");
        String enteredUserPin = JOptionPane.showInputDialog(frame, "Enter User PIN:");

        if (isValidUser(enteredUserId, enteredUserPin)) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid User ID or PIN. Login failed.");
        }
    }

    private boolean isValidUser(String enteredUserId, String enteredUserPin) {
        return userId.equals(enteredUserId) && userPin.equals(enteredUserPin);
    }

    private void showTransactionHistory() {
        JOptionPane.showMessageDialog(frame, "Transaction History:\n\n(Implement your logic to display transaction history here)");
    }

    private void handleWithdraw() {
        String withdrawalAmountStr = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
        double withdrawalAmount = Double.parseDouble(withdrawalAmountStr);

        if (withdrawalAmount <= balance) {
            balance -= withdrawalAmount;
            JOptionPane.showMessageDialog(frame, "Withdrawal successful. New balance: " + balance);
        } else {
            JOptionPane.showMessageDialog(frame, "Insufficient funds. Withdrawal failed.");
        }
    }

    private void handleDeposit() {
        String depositAmountStr = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
        double depositAmount = Double.parseDouble(depositAmountStr);

        balance += depositAmount;
        JOptionPane.showMessageDialog(frame, "Deposit successful. New balance: " + balance);
    }

    private void handleTransfer() {
        String recipientId = JOptionPane.showInputDialog(frame, "Enter recipient's user ID:");
        String transferAmountStr = JOptionPane.showInputDialog(frame, "Enter transfer amount:");
        double transferAmount = Double.parseDouble(transferAmountStr);

        // Implement your logic to perform the transfer here

        JOptionPane.showMessageDialog(frame, "Transfer successful. New balance: " + balance);
    }

    private void quit() {
        JOptionPane.showMessageDialog(frame, "Thank you for using the ATM. Goodbye!");
        frame.dispose();
    }

    public static void main(String[] args) {
        ATM atm = new ATM("123456", "7890", 1000.0);
        atm.showGUI();
    }
}
