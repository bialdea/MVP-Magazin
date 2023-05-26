package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProdusPersistenta {
    private static final String FISIER_CSV = "produse.csv";
    private static final String CSV_SEPARATOR = ",";


    public ProdusPersistenta() {
    }

    private void salveazaProduse(List<Produs> produse) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FISIER_CSV))) {
            writer.println("ID,Reducere,Stoc,Denumire,Marca,Pret");
            for (Produs produs : produse) {
                StringBuilder sb = new StringBuilder();
                sb.append(produs.getIdProdus()).append(CSV_SEPARATOR);
                sb.append(produs.getReducere()).append(CSV_SEPARATOR);
                sb.append(produs.getStoc()).append(CSV_SEPARATOR);
                sb.append(produs.getDenumire()).append(CSV_SEPARATOR);
                sb.append(produs.getMarca()).append(CSV_SEPARATOR);
                sb.append(produs.getPret()).append("\n");
                writer.write(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Eroare la salvarea produselor: " + e.getMessage());
        }
    }

    private List<Produs> incarcaProduse() {
        List<Produs> produse = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FISIER_CSV))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR);
                if (values.length == 6) {
                    try {
                        Produs produs = new Produs();
                        produs.setIdProdus(UUID.fromString(values[0]));
                        produs.setReducere(Integer.parseInt(values[1]));
                        produs.setStoc(Integer.parseInt(values[2]));
                        produs.setDenumire(values[3]);
                        produs.setMarca(values[4]);
                        produs.setPret(Float.parseFloat(values[5]));
                        produse.add(produs);
                    } catch (IllegalArgumentException e) {
                        // Handle or log the error when encountering an invalid UUID string
                        System.out.println("Invalid UUID string: " + values[0]);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la incarcarea produselor: " + e.getMessage());
        }
        return produse;
    }


    public List<Produs> getListaProduse() {
        return incarcaProduse();
    }

    public boolean adaugareProdus(Produs produs) {
        List<Produs> produse = incarcaProduse();
        produse.add(produs);
        salveazaProduse(produse);
        return true;
    }

    public boolean stergeProdus(UUID idProdus) {
        List<Produs> produse = incarcaProduse();
        boolean produsGasit = false;
        for (Produs produs : produse) {
            if (produs.getIdProdus().equals(idProdus)) {
                produse.remove(produs);
                produsGasit = true;
                break;
            }
        }
        if (produsGasit) {
            salveazaProduse(produse);
        }
        return produsGasit;
    }

    public boolean actualizeazaProdus(Produs produsActualizat) {
        List<Produs> produse = incarcaProduse();
        boolean produsGasit = false;
        for (int i = 0; i < produse.size(); i++) {
            Produs produs = produse.get(i);
            if (produs.getIdProdus().equals(produsActualizat.getIdProdus())) {
                produse.set(i, produsActualizat);
                produsGasit = true;
                break;
            }
        }
        if (produsGasit) {
            salveazaProduse(produse);
        }
        return produsGasit;
    }

    public List<Produs> filtreazaProduse(String criteriu, String filtru) {
        List<Produs> produseFiltrate = new ArrayList<>();
        List<Produs> produse = incarcaProduse();
        for (Produs produs : produse) {
            if (criteriu.equals("denumire") && produs.getDenumire().equals(filtru)) {
                produseFiltrate.add(produs);
            } else if (criteriu.equals("marca") && produs.getMarca().equals(filtru)) {
                produseFiltrate.add(produs);
            }
        }
        return produseFiltrate;
    }

    public List<Produs> filtreazaProduseCuPret(float pretMinim, float pretMaxim) {
        List<Produs> produseFiltrate = new ArrayList<>();
        List<Produs> produse = incarcaProduse();
        for (Produs produs : produse) {
            if (produs.getPret() >= pretMinim && produs.getPret() <= pretMaxim) {
                produseFiltrate.add(produs);
            }
        }
        return produseFiltrate;
    }

    public List<Produs> getProdusePromotie() {
        List<Produs> produsePromotie = new ArrayList<>();
        List<Produs> produse = incarcaProduse();
        for (Produs produs : produse) {
            if (produs.getReducere() != 0) {
                produsePromotie.add(produs);
            }
        }
        return produsePromotie;
    }

    public boolean cumparaProdus(UUID idProdus) {
        List<Produs> produse = incarcaProduse();
        for (Produs produs : produse) {
            if (produs.getIdProdus().equals(idProdus)) {
                int stoc = produs.getStoc();
                if (stoc > 0) {
                    produs.setStoc(stoc - 1);
                    return actualizeazaProdus(produs);
                }
                break;
            }
        }
        return false;
    }

    public void anuleazaPromotii() {
        List<Produs> produse = incarcaProduse();
        for (Produs produs : produse) {
            produs.setReducere(0);
        }
        salveazaProduse(produse);
    }
}
