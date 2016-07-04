/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborativeFiltering;

import java.util.Set;

/**
 *
 * @author Andy
 */

/*
 * Facade class to control the matrix and algorithm classes
 */
public class Engine {

    private MapMatrix matrix;
    private CosineSimilarity algorithm;

    /*
     * Constructor
     */
    public Engine() {
        matrix = new MapMatrix();
        algorithm = new CosineSimilarity(matrix.getMatrix());
    }

    /*
     * ***************** Simple methods for matrix *****************************
     */
    // add a user to the matrix, return user id
    public int addUser(String name) {
        return matrix.addUser(name);
    }

    // add a rating to a show for a user
    public boolean addRating(String user, String show, int rating) {
        return matrix.addRating(user, show, rating);
    }

    /*
     * ****************** Method for making recommendations *******************
     */
    public Set getRecommendations(String name) {
        return algorithm.getRecommendationSet(name);
    }
    
    public Set getUsersRatings(String name){
        return matrix.getMatrix().get(name).keySet();
    }

    /*
     * get matrix - used for testing and debugging
     */
    public MapMatrix getMatrix() {
        return matrix;
    }

    /*
     * used for loading the test data
     */
    public void upDateAlgorithm(){
        algorithm = new CosineSimilarity(matrix.getMatrix());
    }

}
