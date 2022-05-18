package Repositories;

import classes.clinica.Clinica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicaRepository extends Repository<Clinica> {

    public ClinicaRepository(){

        super();
    }

    @Override
    protected void insert(Clinica clinica) {

        String textSpecializari = "";
        ArrayList<String> specializari = clinica.getSpecializari();
        for(int i = 0;i < specializari.size(); i++){

            if(i == specializari.size() -1){

                textSpecializari += specializari.get(i);
            }
            else{

                textSpecializari += specializari.get(i) + ",";
            }
        }
        System.out.println(textSpecializari);

        String textMediciID = "";
        ArrayList<Integer> mediciID = clinica.getMediciID();
        for(int i=0; i< mediciID.size(); i++){

            if(i == mediciID.size() -1){

                textMediciID += mediciID.get(i);
            }
            else{

                textMediciID += mediciID.get(i) + ",";
            }
        }
        System.out.println(textMediciID);

        String text = "";
        text += "insert into clinici (nume, oras, adresa, specializari, mediciID) values(";
        text += String.format("'%s', '%s', '%s', '%s', '%s');", clinica.getNume(), clinica.getOras(), clinica.getAdresa(), textSpecializari, textMediciID);

        executeStatement(text);
    }

    @Override
    protected void delete(int id) {

        String text = "";
        text += String.format("delete from clinici where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    protected ResultSet allResultSet() {

        executeStatement("select * from clinici;");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("eroare la select * from clinici");
            return null;
        }
    }

    @Override
    protected List<Clinica> all() {

        List<Clinica> clinici = new ArrayList<>();
        ResultSet set = allResultSet();
        try{

            while(set.next()){

                ArrayList<String> specializari = new ArrayList<>();
                ArrayList<Integer> mediciID = new ArrayList<>();

                String vSepc [] = set.getString(5).split(",");
                String vMedId [] = set.getString(6).split(",");

                for(int i=0; i < vSepc.length; i++){
                    specializari.add(vSepc[i]);
                }

                //System.out.println(specializari);

                for(int i=0; i < vMedId.length; i++){
                    mediciID.add(Integer.parseInt(vMedId[i]));
                }
                //System.out.println(mediciID);

                Clinica clinica = new Clinica(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), specializari, mediciID);
                clinici.add(clinica);
            }

            return clinici;

        }catch (Exception e){

            System.out.println("eroare, nu s-a creat lista");
            return null;
        }
    }

    @Override
    protected void update(Clinica clinica) {

        String textSpecializari = "";
        ArrayList<String> specializari = clinica.getSpecializari();
        for(int i = 0;i < specializari.size(); i++){

            if(i == specializari.size() -1){

                textSpecializari += specializari.get(i);
            }
            else{

                textSpecializari += specializari.get(i) + ",";
            }
        }

        String textMediciID = "";
        ArrayList<Integer> mediciID = clinica.getMediciID();
        for(int i=0; i< mediciID.size(); i++){

            if(i == mediciID.size() -1){

                textMediciID += mediciID.get(i);
            }
            else{

                textMediciID += mediciID.get(i) + ",";
            }
        }

        String text = "";
        text += String.format("update clinici set nume = '%s', oras = '%s', adresa = '%s', specializari = '%s', mediciID = '%s' where id = %d ;", clinica.getNume(), clinica.getOras(), clinica.getAdresa(), textSpecializari, textMediciID, clinica.getId());
        executeStatement(text);
    }

    public void traverse(){

        List<Clinica> clinici = all();
        for(Clinica clinica : clinici){

            System.out.println(clinica);
            System.out.println();
        }
    }

    @Override
    public boolean existsID(int id){

        executeStatement(String.format("select * from clinici where id = %d ;", id));
        try{
            ResultSet set = statement.getResultSet();
            if (set.next()){

                return true;
            }
            return false;
        }catch (Exception e){

            System.out.println("eroare la existsID");
        }
        return false;
    }

    public Clinica getClinicaByID(int id){

        executeStatement(String.format("select * from clinici where id = %d ;", id));

        try{

            ResultSet set = statement.getResultSet();
            if(set.next()){

                ArrayList<String> specializari = new ArrayList<>();
                ArrayList<Integer> mediciID = new ArrayList<>();

                String vSepc [] = set.getString(5).split(",");
                String vMedId [] = set.getString(6).split(",");

                for(int i=0; i < vSepc.length; i++){
                    specializari.add(vSepc[i]);
                }

                //System.out.println(specializari);

                for(int i=0; i < vMedId.length; i++){
                    mediciID.add(Integer.parseInt(vMedId[i]));
                }
                //System.out.println(mediciID);

                Clinica clinica = new Clinica(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), specializari, mediciID);
                return clinica;
            }
        }catch (Exception e){

            System.out.println("eroare la getClinicaByID");
        }
        return null;
    }
}
