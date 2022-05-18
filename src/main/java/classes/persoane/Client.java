package classes.persoane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client extends Persoana implements Comparable<Client>{


    private String adresa;
    private String telefon;


    public Client(String nume, String parola, int varsta, String tip, String adresa, String telefon) {

        super(nume, parola, varsta, "Client");
        this.adresa = adresa;
        this.telefon = telefon;
    }

    @Override
    public String toString(){

        String text = "";
        text += "\nID : " + getId();
        text += "\nNume : " + getNume();
        text += "\nVarsta : " + getVarsta();
        text += "\nAdresa : " + adresa;
        text += "\nTelefon : " + telefon;
        text += "\nTip : " + getTip();

        return text;
    }

    @Override
    public int compareTo(Client c) {//compare dupa varsta

        if(this.getVarsta() < c.getVarsta()){

            return -1;
        }
        else if(this.getVarsta() > c.getVarsta()){

            return 1;
        }
        else{

            return 0;
        }
    }
}
