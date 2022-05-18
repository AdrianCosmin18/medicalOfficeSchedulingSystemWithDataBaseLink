package classes.programare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programare {

    private int id;
    private int idClient;
    private int idMedic;
    private int idClinica;
    private String data;


    public Programare(int idClient, int idMedic, int idClinica, String data) {

        this.idClient = idClient;
        this.idMedic = idMedic;
        this.idClinica = idClinica;
        this.data = data;
    }

    @Override
    public String toString(){

        String text = "";
        text += "ID Programare : "+ id;
        text += "\nID client : " + idClient;
        text += "\nID medic : " + idMedic;
        text += "\nID clinica : " + idClinica;
        text += "\nData : " + data;

        return text;
    }

    @Override
    public boolean equals(Object o){//egalitate daca eu acelasi ID

        return this.id == ((Programare) o).id;
    }

}
