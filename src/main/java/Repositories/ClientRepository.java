package Repositories;

import classes.persoane.Client;
import classes.persoane.Medic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends Repository<Client>{

    public ClientRepository(){

        super();
    }

    @Override
    protected void insert(Client client) {

        String text = "";
        text += "insert into clienti (nume, parola, varsta, tip, adresa, telefon) values (";
        text += String.format("'%s', '%s', %d, '%s', '%s', '%s'", client.getNume(), client.getParola(), client.getVarsta(), client.getTip(), client.getAdresa(), client.getTelefon());
        text += ");";
        executeStatement(text);
    }

    @Override
    protected void delete(int id) {

        String text = String.format("delete from clienti where id = %d ;", id);
        executeStatement(text);
    }

    @Override
    protected ResultSet allResultSet() {

        executeStatement("select * from clienti;");
        try{

            return statement.getResultSet();
        }catch (SQLException e){

            System.out.println("eroare la executeStatement;");
            return null;
        }
    }

    @Override
    protected List<Client> all() {
        ResultSet set = allResultSet();
        List<Client> clienti = new ArrayList<>();

        try{

            while(set.next()){

                Client client = new Client(set.getString(2), set.getString(3), set.getInt(4), set.getString(5), set.getString(6), set.getString(7));
                client.setId(set.getInt(1));
                clienti.add(client);
            }
            return clienti;
        }catch (Exception e){

            System.out.println("eroare la select * from medici");
            return null;
        }
    }

    @Override
    protected void update(Client client) {

        String text = "";
        text += String.format("update clienti set nume = '%s', parola = '%s', varsta = %d, tip = '%s', adresa = '%s', telefon = '%s' where id = %d ;", client.getNume(), client.getParola(), client.getVarsta(), client.getTip(), client.getAdresa(), client.getTelefon(), client.getId());
        executeStatement(text);
    }

    @Override
    protected boolean existsID(int id) {
        executeStatement(String.format("select * from clienti where id = %d", id));

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

    public Client getClientByID(int id){

        executeStatement(String.format("select * from clienti where id = %d", id));
        try{

            ResultSet set = statement.getResultSet();
            if(set.next()){

                Client client = new Client(set.getString(2), set.getString(3), set.getInt(4), set.getString(5), set.getString(6), set.getString(7));
                client.setId(set.getInt(1));

                return client;
            }
        }catch (Exception e){

            System.out.println("eroare la getClientByID");
        }

        return null;
    }
}
