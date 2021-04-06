package dao;

import java.sql.*;

public class DataConnection 
{
    
    static Connection con = null;
    private DataConnection(){}
    public static Connection createConnection() {
        if (con == null) 
        {
            try 
            {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj", "root", "sql");
             } 
            catch (Exception ex) 
            {
                System.out.println("Connection Failed:" + ex);
                return null;
            }
        }
        return con;
    }

    public static void closeConnection() 
    {
        if (con != null) 
        {
            try 
            {
                con.close();
                con = null;
            }
            catch (Exception ex) 
            {
                con = null;
            }
        }

    }

    public static void main(String[] args) 
    {
        DataConnection d = new DataConnection();
        d.createConnection();
        DataConnection d1 = new DataConnection();
        d1.createConnection();
    }
}
