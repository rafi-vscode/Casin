package ui;

import dao.PlayerDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdatePlayerForm extends JFrame {
    private JTextField idField, nameField, emailField, balanceField;
    private JButton updateButton, deleteButton, refreshButton, viewAllButton;

    public UpdatePlayerForm() {
        // Mengatur judul dan ukuran frame
        setTitle("Update Player");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel utama dengan layout BorderLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Mengatur tema gelap dengan warna background gelap
        panel.setBackground(new Color(33, 33, 33));  // Dark background

        // Mengatur gambar latar belakang
        ImageIcon backgroundImage = new ImageIcon("assets/chip.jpg"); // Gambar yang ada di folder assets
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new FlowLayout());
        panel.add(backgroundLabel, BorderLayout.CENTER);

        // Panel untuk form input dan tombol
        JPanel formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setOpaque(false);  // Agar panel form tidak menutupi gambar
        backgroundLabel.add(formPanel);

        // Label dan field untuk input ID
        JLabel idLabel = new JLabel("Player ID:");
        idLabel.setForeground(Color.WHITE);  // Warna teks putih
        idLabel.setBounds(10, 20, 80, 25);
        formPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 20, 165, 25);
        formPanel.add(idField);

        // Label dan field untuk input nama
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(10, 60, 80, 25);
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(100, 60, 165, 25);
        formPanel.add(nameField);

        // Label dan field untuk input email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(10, 100, 80, 25);
        formPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 100, 165, 25);
        formPanel.add(emailField);

        // Label dan field untuk input balance
        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setBounds(10, 140, 80, 25);
        formPanel.add(balanceLabel);

        balanceField = new JTextField();
        balanceField.setBounds(100, 140, 165, 25);
        formPanel.add(balanceField);

        // Tombol update untuk memperbarui data
        updateButton = new JButton("Update");
        updateButton.setBounds(10, 180, 80, 25);
        updateButton.setBackground(new Color(0, 123, 255));  // Tombol dengan warna biru
        updateButton.setForeground(Color.WHITE);  // Warna teks putih di tombol
        formPanel.add(updateButton);

        // Tombol delete untuk menghapus data
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(100, 180, 80, 25);
        deleteButton.setBackground(new Color(255, 69, 0));  // Tombol merah untuk delete
        deleteButton.setForeground(Color.WHITE);  // Warna teks putih di tombol
        formPanel.add(deleteButton);

        // Tombol refresh untuk memperbarui tabel
        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(190, 180, 80, 25);
        refreshButton.setBackground(new Color(34, 167, 240));  // Tombol biru muda
        refreshButton.setForeground(Color.WHITE);  // Warna teks putih di tombol
        formPanel.add(refreshButton);

        // Tombol View All Players
        viewAllButton = new JButton("View All Players");
        viewAllButton.setBounds(280, 180, 120, 25);
        viewAllButton.setBackground(new Color(34, 167, 240));
        viewAllButton.setForeground(Color.WHITE);
        formPanel.add(viewAllButton);

        // ActionListener untuk tombol View All Players
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewPlayersForm();  // Membuka form ViewPlayersForm
            }
        });

        // ActionListener untuk tombol update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                float balance = Float.parseFloat(balanceField.getText());

                // Mengupdate data player menggunakan PlayerDAO
                PlayerDAO playerDAO = new PlayerDAO();
                playerDAO.updatePlayer(id, name, email, balance);

                JOptionPane.showMessageDialog(UpdatePlayerForm.this, "Player Updated!");
            }
        });

        // ActionListener untuk tombol delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic untuk menghapus player
                int id = Integer.parseInt(idField.getText());
                PlayerDAO playerDAO = new PlayerDAO();
                playerDAO.deletePlayer(id);
                JOptionPane.showMessageDialog(UpdatePlayerForm.this, "Player Deleted!");
            }
        });

        setVisible(true);
    }
}
