
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class Product {
    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

public class ShoppingCart extends JFrame {

    JComboBox<String> products;
    DefaultTableModel model;
    JTable cartTable;
    JLabel totalLabel;

    double total = 0;

    Product[] productList = {
        new Product("Laptop", 55000),
        new Product("Mobile", 20000),
        new Product("Headphones", 1500),
        new Product("Keyboard", 1000),
        new Product("Mouse", 500)
    };

    ShoppingCart() {

        setTitle("Online Shopping Cart");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel heading = new JLabel(
            "ONLINE SHOPPING CART", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));

        add(heading, BorderLayout.NORTH);

        JPanel topPanel = new JPanel();

        String[] names = {
            "Laptop", "Mobile", "Headphones",
            "Keyboard", "Mouse"
        };

        products = new JComboBox<>(names);

        JButton addButton = new JButton("Add to Cart");

        topPanel.add(new JLabel("Select Product:"));
        topPanel.add(products);
        topPanel.add(addButton);

        add(topPanel, BorderLayout.BEFORE_FIRST_LINE);

        model = new DefaultTableModel(
            new String[]{"Product", "Price"}, 0);

        cartTable = new JTable(model);

        add(new JScrollPane(cartTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        totalLabel = new JLabel("Total: Rs. 0.0");

        JButton removeButton = new JButton("Remove Item");
        JButton billButton = new JButton("Generate Bill");
        JButton clearButton = new JButton("Clear Cart");

        bottomPanel.add(removeButton);
        bottomPanel.add(clearButton);
        bottomPanel.add(billButton);
        bottomPanel.add(totalLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> {
            int index = products.getSelectedIndex();

            Product p = productList[index];

            model.addRow(new Object[]{
                p.getName(), p.getPrice()
            });

            total += p.getPrice();
            updateTotal();
        });

        removeButton.addActionListener(e -> {
            int row = cartTable.getSelectedRow();

            if (row >= 0) {
                total -= (double) model.getValueAt(row, 1);
                model.removeRow(row);
                updateTotal();
            } else {
                JOptionPane.showMessageDialog(
                    this, "Select an item first!");
            }
        });

        clearButton.addActionListener(e -> {
            model.setRowCount(0);
            total = 0;
            updateTotal();
        });

        billButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                this,
                "Your Total Bill: Rs. " + total,
                "Shopping Bill",
                JOptionPane.INFORMATION_MESSAGE
            );
        });

        setVisible(true);
    }

    void updateTotal() {
        totalLabel.setText("Total: Rs. " + total);
    }

    public static void main(String[] args) {
        new ShoppingCart();
    }
}
