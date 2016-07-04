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
import java.util.Calendar;

/**
 *
 * @author Andy
 */
public class ReadDatabase {

    private String host;
    private String username;
    private String password;

    /*
     * Constructor
     */
    public ReadDatabase() {
        host = "jdbc:derby://localhost:1527/ProjectTest3";
        username = "andy";
        password = "project";
    }

    /*
     * return a string containing all the channels in the database
     */
    public String seeChannels() throws SQLException {
        String result = "";

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        String sql = "SELECT CHANNEL_NAME FROM APP.CHANNEL";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result += rs.getString("CHANNEL_NAME") + "\n";
        }
        return result;
    }

    /*
     * return a string containing all individual shows and descriptions in the database
     */
    public String seeShows() throws SQLException {
        String result = "";

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM APP.TV_SHOW";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result += rs.getString("SHOW_NAME") + "\n"
                    + rs.getString("SHOW_DESCRIPTION") + "\n";
        }
        return result;
    }

    public String seeSchedule(int channel_id) throws SQLException {
        String result = "";

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // get show name where channel id = desired channel
        String sql = "SELECT SHOW_NAME, START_TIME, END_TIME, DATE, DESCRIPTION"
                + " FROM APP.SCHEDULE WHERE CHANNEL_ID = " + channel_id;
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result += removeHyphen(rs.getString("SHOW_NAME")) + "-";
            result += rs.getString("START_TIME") + "-";
            result += rs.getString("END_TIME") + "-";
            result += rs.getString("DATE") + "-";
            result += removeHyphen(rs.getString("DESCRIPTION")) + "-";
        }
        return result;
    }

    public String seeUsers() throws SQLException {
        String result = "";
        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // get show name where channel id = desired channel
        String sql = "SELECT * FROM APP.USERS";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result += rs.getString("USER_NAME") + "/";
        }
        return result;
    }

    public String getShowDescription(String showname) throws SQLException {
        String result = "<Show>" + showname + "</Show><Description>";

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT SHOW_DESCRIPTION FROM APP.TV_SHOW WHERE SHOW_NAME = '" + showname + "'";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            result += rs.getString("SHOW_DESCRIPTION") + "</Description>";
        } else {
            result += "No show information available</Description>";
        }
        return result;

    }

    /*
     * return the specific rating of a users show
     */
    public int getShowRating(String user, String showname) throws SQLException {

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT SHOW_RATING FROM APP.RATINGS WHERE SHOW_NAME = '"
                + showname + "' AND USER_NAME = '" + user + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            return rs.getInt("SHOW_RATING");
        }
        return -1;
    }

    /*
     * return a string of all the users ratings
     */
    public String getUserRatings(String user, boolean type) throws SQLException {
        String result = "";

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT * FROM APP.RATINGS WHERE USER_NAME = '" + user + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            if (type) {
                result += "<show><title>" + rs.getString("SHOW_NAME") + "</title>";
                result += "<rating>" + rs.getInt("SHOW_RATING") + "</rating></show>";
            }else{
                result += rs.getString("SHOW_NAME") + "/";
                result += rs.getInt("SHOW_RATING") + "/";
            }
        }

        return result;
    }

    public String getNextShowing(String showname) throws SQLException {

        String[] split = showname.split("/");
        showname = split[0];

        String result = "";
        // get todays date
        Calendar cal = Calendar.getInstance();
        // date for show
        Calendar cal2 = Calendar.getInstance();


        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        showname = showname.replace("<Recommend>", "");
        showname = showname.replace("<", "");
        String sql = "SELECT * FROM APP.SCHEDULE WHERE SHOW_NAME = '" + showname + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {

            String date = rs.getString("DATE");
            int year = Integer.parseInt(date.substring(4, 8));
            int month = Integer.parseInt(date.substring(2, 4));
            int day = Integer.parseInt(date.substring(0, 2));
            // set the date of the showing
            cal2.set(year, month - 1, day);

            if (cal.getTime().getMonth() <= cal2.getTime().getMonth()) {
                if (cal.getTime().getDate() <= cal2.getTime().getDate()) {
                    String desc = rs.getString("DESCRIPTION");
                    String start = rs.getString("START_TIME");
                    String end = rs.getString("END_TIME");
                    String channel = rs.getString("CHANNEL_ID");
                    channel = getChannel(channel);
                    result += "<Next Showing:   Date - " + date + ", Time:    - " + start
                            + ",   Channel - " + channel
                            + ",   Description - " + desc + ">";
                    return result;
                }
            }

        }

        return result + "<message>There is currently no showing of this programme in the"
                + " upcoming week</message>";
    }

    private String removeHyphen(String str) {
        String result;

        result = str.replace("-", "_");

        return result;
    }

    private String getChannel(String id) throws SQLException {
        String result = "";
        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT CHANNEL_NAME FROM APP.CHANNEL WHERE CHANNEL_ID = " + id;
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            result = rs.getString("CHANNEL_NAME");
        }
        return result;
    }

    private String getChannelID(String channel) throws SQLException {
        String result = null;
        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT CHANNEL_ID FROM APP.CHANNEL WHERE CHANNEL_NAME = '" + channel + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next()) {
            result = rs.getString("CHANNEL_ID");
        }
        return result;
    }

    public String getChannelSchedule(String channel, String date) throws SQLException {
        String result = "";
        String channel_id = getChannelID(channel);

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // get show name where channel id = desired channel
        String sql = "SELECT SHOW_NAME, START_TIME, END_TIME FROM APP.SCHEDULE "
                + "WHERE CHANNEL_ID = " + channel_id + " AND DATE = '" + date + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result += "<Slot><Show>" + removeHyphen(rs.getString("SHOW_NAME")) + "</Show>";
            result += "<Start>" + rs.getString("START_TIME") + "</Start>";
            result += "<End>" + rs.getString("END_TIME") + "</End></Slot>";
        }
        return result;
    }

    public boolean checkUserPassword(String user, String userPassword) throws SQLException {

        String result = null;

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT PASSWORD FROM APP.USERS WHERE USER_NAME = '" + user + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result = rs.getString("PASSWORD");
        }
        if (userPassword == null) {
            return true;
        } else if (userPassword.equals(result)) {
            return true;
        }
        return false;
    }

    public String getUserDetails(String user) throws SQLException {
        String result = "";

        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT USER_AGE, USER_GENDER, PASSWORD FROM APP.USERS WHERE USER_NAME = '" + user + "'";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result += "<age>" + rs.getString("USER_AGE") + "</age>";
            result += "<gender>" + rs.getString("USER_GENDER") + "</gender>";
            result += "<password>" + rs.getString("PASSWORD") + "</password>";
        }
        return result;
    }
    
    public String getEpisodeDescription(String showname, String channel, String date, String startTime) throws SQLException{
        String channel_id = this.getChannelID(channel);
        String result = "<Show>" + showname + "</Show><Description>";
        // CONNECT TO DATABASE
        Connection con = DriverManager.getConnection(host, username, password);
        // create a statement, string and resultset.
        Statement stmt = con.createStatement();
        // create query string
        String sql = "SELECT DESCRIPTION FROM APP.SCHEDULE WHERE SHOW_NAME = '"
                + showname + "' AND CHANNEL_ID = " + channel_id + " AND DATE = '" 
                + date + "' AND START_TIME = '" + startTime + "'";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            result += rs.getString("DESCRIPTION") + "</Description>";
        } else {
            result += "No show information available</Description>";
        }
        return result;
    }
}
