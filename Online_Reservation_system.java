import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

class ReservationSystem {
    private Map<String, String> reservations;

    public ReservationSystem() {
        reservations = new HashMap<>();
    }

    public void reserveTicket(String pnr, String details) {
        reservations.put(pnr, details);
        JOptionPane.showMessageDialog(null, "Ticket reserved successfully! PNR: " + pnr);
    }

    public void cancelTicket(String pnr) {
        if (reservations.containsKey(pnr)) {
            reservations.remove(pnr);
            JOptionPane.showMessageDialog(null, "Ticket with PNR " + pnr + " cancelled successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid PNR. Ticket not found!");
        }
    }

    public void displayAllTickets() {
        StringBuilder ticketInfo = new StringBuilder();
        ticketInfo.append("All Tickets:\n");
        for (Map.Entry<String, String> entry : reservations.entrySet()) {
            ticketInfo.append("PNR: ").append(entry.getKey()).append(", Details: ").append(entry.getValue()).append("\n");
        }
        JOptionPane.showMessageDialog(null, ticketInfo.toString());
    }
}

class OnlineReservationSystemGUI extends JFrame implements ActionListener {
    private ReservationSystem reservationSystem;

    private JTextField pnrField;
    private JTextArea detailsArea;

    public OnlineReservationSystemGUI() {
        reservationSystem = new ReservationSystem();

        setTitle("Online Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        createLoginForm();
    }

    private void createLoginForm() {
        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel usernameLabel = new JLabel("Username:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(usernameLabel, constraints);

        JTextField usernameField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        loginPanel.add(usernameField, constraints);

        JLabel passwordLabel = new JLabel("Password:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        loginPanel.add(passwordField, constraints);

        JButton loginButton = new JButton("Login");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        loginPanel.add(loginButton, constraints);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (isValidCredentials(username, password)) {
                createMainMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
            }
        });

        setContentPane(loginPanel);
        setVisible(true);
    }

    private boolean isValidCredentials(String username, String password) {
        // Replace with your default login credentials
        return username.equals("admin") && password.equals("password");
    }

    private void createMainMenu() {
        JPanel mainMenuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JButton reserveButton = new JButton("Reserve Ticket");
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainMenuPanel.add(reserveButton, constraints);

        JButton cancelButton = new JButton("Cancel Ticket");
        constraints.gridx = 0;
        constraints.gridy = 1;
        mainMenuPanel.add(cancelButton, constraints);

        JButton displayButton = new JButton("Display All Tickets");
        constraints.gridx = 0;
        constraints.gridy = 2;
        mainMenuPanel.add(displayButton, constraints);

        JButton logoutButton = new JButton("Logout");
        constraints.gridx = 0;
        constraints.gridy = 3;
        mainMenuPanel.add(logoutButton, constraints);

        reserveButton.addActionListener(e -> createReservationForm());
        cancelButton.addActionListener(e -> createCancellationForm());
        displayButton.addActionListener(e -> reservationSystem.displayAllTickets());
        logoutButton.addActionListener(e -> createLoginForm());

        setContentPane(mainMenuPanel);
        revalidate();
    }

    private void createReservationForm() {
        JPanel reservationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel pnrLabel = new JLabel("PNR number:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        reservationPanel.add(pnrLabel, constraints);

        pnrField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        reservationPanel.add(pnrField, constraints);

        JLabel detailsLabel = new JLabel("Passenger details:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        reservationPanel.add(detailsLabel, constraints);

        detailsArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        constraints.gridx = 1;
        constraints.gridy = 1;
        reservationPanel.add(scrollPane, constraints);

        JButton insertButton = new JButton("Insert");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        reservationPanel.add(insertButton, constraints);

        JButton backButton = new JButton("Back to Main Menu");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        reservationPanel.add(backButton, constraints);

        insertButton.addActionListener(this);
        backButton.addActionListener(e -> createMainMenu());

        setContentPane(reservationPanel);
        revalidate();
    }

    private void createCancellationForm() {
        JPanel cancellationPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel pnrLabel = new JLabel("PNR number:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        cancellationPanel.add(pnrLabel, constraints);

        JTextField pnrField = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        cancellationPanel.add(pnrField, constraints);

        JButton cancelButton = new JButton("Cancel");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        cancellationPanel.add(cancelButton, constraints);

        JButton backButton = new JButton("Back to Main Menu");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        cancellationPanel.add(backButton, constraints);

        cancelButton.addActionListener(e -> {
            String pnr = pnrField.getText();
            reservationSystem.cancelTicket(pnr);
        });

        backButton.addActionListener(e -> createMainMenu());

        setContentPane(cancellationPanel);
        revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pnr = pnrField.getText();
        String details = detailsArea.getText();
        reservationSystem.reserveTicket(pnr, details);
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(OnlineReservationSystemGUI::new);
    }
}