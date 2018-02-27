package app.database;

import java.sql.*;

public class DatabaseManager {
    private Connection conn;
    private Statement st;
    public DatabaseManager() throws Exception{
        openConnection();
        tableInit();
    }
    private void openConnection() throws Exception{
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection("jdbc:h2:~/database","sa","");
        st = conn.createStatement();

    }
    private void tableInit() throws Exception{
        String sql = "CREATE TABLE IF NOT EXISTS Persons (PersonID int PRIMARY KEY, LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255))";
        st.execute(sql);
    }
    public void closeConnection() throws Exception{
        conn.close();
    }
    public void addPerson(int personID, String lastName, String firstName, String address, String city) throws Exception{
        String sql = "INSERT INTO Persons VALUES ('"+personID+"', '"+lastName+"', '"+firstName+"', '"+address+"', '"+city+"')";
        st.execute(sql);
    }
    public void removePerson(int personID)throws Exception{
        String sql = "DELETE FROM Persons WHERE PersonID='"+personID+"';";
        st.execute(sql);
    }
    public ResultSet getPersons() throws Exception{
        String sql = "SELECT * FROM Persons;";
        return st.executeQuery(sql);
    }
}
