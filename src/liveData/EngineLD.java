/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liveData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Andy
 */
public class EngineLD {

    private Xmltv xmltv;
    private ReadDatabase read;
    private UsersDatabase userDB;

    public EngineLD() {
        xmltv = new Xmltv();
        read = new ReadDatabase();
        userDB = new UsersDatabase();
    }

    /*
     * **************** XMLTV Methods ******************************************
     */
    public void addChannel(String channelName) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, FileNotFoundException, SQLException {
        xmltv.addChannel(channelName);
    }

    public void updateChannel(String channelName) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, FileNotFoundException, SQLException {
        xmltv.upDateChannel(channelName);
    }
    
    /*
     * *********************** READ DATABASE Methods ***************************
     */
    public String seeChannels() throws SQLException {
        return read.seeChannels();
    }
    
    public String seeShows() throws SQLException {
        return read.seeShows();
    }
    
    public String seeSchedule(int channel_id) throws SQLException {
        return read.seeSchedule(channel_id);
    }
    
    public String seeUsers() throws SQLException{
        return read.seeUsers();
    }
    
    public String getShowDescription(String showname) throws SQLException{
        return read.getShowDescription(showname);
    }
    
    public int getShowRating(String username, String showname) throws SQLException{
        return read.getShowRating(username, showname);
    }
    
    public String getUserRatings(String username, boolean type) throws SQLException{
        return read.getUserRatings(username, type);
    }
    
    public String getNextShowing(String showname) throws SQLException{
        return read.getNextShowing(showname);
    }
    
    public String getChannelSchedule(String channel, String date) throws SQLException{
        return read.getChannelSchedule(channel, date);
    }
    
    public String getEpisodeDescription(String channel, String show, String date, String startTime) throws SQLException{
        return read.getEpisodeDescription(channel, show, date, startTime);
    }
    /*
     * *********************** USER DATABASE Methods ***************************
     */
    public boolean addUser(int id, String name, int age, char g, String password) throws SQLException {
        return userDB.addUser(id, name, age, g, password);
    }
    
    public boolean updateUser(int id, String name, int age, char g, String password) throws SQLException {
        return userDB.updateUser(id, name, age, g, password);
    }
    
    public boolean addRating(String name, String show, int rating) throws SQLException{
        return userDB.addRating(name, show, rating);
    }
    
    public boolean login(String username, String password) throws SQLException{
        return read.checkUserPassword(username, password);
    }
    
    public String getUserDetails(String username) throws SQLException{
        return read.getUserDetails(username);
    }
}
