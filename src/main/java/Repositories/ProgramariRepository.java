package Repositories;

import classes.programare.Programare;

import java.security.spec.ECField;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProgramariRepository extends Repository<Programare>{

    public ProgramariRepository(){

        super();
    }

    @Override
    public void insert(Programare programare) {

        String text = "";
        text += "insert into programari (client_id, medic_id, clinica_id, data) values (";
        text += String.format("%d, %d, %d, '%s'", programare.getIdClient(), programare.getIdMedic(), programare.getIdClinica(), programare.getData());
        text += ");";
        executeStatement(text);
    }

    @Override
    public void delete(int id) {

        String text = String.format("delete from programari where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    public ResultSet allResultSet() {

        executeStatement("select * from programari;");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("eroare la executeStatement;");
            return null;
        }
    }

    @Override
    public List<Programare> all() {

        ResultSet set = allResultSet();
        List<Programare> programari = new ArrayList<>();

        try{

            while(set.next()){

                Programare programare = new Programare(set.getInt(1), set.getInt(2), set.getInt(3), set.getInt(4), set.getString(5));
                programari.add(programare);
            }
            return programari;
        }catch (Exception e){

            System.out.println("eroare la select * from programari");
            return null;
        }
    }

    @Override
    public void update(Programare programare) {

        String text = String.format("update programari set client_id = %d, medic_id = %d, clinica_id = %d, data = '%s' where id = %d", programare.getIdClient(), programare.getIdMedic(), programare.getIdClinica(), programare.getData(), programare.getId());
        executeStatement(text);
    }

    @Override
    public boolean existsID(int id) {

        executeStatement(String.format("select * from programari where id = %d", id));

        try{

            ResultSet set = statement.getResultSet();

            if(set.next()){

                return true;
            }
        }catch (Exception e){

            System.out.println("eroare la existsID");
        }
        return false;
    }

    public Programare getProgramareByID(int id){

        if(existsID(id)){

            executeStatement(String.format("select * from programari where id = %d", id));

            try{
                ResultSet set = statement.getResultSet();
                if (set.next()){

                    return new Programare(set.getInt(1), set.getInt(2), set.getInt(3), set.getInt(4), set.getString(5));
                }
            }catch (Exception e){

                System.out.println("eroare la getProgramareByID");

            }
        }
        return null;
    }

    public List<Programare> getListByClientID(int id){

        executeStatement(String.format("select * from programari where client_id = %d", id));
        List<Programare> programri = new ArrayList<>();
        try{

            ResultSet set = statement.getResultSet();
            while(set.next()){

                Programare p =new Programare(set.getInt(1), set.getInt(2), set.getInt(3), set.getInt(4), set.getString(5));
                programri.add(p);
            }
            return programri;

        }catch (Exception e){

            System.out.println("eroare la getListByClientID");
        }
        return null;
    }

    @Override
    public void traverse(){

        List<Programare> programari = all();
        for(Programare p : programari){

            System.out.println(p);
            System.out.println();
        }
    }
}
