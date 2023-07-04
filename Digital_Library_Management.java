import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

class Book {
    private String title;
    private String author;
    private String category;
    private boolean available;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> searchBooks(String query) {
        List<Book> searchResults = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(query) ||
                    book.getAuthor().contains(query) ||
                    book.getCategory().contains(query)) {
                searchResults.add(book);
            }
        }
        return searchResults;
    }
}

class LibraryGUI extends JFrame {
    private Library library;
    private JTextField searchField;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public LibraryGUI(Library library) {
        this.library = library;

        setTitle("Digital Library");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Create components
        JLabel titleLabel = new JLabel("Digital Library");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new SearchButtonListener());
        tableModel = new DefaultTableModel(new String[]{"Title", "Author", "Category", "Availability"}, 0);
        bookTable = new JTable(tableModel);

        // Add components to panels
        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        JScrollPane tableScrollPane = new JScrollPane(bookTable);

        // Add panels to the frame
        add(titlePanel, BorderLayout.NORTH);
        add(searchPanel, BorderLayout.CENTER);
        add(tableScrollPane, BorderLayout.SOUTH);

        setVisible(true);
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String query = searchField.getText();
            List<Book> searchResults = library.searchBooks(query);
            updateBookTable(searchResults);
        }
    }

    private void updateBookTable(List<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            String title = book.getTitle();
            String author = book.getAuthor();
            String category = book.getCategory();
            String availability = book.isAvailable() ? "Available" : "Not Available";
            Object[] rowData = {title, author, category, availability};
            tableModel.addRow(rowData);
        }
    }
}

public class DigitalLibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook(new Book("Java Programming", "John Smith", "Programming"));
        library.addBook(new Book("Data Structures", "Jane Doe", "Computer Science"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LibraryGUI(library);
            }
        });
    }
}
