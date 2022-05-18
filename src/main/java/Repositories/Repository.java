package Repositories;

import java.sql.*;
import java.util.List;

public abstract class Repository<T> {

    private String JdbcURL = "jdbc:mysql://localhost:3306/medicalOfficeSchedulingSystem";
    private String username = "root";
    private String password = "1234";
    private Connection connection = null;
    protected Statement statement = null;

    public Repository(){

        try{
            connection = DriverManager.getConnection(JdbcURL, username, password);
            statement = connection.createStatement();
        }catch(SQLException e){

            System.out.println("Eroare la conectare la baza de date");
        }
    }

    public void executeStatement(String execute){

        try{
            statement.execute(execute);
        }catch (SQLException e){

            System.out.println("Nu s-a reusit: " + execute);
        }
    }

    protected abstract void insert(T t);

    protected abstract void delete(int id);

    protected abstract ResultSet allResultSet();

    protected abstract List<T> all();

    protected abstract void update(T t);

    protected abstract boolean existsID(int id);
}
