package View;

import javax.swing.table.DefaultTableModel;

public interface IAdministratorFrame {
    void setTable(DefaultTableModel model);

    void refreshTable();
    void anuleazaPromotii();
}
