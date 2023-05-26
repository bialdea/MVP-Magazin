package Presenter;

import Model.Produs;
import Model.ProdusPersistenta;
import View.IAdministratorFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.UUID;

public class AdministratorPresenter {
    private final IAdministratorFrame vizualizareAdministrator;
    private final ProdusPersistenta persistentaProdus;

    public AdministratorPresenter(IAdministratorFrame vizualizareAdministrator) {
        this.vizualizareAdministrator = vizualizareAdministrator;
        this.persistentaProdus = new ProdusPersistenta();

    }
    public void adaugareTabel() {
        List<Produs> produse = persistentaProdus.getListaProduse();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5;
            }
        };
        model.addColumn("ID Produs");
        model.addColumn("denumire");
        model.addColumn("marca");
        model.addColumn("stoc");
        model.addColumn("promotie");
        model.addColumn("pret");


        for (Produs p : produse) {
            model.addRow(new Object[]{p.getIdProdus(), p.getDenumire(), p.getMarca(), p.getStoc(), p.getReducere(), p.getPret()});
        }

        vizualizareAdministrator.setTable(model);
    }
    public void adaugareProdus(Produs produs) {
        List<Produs> produse = persistentaProdus.getListaProduse();
        boolean existaProdus = false;
        for (Produs p : produse) {
            if (p.getIdProdus() == produs.getIdProdus()) {
                existaProdus = true;
                break;
            }
        }

        if (existaProdus) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Produs deja existent!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean success = persistentaProdus.adaugareProdus(produs);
            if (success) {
                JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Adaugare efectuata cu succes!", "Message", JOptionPane.INFORMATION_MESSAGE);
                vizualizareAdministrator.refreshTable();
            } else {
                JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Eroare la adaugarea produsului!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public void actualizareProdus(Produs produsActualizat) {
        boolean success = persistentaProdus.actualizeazaProdus(produsActualizat);
        if (success) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Actualizare efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
            vizualizareAdministrator.refreshTable();
        } else {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Eroare la actualizarea produsului!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void stergereProdus(UUID idProdus) {
        boolean success = persistentaProdus.stergeProdus(idProdus);
        if (success) {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Stergere efectuata cu succes!","Message",JOptionPane.INFORMATION_MESSAGE);
            vizualizareAdministrator.refreshTable();
        } else {
            JOptionPane.showMessageDialog(new JFrame("JOptionPane"),"Eroare la stergerea produsului!","Error",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public void anuleazaPromotii() {
        persistentaProdus.anuleazaPromotii();
        JOptionPane.showMessageDialog(new JFrame("JOptionPane"), "Promoțiile au fost anulate cu succes!", "Message", JOptionPane.INFORMATION_MESSAGE);
        vizualizareAdministrator.refreshTable();
    }

}
