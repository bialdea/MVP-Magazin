package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UtilizatorPersistenta {
    private static final String FISIER_CSV = "utilizatori.csv";
    private static final String CSV_SEPARATOR = ",";

    private static UtilizatorPersistenta instance;

    private UtilizatorPersistenta() {
    }

    public static UtilizatorPersistenta getInstance() {
        if (instance == null) {
            instance = new UtilizatorPersistenta();
        }
        return instance;
    }

    public Utilizator cautareUtilizator(String username, String parola) {
        List<Utilizator> utilizatori = incarcaUtilizatori();
        for (Utilizator utilizator : utilizatori) {
            if (utilizator.getUser().equals(username) && utilizator.getParola().equals(parola)) {
                return utilizator;
            }
        }
        return null;
    }

    private List<Utilizator> incarcaUtilizatori() {
        List<Utilizator> utilizatori = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FISIER_CSV))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(CSV_SEPARATOR);
                if (values.length == 4) {
                    Utilizator utilizator = new Utilizator();
                    utilizator.setIdUtilizator(Integer.parseInt(values[0]));
                    utilizator.setUser(values[1]);
                    utilizator.setParola(values[2]);
                    utilizator.setTip(values[3]);
                    utilizatori.add(utilizator);
                }
            }
        } catch (IOException e) {
            System.out.println("Eroare la incarcarea utilizatorilor: " + e.getMessage());
        }
        return utilizatori;
    }

}
