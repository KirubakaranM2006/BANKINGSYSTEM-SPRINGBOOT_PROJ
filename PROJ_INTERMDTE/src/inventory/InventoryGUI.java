package inventory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class InventoryGUI {

    JFrame frame;
    JTextField idField, nameField, qtyField, priceField;
    DefaultTableModel model;
    JTable table;

    InventoryManager manager = InventoryManager.getInstance();

    public InventoryGUI() {

        frame = new JFrame("Inventory System");
        frame.setSize(600, 400);
        frame.setLayout(null);

        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(20, 20, 100, 20);
        frame.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 100, 20);
        frame.add(idField);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(20, 50, 100, 20);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 50, 100, 20);
        frame.add(nameField);

        JLabel qtyLabel = new JLabel("Quantity");
        qtyLabel.setBounds(20, 80, 100, 20);
        frame.add(qtyLabel);

        qtyField = new JTextField();
        qtyField.setBounds(100, 80, 100, 20);
        frame.add(qtyField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(20, 110, 100, 20);
        frame.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(100, 110, 100, 20);
        frame.add(priceField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(20, 150, 80, 30);
        frame.add(addBtn);

        JButton deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(110, 150, 90, 30);
        frame.add(deleteBtn);

        JButton updateBtn = new JButton("Update");
        updateBtn.setBounds(210, 150, 90, 30);
        frame.add(updateBtn);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Qty");
        model.addColumn("Price");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(250, 20, 300, 200);
        frame.add(sp);

        // ADD
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (qty < 0) {
                    JOptionPane.showMessageDialog(frame, "Quantity cannot be negative");
                    return;
                }

                Product p = new Product(id, name, qty, price);
                manager.addProduct(p);

                model.addRow(new Object[]{id, name, qty, price});
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
                JOptionPane.showMessageDialog(frame, "Select a row first!");
            }
        });

        // UPDATE
        updateBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Product p = manager.findProduct(id);

                    if (p != null) {
                        p.setName(nameField.getText());
                        p.setQuantity(Integer.parseInt(qtyField.getText()));
                        p.setPrice(Double.parseDouble(priceField.getText()));

                        model.setValueAt(p.getName(), row, 1);
                        model.setValueAt(p.getQuantity(), row, 2);
                        model.setValueAt(p.getPrice(), row, 3);
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Select a row first!");
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