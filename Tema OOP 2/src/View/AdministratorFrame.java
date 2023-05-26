package View;

import Model.Produs;
import Presenter.AdministratorPresenter;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.UUID;


public class AdministratorFrame extends JFrame implements IAdministratorFrame {

    public final JTable table = new JTable();
    public final JScrollPane scrollPane = new JScrollPane();
    private final AdministratorPresenter a;
    private final JTextField txtDenumire = new JTextField();

    private final JTextField txtMarca = new JTextField();

    private final JTextField txtStoc = new JTextField();

    private final JTextField txtReducere = new JTextField();

    private final JTextField txtPret = new JTextField();


    public AdministratorFrame() {
        setTitle("Administrator");
        JPanel panelAdministrator = new JPanel();
        setContentPane(panelAdministrator);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        a = new AdministratorPresenter(this);


        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(e -> a.adaugareTabel());

        JButton btnAdauga = new JButton("Adauga");
        btnAdauga.addActionListener(e -> {
            try {
            Produs produs = new Produs();
            produs.setDenumire(txtDenumire.getText());
            produs.setMarca(txtMarca.getText());
            produs.setStoc(Integer.parseInt(txtStoc.getText()));
            produs.setReducere(Integer.parseInt(txtReducere.getText()));
            produs.setPret(Float.parseFloat(txtPret.getText()));
            a.adaugareProdus(produs);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valoare introdusă lipsă sau incorectă !", "Error", JOptionPane.ERROR_MESSAGE);
        }
        });

        JButton btnSterge = new JButton("Sterge");
        btnSterge.addActionListener(e -> {
            try {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                UUID idProdus = (UUID) table.getValueAt(selectedRow, 0);
                a.stergereProdus(idProdus);
            } else {
                JOptionPane.showMessageDialog(this, "Selectati un produs pentru a-l sterge!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valoarea ID-ului lipsă sau incorectă!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnActualizeaza = new JButton("Actualizează");
        btnActualizeaza.addActionListener(e -> {
            try{
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int idProdus = (int) table.getValueAt(selectedRow, 0);
                Produs produs = new Produs();
                produs.setDenumire(txtDenumire.getText());
                produs.setMarca(txtMarca.getText());
                produs.setStoc(Integer.parseInt(txtStoc.getText()));
                produs.setReducere(Integer.parseInt(txtReducere.getText()));
                produs.setPret(Float.parseFloat(txtPret.getText()));
                a.actualizareProdus(produs);
            } else {
                JOptionPane.showMessageDialog(this, "Selectati un produs pentru a-l actualiza!", "Error", JOptionPane.ERROR_MESSAGE);

            }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Valoare lipsă sau incorectă!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        JButton btnAnuleazaPromotii = new JButton("Anulează Promotii");
        btnAnuleazaPromotii.addActionListener(e -> anuleazaPromotii());


        panelAdministrator.setLayout(new BorderLayout());
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        panelAdministrator.add(tablePanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnAnuleazaPromotii);
        buttonPanel.setBackground(new Color(248, 236, 225));
        panelAdministrator.add(buttonPanel, BorderLayout.SOUTH);

        JPanel addPanel = new JPanel(new GridLayout(4, 2));


        JLabel lblDenumire = new JLabel("Denumire:");
        addPanel.add(lblDenumire);
        addPanel.add(txtDenumire);
        JLabel lblMarca = new JLabel("Marca:");
        addPanel.add(lblMarca);
        addPanel.add(txtMarca);

        JLabel lblStoc = new JLabel("Stoc:");
        addPanel.add(lblStoc);
        addPanel.add(txtStoc);

        JLabel lblReducere = new JLabel("Reducere (%):");
        addPanel.add(lblReducere);
        addPanel.add(txtReducere);

        JLabel lblPret = new JLabel("Pret:");
        addPanel.add(lblPret);
        addPanel.add(txtPret);
        addPanel.add(Box.createHorizontalGlue());
        addPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        addPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        addPanel.add(btnAdauga);
        addPanel.add(btnSterge);
        addPanel.add(btnActualizeaza);
        btnAdauga.setPreferredSize(new Dimension( 40, 25));
        btnSterge.setPreferredSize(new Dimension( 40, 25));
        btnActualizeaza.setPreferredSize(new Dimension( 40, 25));

        addPanel.setBackground(new Color(248, 236, 225));
        panelAdministrator.add(addPanel, BorderLayout.NORTH);

    }

    @Override
    public void setTable(DefaultTableModel model) {
        table.setModel(model);
        addTableSorter();
        scrollPane.setViewportView(table);
    }

    @Override
    public void refreshTable() {
        a.adaugareTabel();
    }


    private void addTableSorter() {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
    }

    @Override
    public void anuleazaPromotii() {
        a.anuleazaPromotii();
    }

}