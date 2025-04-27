package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Lớp đối tượng Nhân viên
class Employee {
    private String name;
    private String position;

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}

// Lớp đối tượng Hóa đơn
class Invoice {
    private int invoiceNumber;
    private double amount;

    public Invoice(int invoiceNumber, double amount) {
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public double getAmount() {
        return amount;
    }
}

// Lớp đối tượng Món nước
class Beverage {
    private String name;
    private double price;

    public Beverage(String name, double price) {
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

// Lớp giao diện người dùng
public class MainFrame extends JFrame implements ActionListener {
    private JList<String> objectList;
    private DefaultListModel<String> listModel;

    private Employee employee1 = new Employee("John Doe", "Manager");
    private Invoice invoice1 = new Invoice(1001, 500.0);
    private Beverage beverage1 = new Beverage("Coffee", 3.5);

    public MainFrame() {
        setTitle("Object Viewer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        listModel = new DefaultListModel<>();
        listModel.addElement("Employee");
        listModel.addElement("Invoice");
        listModel.addElement("Beverage");

        objectList = new JList<>(listModel);
        objectList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        objectList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedIndex = objectList.getSelectedIndex();
                if (selectedIndex == 0) {
                    JOptionPane.showMessageDialog(this, "Name: " + employee1.getName() + "\nPosition: " + employee1.getPosition());
                } else if (selectedIndex == 1) {
                    JOptionPane.showMessageDialog(this, "Invoice Number: " + invoice1.getInvoiceNumber() + "\nAmount: $" + invoice1.getAmount());
                } else if (selectedIndex == 2) {
                    JOptionPane.showMessageDialog(this, "Name: " + beverage1.getName() + "\nPrice: $" + beverage1.getPrice());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(objectList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Xử lý sự kiện
    }
}
