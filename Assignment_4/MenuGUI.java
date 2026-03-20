import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/*
using Swing for your GUI implementation:
JFrame for the main window
JPanel for organizing components in a layout
JTable for displaying tabular data
JComboBox for category selection
JButton for interactive buttons
 */

public class MenuGUI {
    private JFrame frame;
    private JTable table;
    private JButton sortByPriceButton;
    private JComboBox<String> categoryComboBox;
    private JTextField searchField;
    private boolean isAscending = true; // Flag to toggle between ascending and descending

    public MenuGUI(Map<String, FoodItem> menu) {
        frame = new JFrame("Canteen Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);

        // Table columns - added "Category" column
        String[] columnNames = {"Name", "Price", "Availability", "Category"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate table with menu items
        for (FoodItem item : menu.values()) {
            String availability = item.isAvailable() ? "Available" : "Not available";
            tableModel.addRow(new Object[]{item.getName(), item.getPrice(), availability, item.getCategory()});
        }

        // JTable setup
        table = new JTable(tableModel);
        table.setEnabled(true); // Read-only table
        JScrollPane scrollPane = new JScrollPane(table);

        // Panel setup for Buttons and Filters
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Search field setup
        searchField = new JTextField(20);
        panel.add(new JLabel("Search:"));
        panel.add(searchField);

        // Category selection dropdown
        Set<String> categories = new HashSet<>();
        for (FoodItem item : menu.values()) {
            categories.add(item.getCategory());
        }
        categories.add("All"); // Add "All" category for no filtering
        categoryComboBox = new JComboBox<>(categories.toArray(new String[0]));
        panel.add(new JLabel("Category:"));
        panel.add(categoryComboBox);

        // Sorting button setup
        sortByPriceButton = new JButton("Sort by Price");
        panel.add(sortByPriceButton);

        // Add action listeners for buttons
        sortByPriceButton.addActionListener(e -> sortByPrice(menu));

        // Category selection listener
        categoryComboBox.addActionListener(e -> filterTable(menu)); // Filter by category

        // Search functionality listener
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent e) {
                filterTable(menu); // Filter the table when user types in the search field
            }
        });

        // Add components to frame
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Canteen Menu", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void filterTable(Map<String, FoodItem> menu) {
        String searchText = searchField.getText().toLowerCase();
        String selectedCategory = (String) categoryComboBox.getSelectedItem();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table

        // Add rows based on the search query and selected category
        for (FoodItem item : menu.values()) {
            // Check if the item's name contains the search text and if it matches the selected category
            boolean matchesSearch = item.getName().toLowerCase().contains(searchText);
            boolean matchesCategory = selectedCategory.equals("All") || item.getCategory().equals(selectedCategory);

            if (matchesSearch && matchesCategory) {
                String availability = item.isAvailable() ? "Available" : "Not available";
                model.addRow(new Object[]{item.getName(), item.getPrice(), availability, item.getCategory()});
            }
        }
    }

    private void sortByPrice(Map<String, FoodItem> menu) {
        // Get the current sort order (ascending or descending)
        Object[] menuArray = menu.values().toArray();

        if (isAscending) {
            // Sort in ascending order
            Arrays.sort(menuArray, (a, b) -> Double.compare(((FoodItem) a).getPrice(), ((FoodItem) b).getPrice()));
        } else {
            // Sort in descending order
            Arrays.sort(menuArray, (a, b) -> Double.compare(((FoodItem) b).getPrice(), ((FoodItem) a).getPrice()));
        }

        // Update the table with sorted data
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear current rows

        // Add sorted rows to the table
        for (Object itemObj : menuArray) {
            FoodItem item = (FoodItem) itemObj;
            String availability = item.isAvailable() ? "Available" : "Not available";
            model.addRow(new Object[]{item.getName(), item.getPrice(), availability, item.getCategory()});
        }

        // Toggle the sort order for the next click
        isAscending = !isAscending;
    }

}
