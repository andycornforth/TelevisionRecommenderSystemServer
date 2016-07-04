/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liveData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Andy
 */
public class UsersDatabase {

    private String host;
    private String username;
    private String password;

    public UsersDatabase() {
        host = "jdbc:derby://localhost:1527/ProjectTest3";
        username = "andy";
        password = "project";
    }

    public boolean addUser(int id, String name, int age, char g, String userpassword) throws SQLException {
        // create a connection to the Java database
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement to execute a query
        Statement stmt = con.createStatement();
        // create a query
        String sql = "SELECT USER_NAME FROM APP.USERS WHERE USER_NAME = '" + name + "'";
        // execute the query and output to a result set
        ResultSet rs = stmt.executeQuery(sql);
        // if the user is not already in the database add the new user
        if (!rs.next()) {
            String addEntry = "INSERT INTO APP.USERS VALUES(" + id + ",'" + name
                    + "'," + age + ",'" + g + "'," + 0 + ",'" + userpassword + "')";
            stmt.executeUpdate(addEntry);
            return true;
        }
        return false;
    }

    public boolean addRating(String user, String showname, int rating) throws SQLException {
        Connection con = DriverManager.getConnection(host, username, password);
        Statement stmt = con.createStatement();

        // query to determain the user
        String sql = "SELECT * FROM APP.USERS WHERE USER_NAME = '" + user + "'";
        ResultSet rs = stmt.executeQuery(sql);


        // if the user is valid
        if (rs.next()) {
            
            // query to determain the tv show
            String sql2 = "SELECT * FROM APP.TV_SHOW WHERE SHOW_NAME = '" + showname + "'";
            ResultSet rs2 = stmt.executeQuery(sql2);
            // if the show is valid
            if (rs2.next()) {
                
                // query to determain if the rating already exists
                String sql3 = "SELECT * FROM APP.RATINGS WHERE SHOW_NAME = '" + showname + "' AND USER_NAME = '" + user + "'";
                ResultSet rs3 = stmt.executeQuery(sql3);
                // if the rating already exists and we need to append
                if (rs3.next()) {
                    String append = "UPDATE APP.RATINGS SET SHOW_RATING = "
                            + rating + " WHERE USER_NAME = '" + user
                            + "' AND SHOW_NAME = '" + showname + "'";
                    stmt.executeUpdate(append);
                } else {
                    String addEntry = "INSERT INTO APP.RATINGS VALUES('" + user
                            + "','" + showname + "'," + rating + ")";
                    stmt.executeUpdate(addEntry);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean updateUser(int id, String name, int age, char g, String userpassword) throws SQLException {
        Connection con = DriverManager.getConnection(host, username, password);
        Statement stmt = con.createStatement();

        String sql = "SELECT USER_NAME FROM APP.USERS WHERE USER_NAME = '" + name + "'";
        ResultSet rs = stmt.executeQuery(sql);

        // get the user
        if (rs.next()) {
            String append = "UPDATE APP.USERS "
                    + "SET PASSWORD = '" + userpassword + "', "
                    + "USER_AGE = " + age + ", "
                    + "USER_GENDER = '" + g + "' "
                    + "WHERE USER_NAME = '" + name + "'";
                    stmt.executeUpdate(append);
            return true;
        }
        return false;
    }
}
