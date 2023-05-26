package Model;

import java.io.Serializable;
import java.util.UUID;


public class Produs implements Serializable {
        private UUID idProdus;
        private int reducere, stoc;
        private String denumire, marca;
        private float pret;

    public Produs() {
        this.idProdus = UUID.randomUUID();
    }

    public Produs(UUID idProdus, int reducere, int stoc, String denumire, String marca, float pret) {
        this.idProdus = idProdus;
        this.reducere = reducere;
        this.stoc = stoc;
        this.denumire = denumire;
        this.marca = marca;
        this.pret = pret;
    }

    public UUID getIdProdus() {
        return idProdus;
    }
    public void setIdProdus(UUID idProdus) {
        this.idProdus = idProdus;
    }
    public int getStoc() {
        return stoc;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }

    public int getReducere() {
        return reducere;
    }

    public void setReducere(int reducere) {
        this.reducere = reducere;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public float getPret() {
        return pret;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Produs{" +
                "idProdus=" + idProdus +
                ", reducere=" + reducere +
                ", stoc=" + stoc +
                ", denumire='" + denumire + '\'' +
                ", marca='" + marca + '\'' +
                ", pret=" + pret +
                '}';
    }
}
