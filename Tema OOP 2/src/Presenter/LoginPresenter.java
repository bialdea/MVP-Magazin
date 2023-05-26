package Presenter;

import Model.*;
import View.ILoginFrame;

import java.sql.SQLException;


public class LoginPresenter {
    ILoginFrame link;
    UtilizatorPersistenta utilizator;

    public LoginPresenter(ILoginFrame link1) {
        link = link1;
        utilizator = UtilizatorPersistenta.getInstance();
    }


    public void autentificare() throws SQLException {
        String username = link.getUser();
        String password = link.getParola();
            Utilizator u = utilizator.cautareUtilizator(username, password);
            if (u == null)
                link.mesajEroare();
            else {
                if (u.getTip().equals("client"))
                    link.setClientView(u.getUser());
                else if (u.getTip().equals("administrator"))
                    link.setAdministratorView(u.getUser());
                }

            }
        }


