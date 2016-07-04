/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liveData;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andy
 */
public class Database {

    private String host;
    private String username;
    private String password;
    private int nChannels;

    /*
     * constructor
     */
    public Database() {
        host = "jdbc:derby://localhost:1527/ProjectTest3";
        username = "andy";
        password = "project";
        nChannels = 0;
    }

    public void addToDatabase(String file) throws FileNotFoundException, SQLException, IOException {
        addChannel(file);
        addShows(file);
        addSchedule(file);
    }

    /*
     * add channel name to CHANNEL table ***************************************
     */
    private void addChannel(String file) throws FileNotFoundException, SQLException {
        // connect to the database
        try {
            Connection con = DriverManager.getConnection(host, username, password);
            // split file name to get name of channel
            String[] fileSplit = new String[2];
            fileSplit = file.split("-");
            /* if table in database does not alread contain a channel with the same 
             * name then add the channel:
             * create a statement, query and results set, these are the 3 things 
             * needed to execute an sql command in java */
            Statement stmt = con.createStatement();
            String sql = "SELECT CHANNEL_NAME FROM APP.CHANNEL WHERE CHANNEL_NAME "
                    + "= '" + fileSplit[0] + "'";
            ResultSet rs = stmt.executeQuery(sql);
            // if table does not contain channel name, then add channel to database
            if (!rs.next()) {
                nChannels++;
                String addEntry = "INSERT INTO APP.CHANNEL"
                        + " VALUES(" + nChannels + ", '" + fileSplit[0] + "')";
                // add channel
                stmt.executeUpdate(addEntry);
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("could not connect");
        }
    }

    /*
     * add shows to TV_SHOW table **********************************************
     */
    private void addShows(String file) throws IOException {
        // connect to the database
        try {
            Connection con = DriverManager.getConnection(host, username, password);
            // to read in text file
            BufferedReader in = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                String line = in.readLine();
                // loop until every line in the file has been read
                while (line != null) {
                    // remove the character "'" from show name as it corrupts statement
                    if (line.contains("'")) {
                        line = line.replace("'", "");
                    }
                    // set show name
                    String showName = line;
                    // avoid start and end time
                    for (int i = 0; i < 2; i++) {
                        line = in.readLine();
                    }
                    // set description
                    String description = in.readLine();
                    if (description.contains("'")) {
                        description = description.replace("'", "");
                    }
                    // do not add show if same show is already in table
                    Statement stmt = con.createStatement();
                    String sql = "SELECT SHOW_NAME FROM APP.TV_SHOW WHERE SHOW_NAME = '" + showName + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    if (!rs.next()) {
                        // add show title to the tv_show table
                        String addEntry = "INSERT INTO APP.TV_SHOW"
                                + " VALUES('" + showName + "', '" + description + "')";
                        stmt.executeUpdate(addEntry);
                    }
                    // ready for the next loop
                    line = in.readLine();
                }
            } finally {
                in.close();
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("could not connect");
        }
    }

    /*
     * add the listings to SCHEDULE table **************************************
     */
    private void addSchedule(String file) throws FileNotFoundException, IOException {
        // split file name to get name of channel and the date
        String[] fileSplit = new String[2];
        fileSplit = file.split("-");
        String channel = fileSplit[0];
        String date = fileSplit[1];
        // variables - other strings and ints used for the query
        String schedule_id, show_name, start_time, end_time, description;
        int channel_id;
        // connect to the database
        try {
            Connection con = DriverManager.getConnection(host, username, password);
            // to read in text file
            BufferedReader in = new BufferedReader(new FileReader(file));
            try {
                StringBuilder sb = new StringBuilder();
                String line = in.readLine();
                // loop until every line in the file has been read
                while (line != null) {
                    sb.append(line);
                    sb.append('\n');
                    // remove the character "'" from show name as it corrupts statement
                    line = fixString(line);
                    // set all variables
                    show_name = line;
                    channel_id = getChannelId(channel);
                    start_time = in.readLine();
                    end_time = in.readLine();
                    date = date.replace(".txt", "");
                    description = in.readLine();
                    description = fixString(description);
                    // set the schedule_id, this must be unique
                    schedule_id = channel_id + show_name + date + start_time + end_time;

                    // do not add show if same schedule is already in table
                    Statement stmt = con.createStatement();
                    String sql = "SELECT SCHEDULE_ID FROM APP.SCHEDULE WHERE SCHEDULE_ID = '" + schedule_id + "'";
                    ResultSet rs = stmt.executeQuery(sql);

                    // IF OKAY
                    if (!rs.next()) {
                        // create statement
                        stmt = con.createStatement();
                        String addEntry = "INSERT INTO APP.SCHEDULE"
                                + " VALUES('" + schedule_id + "', '" + show_name + "', "
                                + channel_id + ", '" + start_time + "', '"
                                + end_time + "', '" + date + "', '" + description
                                + "'" + ")";
                        stmt.executeUpdate(addEntry);
                    }
                    // read the next line ready for the next loop
                    line = in.readLine();
                }
            } finally {
                in.close();
            }
        } catch (SQLException err) {
            System.out.println(err.getMessage());
            System.out.println("could not connect");
        }
    }
    
    /*
     * return the id of desired channel name
     */
    private int getChannelId(String name) throws SQLException {
        int id = 0;
        Connection con = DriverManager.getConnection(host, username, password);
        Statement stmt = con.createStatement();
        String sql = "SELECT CHANNEL_ID FROM APP.CHANNEL WHERE CHANNEL_NAME = '" + name + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            id = rs.getInt("CHANNEL_ID");
        }
        return id;
    }

    /*
     * validate strings to be accepted by database
     */
    private String fixString(String str) {
        if (str.contains("'")) {
            str = str.replace("'", "");
        }
        return str;
    }
}
