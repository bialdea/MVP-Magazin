package Model;


import java.io.Serializable;

public class Utilizator implements Serializable {
    private int idUtilizator;
    private String username;
    private String parola;
    private String tip;

    public Utilizator() {
    }
    public void setIdUtilizator(int idUtilizator) {
        this.idUtilizator = idUtilizator;
    }
    public String getUser() {
        return username;
    }

    public void setUser(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }



    @Override
    public String toString() {
        return "Utilizator{" +
                "idUtilizator=" + idUtilizator +
                ", username='" + username + '\'' +
                ", parola='" + parola + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
