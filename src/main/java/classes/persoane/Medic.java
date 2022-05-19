package classes.persoane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medic extends Persoana implements Comparable<Medic>{

    private String specializare;
    private double salariu;
    private int experienta;//nr de ani


    public Medic(String nume, String parola, int varsta, String tip, String specializare, double salariu, int xp) {

        super(nume, parola, varsta, "Medic");
        this.specializare = specializare;
        this.salariu = salariu;
        this.experienta = xp;
    }

    @Override
    public String toString() {

        String text = "";
        text += "\nID : " + this.getId();
        text += "\nNume : " + this.getNume();
        text += "\nVarsta : " + this.getVarsta();
        text += "\nTip : " + this.getTip();
        text += "\nSpecializare : " + this.specializare;
        text += "\nExperienta : " + this.experienta + " ani";

        return text;
    }

    @Override
    public int compareTo(Medic m){//compare dupa anii de experienta

        if(this.experienta < m.experienta){

            return -1;
        }
        else if(this.experienta > m.experienta){

            return 1;
        }
        else{

            return 0;
        }
    }
}
