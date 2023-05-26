package Presenter;
import Model.*;
import View.IClientFrame;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.UUID;

public class ClientPresenter {
    private final IClientFrame vizualizareClient;
    private final ProdusPersistenta persistentaProdus;

    public ClientPresenter(IClientFrame vizualizareClient) {
        this.vizualizareClient = vizualizareClient;
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

        vizualizareClient.setTable(model);
    }

    public void readaugareTabel(){
        List<Produs> produse = persistentaProdus.filtreazaProduse(vizualizareClient.getCriteriu(), vizualizareClient.getFiltru());
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };

        model.addColumn("ID Produs");
        model.addColumn("denumire");
        model.addColumn("marca");
        model.addColumn("stoc");
        model.addColumn("promotie (%)");
        model.addColumn("pret");

        for (Produs p : produse) {
            model.addRow(new Object[]{p.getIdProdus(), p.getDenumire(), p.getMarca(), p.getStoc(), p.getReducere(), p.getPret()});
        }

        vizualizareClient.setTable(model);
    }
    public void readaugareTabelPret(){
        float pretMinim = vizualizareClient.getPretMinim();
        float pretMaxim = vizualizareClient.getPretMaxim();
        List<Produs> produse = persistentaProdus.filtreazaProduseCuPret(pretMinim, pretMaxim);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };

        model.addColumn("ID Produs");
        model.addColumn("denumire");
        model.addColumn("marca");
        model.addColumn("stoc");
        model.addColumn("promotie (%)");
        model.addColumn("pret");

        for (Produs p : produse) {
            model.addRow(new Object[]{p.getIdProdus(), p.getDenumire(), p.getMarca(), p.getStoc(), p.getReducere(), p.getPret()});
        }

        vizualizareClient.setTable(model);
    }
    public void afisareProdusePromotie() {
        List<Produs> produsePromotie = persistentaProdus.getProdusePromotie();

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 1 && column != 2 && column != 3 && column != 4 && column != 5 && column != 6 && column != 7;
            }
        };

        model.addColumn("ID Produs");
        model.addColumn("denumire");
        model.addColumn("marca");
        model.addColumn("stoc");
        model.addColumn("promotie (%)");
        model.addColumn("pret");

        for (Produs p : produsePromotie) {
            model.addRow(new Object[]{p.getIdProdus(), p.getDenumire(), p.getMarca(), p.getStoc(), p.getReducere(), p.getPret()});
        }

        vizualizareClient.setTable(model);
    }
    public boolean cumparaProdus(UUID idProdus) {
        return persistentaProdus.cumparaProdus(idProdus);
    }


}
