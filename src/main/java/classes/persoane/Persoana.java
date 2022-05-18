package classes.persoane;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Persoana {

    private int id;
    private String nume;
    private String parola;
    private int varsta;
    private String tip;


    public Persoana(String nume, String parola, int varsta, String tip) {

        this.nume = nume;
        this.parola = parola;
        this.varsta = varsta;
        this.tip = tip;
    }

    @Override
    public boolean equals(Object o){

        Persoana p = (Persoana) o;
        return this.getId() == p.getId() && this.getNume().equals(p.getNume());
    }

    //public abstract void copy(Persoana p);

    public abstract String toString();
}
