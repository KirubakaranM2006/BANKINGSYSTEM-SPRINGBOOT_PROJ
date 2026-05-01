package fxdemo;



import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class InventoryGUI {

    JFrame frame;
    JTextField idField, nameField, qtyField, priceField, searchField;
    DefaultTableModel model;
    JTable table;

    InventoryManager manager = InventoryManager.getInstance();

    public InventoryGUI() {

        frame = new JFrame("Inventory System");
        frame.setSize(700, 450);
        frame.setLayout(null);

        // Labels
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(20, 20, 100, 20);
        frame.add(idLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 50, 100, 20);
        frame.add(nameLabel);

        JLabel qtyLabel = new JLabel("Quantity");
        qtyLabel.setBounds(20, 80, 100, 20);
        frame.add(qtyLabel);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(20, 110, 100, 20);
        frame.add(priceLabel);

        // TextFields
        idField = new JTextField();
        idField.setBounds(100, 20, 100, 20);
        frame.add(idField);

        nameField = new JTextField();
        nameField.setBounds(100, 50, 100, 20);
        frame.add(nameField);

        qtyField = new JTextField();
        qtyField.setBounds(100, 80, 100, 20);
        frame.add(qtyField);

        priceField = new JTextField();
        priceField.setBounds(100, 110, 100, 20);
        frame.add(priceField);

        // Buttons
        JButton addBtn = new JButton("Add");
        addBtn.setBounds(20, 150, 80, 30);
        frame.add(addBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(110, 150, 90, 30);
        frame.add(updateBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(210, 150, 90, 30);
        frame.add(deleteBtn);

        // Search
        searchField = new JTextField();
        searchField.setBounds(20, 200, 150, 25);
        searchField.setToolTipText("Search by ID");
        frame.add(searchField);

        // Table
        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Qty");
        model.addColumn("Price");
        model.addColumn("Total");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(300, 20, 360, 300);
        frame.add(sp);

        // ADD
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (qty < 0) {
                    JOptionPane.showMessageDialog(frame, "Stock cannot be negative");
                    return;
                }

                double total = qty * price;

                Product p = new Product(id, name, qty, price);
                manager.addProduct(p);

                model.addRow(new Object[]{id, name, qty, price, total});

                if (qty < 5) {
                    JOptionPane.showMessageDialog(frame, "Low Stock Alert!");
                }

                clearFields();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input!");
            }
        });

        // DELETE
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) model.getValueAt(row, 0);
                manager.deleteProduct(id);
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row!");
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    int qty = Integer.parseInt(qtyField.getText());
                    double price = Double.parseDouble(priceField.getText());

                    if (qty < 0) {
                        JOptionPane.showMessageDialog(frame, "Stock cannot be negative");
                        return;
                    }

                    double total = qty * price;

                    Product p = manager.findProduct(id);
                    if (p != null) {
                        p.name = nameField.getText();
                        p.quantity = qty;
                        p.price = price;
                    }

                    model.setValueAt(nameField.getText(), row, 1);
                    model.setValueAt(qty, row, 2);
                    model.setValueAt(price, row, 3);
                    model.setValueAt(total, row, 4);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row!");
            }
        });

        // LOW STOCK COLOR
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int col) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, col);

                int qty = Integer.parseInt(table.getValueAt(row, 2).toString());

                if (qty < 5) {
                    c.setBackground(Color.PINK);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });

        // SEARCH
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String text = searchField.getText();

                for (int i = 0; i < table.getRowCount(); i++) {
                    if (table.getValueAt(i, 0).toString().contains(text)) {
                        table.setRowSelectionInterval(i, i);
                        break;
                    }
                }
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void clearFields() {
        idField.setText("");
        nameField.setText("");
        qtyField.setText("");
        priceField.setText("");
    }

    public static void main(String[] args) {
        new InventoryGUI();
    }
}