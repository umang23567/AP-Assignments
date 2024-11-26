import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


/*
using Swing for your GUI implementation:
JFrame for the main window
JPanel for organizing components in a layout
JTable for displaying tabular data
JButton for interactive buttons
*/

public class PendingOrdersGUI {
    private JFrame frame;
    private JTable table;

    public PendingOrdersGUI(PriorityQueue<Order> orderList) {
        frame = new JFrame("Pending Orders");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);

        // Table columns, including Address
        String[] columnNames = {"Order Number", "Items Ordered", "Order Time", "Special Request", "Address", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate table with pending orders
        for (Order order : orderList) {
            // Format the order time
            String orderTime = order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String specialRequest = order.getSpecialRequest(); // Get the special request
            String address = order.getAddress(); // Get the address

            // Add the new row with order details
            tableModel.addRow(new Object[]{
                order.getOrderId(),
                order.getItemsAsString(),
                orderTime,
                specialRequest,
                address,  // Add address to the table row
                order.getStatus()
            });
        }

        // JTable setup
        table = new JTable(tableModel);
        table.setEnabled(false); // Read-only table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add components to frame
        frame.setLayout(new BorderLayout());
        frame.add(new JLabel("Pending Orders", JLabel.CENTER), BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Navigation buttons
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Refresh");
        JButton closeButton = new JButton("Close");
        buttonPanel.add(refreshButton);
        buttonPanel.add(closeButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        refreshButton.addActionListener(e -> refreshOrders(orderList)); 
        closeButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }

    private void refreshOrders(PriorityQueue<Order> orderList) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); 

        for (Order order : orderList) {
            // Format the order time
            String orderTime = order.getOrderDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String specialRequest = order.getSpecialRequest(); // Get the special request
            String address = order.getAddress(); // Get the address

            // Add the new row with order details
            tableModel.addRow(new Object[]{
                order.getOrderId(),
                order.getItemsAsString(),
                orderTime,
                specialRequest,
                address,  // Add address to the table row
                order.getStatus()
            });
        }

        // Revalidate and repaint to make sure the table updates
        table.revalidate();
        table.repaint();
    }


}
