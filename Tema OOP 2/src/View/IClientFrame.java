package View;

import javax.swing.table.DefaultTableModel;

public interface IClientFrame {
    String getCriteriu();
    String getFiltru();

    void setTable(DefaultTableModel model);

    float getPretMinim();

    float getPretMaxim();
}
