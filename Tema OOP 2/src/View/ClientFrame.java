package View;
import javax.swing.JLabel;


import Presenter.ClientPresenter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.UUID;


public class ClientFrame extends JFrame implements IClientFrame {

    public final JTextField txtFiltru = new JTextField();
    String[] options = {"denumire", "marca"};
    JComboBox<String> comboCriteriu = new JComboBox<>(options);

    public final JTable table = new JTable();
    public final JScrollPane scrollPane = new JScrollPane();
    private final JTextField txtPretMinim = new JTextField();
    private final JTextField txtPretMaxim = new JTextField();

    private final ClientPresenter m;

    public ClientFrame() {
        setTitle("Client");
        JPanel panelManager = new JPanel();
        setContentPane(panelManager);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        m = new ClientPresenter(this);

        JButton btnCauta = new JButton("Cauta");
        btnCauta.addActionListener(e -> m.readaugareTabel());

        JButton btnGaseste = new JButton("Gaseste");
        btnGaseste.addActionListener(e -> m.readaugareTabelPret());

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> m.adaugareTabel());
        JButton btnPromotie = new JButton("Afiseaza produse la promotie");
        btnPromotie.addActionListener(e -> m.afisareProdusePromotie());



        JButton btnCumpara = new JButton("Cumpara");
        btnCumpara.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                UUID idProdus = (UUID) table.getValueAt(selectedRow, 0);
                boolean success = m.cumparaProdus(idProdus);
                if (success) {
                    m.adaugareTabel();
                } else {
                    JOptionPane.showMessageDialog(ClientFrame.this, "Produsul nu este disponibil în stoc!", "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(ClientFrame.this, "Nu a fost selectat niciun produs!", "Eroare", JOptionPane.ERROR_MESSAGE);
            }
        });


        panelManager.setLayout(new BorderLayout());

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(3, 1));

        JPanel filterPanelRow1 = new JPanel();
        JPanel filterPanelRow2 = new JPanel();
        JPanel filterPanelRow0 = new JPanel();

        String htmlText = "<html><body><p style='font-size:22pt; font-weight:bold;'>Bine ai venit!</p></body></html>";
        JLabel lblBineAiVenit = new JLabel(htmlText);
        filterPanelRow0.add(lblBineAiVenit);

        JLabel lblPretMinim = new JLabel("PretMinim:");
        filterPanelRow1.add(lblPretMinim);
        filterPanelRow1.add(txtPretMinim);
        txtPretMinim.setPreferredSize(new Dimension(80, 25));
        JLabel lblPretMaxim = new JLabel("PretMaxim:");
        filterPanelRow1.add(lblPretMaxim);
        filterPanelRow1.add(txtPretMaxim);
        txtPretMaxim.setPreferredSize(new Dimension(80, 25));
        filterPanelRow1.add(btnGaseste);

        filterPanelRow2.add(new JLabel("Criteriu:"));
        filterPanelRow2.add(comboCriteriu);
        filterPanelRow2.add(new JLabel("Filtru:"));
        filterPanelRow2.add(txtFiltru);
        txtFiltru.setPreferredSize(new Dimension(120, 25));
        filterPanelRow2.add(btnCauta);

        filterPanelRow1.setBackground(new Color(232, 201, 188));
        filterPanelRow2.setBackground(new Color(232, 201, 188));
        filterPanelRow0.setBackground(new Color(232, 201, 188));
        filterPanel.setBackground(new Color(232, 201, 188));

        filterPanel.add(filterPanelRow0);
        filterPanel.add(filterPanelRow1);
        filterPanel.add(filterPanelRow2);
        filterPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelManager.add(filterPanel, BorderLayout.NORTH);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelManager.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        JPanel row1Panel = new JPanel();
        row1Panel.setBackground(new Color(232, 201, 188));
        row1Panel.add(btnPromotie);
        buttonPanel.add(row1Panel);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        JPanel row2Panel = new JPanel();
        row2Panel.setBackground(new Color(232, 201, 188));
        row2Panel.add(btnRefresh);
        row2Panel.add(btnCumpara);
        buttonPanel.add(row2Panel);
        buttonPanel.setBackground(new Color(232, 201, 188));
        panelManager.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public String getCriteriu(){String data = null;
        Object selectedItem = comboCriteriu.getSelectedItem();
        if (selectedItem != null) {
            data = selectedItem.toString();
        }
        return data;}
    @Override
    public String getFiltru(){return txtFiltru.getText();}

    @Override
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        scrollPane.setViewportView(table);
    }
    @Override
    public float getPretMinim() {
        return Integer.parseInt(txtPretMinim.getText());
    }
    @Override
    public float getPretMaxim() {
        return Integer.parseInt(txtPretMaxim.getText());
    }

}
