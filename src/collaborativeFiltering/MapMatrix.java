/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborativeFiltering;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Andy
 */
public class MapMatrix {

    // dynamic matrix to hold 0 or 1's determaining whether a user likes a show
    private HashMap<String, HashMap<String, Integer>> matrix;
    // normal arraylist to hold the corrosponding user information
    private ArrayList<String> users;
    // x = shows, y = users
    private int X, Y;

    /*
     * ********************* Constructor ***************************************
     */
    public MapMatrix() {
        matrix = new HashMap<>();
        users = new ArrayList<>();
        X = 0;
        Y = 0;
    }

    /*
     * ************* The 3 main methods ****************************************
     * add a user
     * add a rating
     * 
     */
    public int addUser(String name) {
        // create inside hashmap to contain users show ratings
        HashMap likes = new HashMap<>();
        // add the new user and show hashmap to the multidimensional hashmap
        matrix.put(name, likes);
        // now add the users information to the corrosponding users arraylist
        String userInfo = name;
        users.add(Y, userInfo);
        // increment the number of users
        Y++;
        // return the users id number
        return Y + 101;
    }

    public boolean addRating(String userName, String showName, int rating) {
        // a rating must be between 0 and 10
        if (rating > 10 || rating < 0) {
            return false;
        }
        HashMap usersShows = matrix.get(userName);
        usersShows.put(showName, rating);
        return true;
    }

    /*
     * ********************* Simple Getter methods *****************************
     */
    public HashMap getUsersRatings(String name) {
        return matrix.get(name);
    }

    public HashMap<String, HashMap<String, Integer>> getMatrix() {
        return matrix;
    }

    public ArrayList<String> getUsers() {
        return users;
    }
    
    /*
     * ******************* for loading test data *******************************
     */
    public void setMatrix(HashMap<String, HashMap<String, Integer>> matrix) {
        this.matrix = matrix;
    }
    
    
}
