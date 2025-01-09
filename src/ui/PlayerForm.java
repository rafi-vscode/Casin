package ui;

import dao.PlayerDAO;

import javax.swing.*;
import java.awt.*;

public class PlayerForm extends JFrame {
    private JTextField nameField, emailField, balanceField;
    private JButton saveButton, viewAllButton;

    public PlayerForm() {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize Look and Feel");
        }

        setTitle("Add New Player");
        setSize(450, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama dengan latar belakang gambar
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("assets/chip.jpg");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        add(mainPanel);

        // Panel kotak untuk form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(new Color(40, 40, 40, 220)); // Transparansi pada kotak
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menambahkan teks "Registration" di atas kotak
        JLabel titleLabel = new JLabel("Daftar dulu yaa");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(titleLabel, gbc);

        // Menambahkan logo di bawah teks "Registration"
        JLabel logoLabel = new JLabel(new ImageIcon("assets/logo.png"));
        gbc.gridy++;
        formPanel.add(logoLabel, gbc);

        // Label dan field untuk Name
        gbc.gridy++;
        gbc.gridwidth = 1;
        formPanel.add(createLabeledField("Name:", nameField = new JTextField()), gbc);

        // Label dan field untuk Email
        gbc.gridy++;
        formPanel.add(createLabeledField("Email:", emailField = new JTextField()), gbc);

        // Label dan field untuk Balance
        gbc.gridy++;
        formPanel.add(createLabeledField("Balance:", balanceField = new JTextField()), gbc);

        // Tombol Save
        saveButton = new JButton("Save");
        styleButton(saveButton, new Color(50, 150, 50));
        saveButton.addActionListener(e -> savePlayer());
        gbc.gridy++;
        formPanel.add(saveButton, gbc);

        // Tombol View All Players
        viewAllButton = new JButton("View All Players");
        styleButton(viewAllButton, new Color(34, 167, 240));
        viewAllButton.addActionListener(e -> new ViewPlayersForm());
        gbc.gridy++;
        formPanel.add(viewAllButton, gbc);

        mainPanel.add(formPanel);
        setVisible(true);
    }

    // Membuat panel dengan label dan text field
    private JPanel createLabeledField(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText);
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(80, 30));

        textField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(30, 30, 30));
        textField.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
        textField.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    // Styling untuk tombol
    private void styleButton(JButton button, Color color) {
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    // Fungsi untuk menyimpan pemain
    private void savePlayer() {
        String name = nameField.getText();
        String email = emailField.getText();
        try {
            float balance = Float.parseFloat(balanceField.getText());
            PlayerDAO playerDAO = new PlayerDAO();
            playerDAO.addPlayer(name, email, balance);
            JOptionPane.showMessageDialog(this, "Player Added!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid balance value.");
        }
    }

    public static void main(String[] args) {
        new PlayerForm();
    }
}
