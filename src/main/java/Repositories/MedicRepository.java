package Repositories;

import classes.persoane.Medic;
import classes.programare.Programare;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicRepository extends Repository<Medic>{

    public MedicRepository(){

        super();
    }

    @Override
    protected void insert(Medic medic) {

        String text = "";
        text += "insert into medici (nume, parola, varsta, tip, specializare, salariu, experienta) values (";
        text += String.format("'%s', '%s', %d, '%s', '%s', %.2f, %d", medic.getNume(), medic.getParola(), medic.getVarsta(), medic.getTip(), medic.getSpecializare(), medic.getSalariu(), medic.getExperienta());
        text += ");";
        executeStatement(text);
    }

    @Override
    protected void delete(int id) {

        String text = String.format("delete from medici where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    protected ResultSet allResultSet() {
        executeStatement("select * from medici;");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("eroare la executeStatement;");
            return null;
        }
    }

    @Override
    protected List<Medic> all() {

        ResultSet set = allResultSet();
        List<Medic> medici = new ArrayList<>();

        try{

            while(set.next()){

                Medic medic = new Medic(set.getString(2), set.getString(3), set.getInt(4), set.getString(5), set.getString(6), set.getDouble(7), set.getInt(8));
                medic.setId(set.getInt(1));
                medici.add(medic);
            }
            return medici;
        }catch (Exception e){

            System.out.println("eroare la select * from medici");
            return null;
        }
    }

    @Override
    protected void update(Medic medic) {

        String text = String.format("update medici set nume = '%s', parola = '%s', varsta = %d, tip = '%s', specializare = '%s', salariu = %.2f, experienta = %d where id = %d ;", medic.getNume(), medic.getParola(), medic.getVarsta(), medic.getTip(), medic.getSpecializare(), medic.getSalariu(), medic.getExperienta(), medic.getId());
        executeStatement(text);
    }

    @Override
    protected boolean existsID(int id) {

        executeStatement(String.format("select * from medici where id = %d", id));

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

    public Medic getMedicByID(int id){

        if(existsID(id)){

            executeStatement(String.format("select * from medici where id = %d;", id));

            try{
                ResultSet set = statement.getResultSet();
                if (set.next()){

                    Medic m = new Medic(set.getString(2), set.getString(3), set.getInt(4), set.getString(5), set.getString(6), set.getDouble(7), set.getInt(8));
                    m.setId(set.getInt(1));
                    return m;
                }
            }catch (Exception e){

                System.out.println("eroare la getMedicByID");

            }
        }
        return null;
    }
}
