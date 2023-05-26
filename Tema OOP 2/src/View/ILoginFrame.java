package View;

public interface ILoginFrame {

    String getUser();
    String getParola();

    void mesajEroare();

    void setClientView(String user);

    void setAdministratorView(String user);

}
