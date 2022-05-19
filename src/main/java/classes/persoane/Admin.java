package classes.persoane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Admin extends Persoana{

    public Admin(String nume, String parola, int varsta, String tip) {

        super(nume, parola, varsta, "Admin");
    }

    @Override
    public String toString(){

        String text = "";
        text += "\nID : " + getId();
        text += "\nNume : " + getNume();
        text += "\nVarsta : " + getVarsta();
        text += "\nTip : " + getTip();

        return text;
    }
}
