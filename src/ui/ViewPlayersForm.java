package ui;

import dao.PlayerDAO;
import model.Player;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewPlayersForm extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private PlayerDAO playerDAO;
    private JButton refreshButton, deleteButton, updateButton;

    public ViewPlayersForm() {
        setTitle("All Players");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        playerDAO = new PlayerDAO();

        // Tabel untuk menampilkan data
        table = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Email", "Balance"}, 0);
        table.setModel(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Tombol Refresh
        refreshButton = new JButton("Refresh");
        refreshButton.setBackground(new Color(34, 167, 240));  // Tombol biru muda
        refreshButton.setForeground(Color.WHITE);
        buttonPanel.add(refreshButton);

        // Tombol Update
        updateButton = new JButton("Update Selected");
        updateButton.setBackground(new Color(0, 123, 255));  // Tombol biru
        updateButton.setForeground(Color.WHITE);
        buttonPanel.add(updateButton);

        // Tombol Delete
        deleteButton = new JButton("Delete Selected");
        deleteButton.setBackground(new Color(255, 69, 0));  // Tombol merah
        deleteButton.setForeground(Color.WHITE);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event untuk tombol refresh
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        // Event untuk tombol delete
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedPlayer();
                loadData(); // Segarkan tabel setelah menghapus pemain
            }
        });

        // Event untuk tombol update
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedPlayer();
                loadData(); // Segarkan tabel setelah memperbarui pemain
            }
        });

        // Muat data awal
        loadData();

        setVisible(true);
    }

    // Metode untuk memuat data dari database ke tabel
    private void loadData() {
        tableModel.setRowCount(0); // Hapus data lama di tabel
        List<Player> players = playerDAO.getAllPlayers();
        for (Player player : players) {
            tableModel.addRow(new Object[]{player.getId(), player.getName(), player.getEmail(), player.getBalance()});
        }
    }

    // Metode untuk menghapus pemain yang dipilih
    private void deleteSelectedPlayer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a player to delete.");
            return;
        }

        int playerId = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this player?");
        if (confirm == JOptionPane.YES_OPTION) {
            playerDAO.deletePlayer(playerId);
            JOptionPane.showMessageDialog(this, "Player deleted successfully!");
        }
    }

    // Metode untuk memperbarui data pemain yang dipilih
    private void updateSelectedPlayer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a player to update.");
            return;
        }

        int playerId = (int) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String email = (String) tableModel.getValueAt(selectedRow, 2);
        float balance;
        try {
            balance = Float.parseFloat(tableModel.getValueAt(selectedRow, 3).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid balance format.");
            return;
        }

        // Perbarui data pemain di database
        playerDAO.updatePlayer(playerId, name, email, balance);
        JOptionPane.showMessageDialog(this, "Player updated successfully!");
    }
}
