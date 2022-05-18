package classes.clinica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Clinica implements Comparable<Clinica>{

    private int id;
    private String nume;
    private String oras;
    private String adresa;
    private ArrayList<String> specializari;
    private ArrayList<Integer> mediciID;//lista de id-uri de medici care lucreaza la aceasta clinica


    public Clinica(String nume, String oras, String adresa, ArrayList<String> specializari, ArrayList<Integer> medici) {

        this.nume = nume;
        this.oras = oras;
        this.adresa = adresa;
        this.specializari = specializari;
        this.mediciID = medici;
    }

    @Override
    public String toString(){

        String text = "";
        text += "\nID : " + id;
        text += "\nNume : " + nume;
        text += "\nOras : " + oras;
        text += "\nAdresa : " + adresa;
        text += "\nSpecializari : ";
        for(int i = 0;i < specializari.size(); i++){

            if(i == specializari.size() -1){

                text += specializari.get(i) + ".";
            }
            else{

                text += specializari.get(i) + ", ";
            }
        }
        text += "\nNumar medici : " + mediciID.size();

        return text;
    }

    @Override
    public int compareTo(Clinica c){//compare dupa nr de specializari

        if(this.specializari.size() < c.specializari.size()){

            return -1;
        }
        else if(this.specializari.size() > c.specializari.size()){

            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public boolean equals(Object o){//egale dupa nr de medici

        Clinica c = (Clinica) o;
        return this.getId() == c.getId() && this.nume.equals(c.getNume());
    }

    public boolean existaSpecializare(String specializare){

        for(String s:specializari){

            if(s.equals(specializare)){

                return true;
            }
        }
        return false;
    }
}
