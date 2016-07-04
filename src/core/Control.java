/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import hybridFiltering.HybridFiltering;
import java.sql.SQLException;
import java.util.Set;
import liveData.EngineLD;

/**
 *
 * @author Andy
 */

/*
 * Methods in class:
 * 
 * addShows()
 * addUser()
 * addRating()
 * getRating()
 * getCFRecommendationSet()
 * getRecommendation()
 * channelSchedule()
 * getShowDescription()
 * getNextShowing()
 * updateDatabase()
 * loadDBToMatrix()
 * 
 */
public class Control {

    private HybridFiltering hybrid;
    private EngineLD liveData;

    /*
     * Constructor
     */
    public Control() throws SQLException, Exception {
        hybrid = new HybridFiltering();
        liveData = new EngineLD();
        upDateDatabase();
        addShows();
        loadDBToMatrix();
    }

    /*
     * called in constructor, adds all show names and descriptions to cb engine
     */
    public void addShows() throws SQLException {
        String allShowsString = liveData.seeShows();
        String[] allShows = allShowsString.split("\n");
        if (!"".equals(allShowsString)) {
            for (int i = 0; i < allShows.length; i = i + 2) {
                hybrid.addShow(allShows[i], allShows[i + 1]);
            }
        }
    }

    /*
     * add a user to the cf engine
     */
    public boolean addUser(String name, String password, int age, char gender) throws SQLException {
        int id = hybrid.addUser(name);
        return liveData.addUser(id, name, age, gender, password);
    }

    public boolean updateUser(String name, String password, int age, char gender) throws SQLException {
        int id = hybrid.addUser(name);
        return liveData.updateUser(id, name, age, gender, password);
    }
    /*
     * add like to cf engine
     */

    public boolean addRating(String user, String show, int rating) throws SQLException {
        hybrid.addRating(user, show, rating);
        return liveData.addRating(user, show, rating);
    }

    public int getRating(String user, String show) throws SQLException {
        return liveData.getShowRating(user, show);
    }

    /*
     * get a recommendation for a user from the cf engine
     */
    public Set getCFRecommendationSet(String user) {
        return hybrid.getRecommendations(user);
    }

    /*
     * *********** GET MAIN RECOMMENDATION USING HYBRID FILTERING **************
     * format of returned string:
     * recommended show / because you rated / date / start / end / channel / desc
     */
    public String getRecommendation(String user, int requestNumber) throws SQLException {
        // uses set returned from the cf recommendation and uses cb to get best
        String result = hybrid.getHybridRecommendation(user, requestNumber);
        return result + liveData.getNextShowing(result);
    }

    public boolean LOG_IN(String username, String password) throws SQLException {
        return liveData.login(username, password);
    }

    /*
     * return a channel schedule in a string arrray, for each 5 in array is a 
     * new show
     */
    public String[] channelSchedule(int channel_id) throws SQLException {
        // get string with all data needed
        String str = liveData.seeSchedule(channel_id);
        // split into an array
        String[] data = str.split("-");

        return data;
    }

    public String getChannelSchedule(String channel, String date) throws SQLException {
        return liveData.getChannelSchedule(channel, date);
    }

    /*
     * return a television shows description from its name
     */
    public String getShowDescription(String showname) throws SQLException {
        return liveData.getShowDescription(showname);
    }

    /*
     * return xml of next showing of the programme
     */
    public String getNextShowing(String showname) throws SQLException {
        return liveData.getNextShowing(showname);
    }

    public String getUserDetails(String username) throws SQLException {
        return liveData.getUserDetails(username);
    }

    public String getEpisode(String show, String channel, String date, String startTime) throws SQLException {
        return liveData.getEpisodeDescription(channel, show, date, startTime);
    }
    /*
     ******** method to update the database with new tv listings for next 7 days
     */

    public void upDateDatabase() throws Exception {
//            liveData.addChannel("bbc1");
//            liveData.addChannel("bbc2");
//            liveData.addChannel("ch4");
//            liveData.addChannel("five");
//            liveData.addChannel("sky_one");
//            liveData.addChannel("scifi");
//            liveData.addChannel("sky_sports1");
//            liveData.addChannel("sky_movies_premiere");
        liveData.updateChannel("bbc1");
        liveData.updateChannel("bbc2");
        liveData.updateChannel("ch4");
        liveData.updateChannel("five");
        liveData.updateChannel("sky_one");
        liveData.updateChannel("scifi");
        liveData.updateChannel("sky_sports1");
        liveData.updateChannel("sky_movies_premiere");
    }

    /*
     * ********************* load information from database into matrix ********
     */
    public void loadDBToMatrix() throws SQLException {
        // get a string array of all the users in the database
        String[] users = liveData.seeUsers().split("/");
        //loop through all users
        for (int i = 0; i < users.length; i++) {
            String username = users[i];
            hybrid.addUser(username);
            // get all the users ratings from the database
            String[] usersRatings = liveData.getUserRatings(username, false).split("/");
            //add all ratings to the matrix
            for (int j = 0; j < usersRatings.length; j = j + 2) {
                String show = usersRatings[j];
                if (!show.equals("")) {
                    int rating = Integer.parseInt(usersRatings[j + 1]);
                    addRating(username, show, rating);
                }
            }
        }
    }

    public String getUsersRatings(String user, boolean type) throws SQLException {
        return liveData.getUserRatings(user, type);
    }
}
