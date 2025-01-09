package ui;

import javax.swing.*;
import java.awt.*;


public class MainForm extends JFrame {
    public MainForm() {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize Look and Feel");
        }

        setTitle("Casino Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel utama
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(20, 20, 20)); // Warna gelap untuk nuansa kasino
        add(panel);

        // Judul
        JLabel titleLabel = new JLabel("🎰 Welcome to My Cassino 🎲", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(Color.ORANGE);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Gambar utama
        JLabel casinoImage = new JLabel(new ImageIcon("assets/casino_banner.jpeg")); // Path gambar
        panel.add(casinoImage, BorderLayout.CENTER);

        // Tombol navigasi
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(panel.getBackground());
        JButton managePlayersButton = new JButton("Lanjut Main?");
        managePlayersButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        managePlayersButton.setBackground(new Color(255, 50, 50));
        managePlayersButton.setForeground(Color.WHITE);
        managePlayersButton.addActionListener(e -> new PlayerForm());
        buttonPanel.add(managePlayersButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new MainForm();
    }
}
