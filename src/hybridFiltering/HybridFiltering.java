/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hybridFiltering;

import contentBasedFiltering.EngineCB;
import collaborativeFiltering.Engine;
import java.util.Set;

/**
 *
 * @author Andy
 */
public class HybridFiltering {

    private Engine cfEngine;
    private EngineCB cbEngine;

    public HybridFiltering() {
        cfEngine = new Engine();
        cbEngine = new EngineCB();
    }

    /*
     * ************* Methods to add to CF engine *******************************
     */
    public int addUser(String name) {
        return cfEngine.addUser(name);
    }

    public boolean addRating(String user, String show, int rating) {
        return cfEngine.addRating(user, show, rating);
    }

    /*
     * **************** Method to add to CB Engine *****************************
     */
    public void addShow(String showName, String showDescription) {
        cbEngine.addShow(showName, showDescription);
    }

    /*
     * ********** Get CF recommendation set ************************************
     */
    public Set getRecommendations(String name) {
        return cfEngine.getRecommendations(name);
    }

    public Set getUsersRatings(String name) {
        return cfEngine.getUsersRatings(name);
    }

    /*
     * ****************** Get CB recommendation from CF ************************
     */
    public String getHybridRecommendation(String name, int requestNumber) {
        String currentBest = "", secondBest = "", becauseYouWatched = "";
        // to return if no match by content-based
        String defaultShow = "";
        double currentBestCosine = 0;

        try {

            Set<String> recommendationSet = getRecommendations(name);
            Set<String> usersSet = getUsersRatings(name);

            //update tfidf and cosine
            cbEngine.upDate();
            if (recommendationSet != null) {
                // loop through the recommendation set
                for (String rec : recommendationSet) {
                    // loop through the users set
                    for (String users : usersSet) {
                        // get cosine similarity between 2 shows
                        double result = cbEngine.getCosineSimilarity(users, rec);
                        // compare result to see whether the shows are the best match yet
                        if (result > currentBestCosine) {
                            currentBestCosine = result;
                            secondBest = currentBest;
                            currentBest = rec;
                            becauseYouWatched = users;
                            break;
                        }
                    }
                    // this is set incase there are no similarities between any of the shows
                    defaultShow = rec;
                }

                if ("".equals(currentBest)) {
                    currentBest = defaultShow;
                }
                if ("".equals(secondBest)){
                    secondBest = defaultShow;
                }
                if(requestNumber % 2 != 0){
                    currentBest = secondBest;
                }
                return "<Recommend>" + currentBest + "</Recommend><Because>"
                        + becauseYouWatched + "</Because>";
            }
        } catch (Exception e) {
        }
        return "<Message>Unfortunatly we do not have enough information about "
                + "you for a recommendaion, please rate some shows and "
                + "try again</Message>";
    }

    /*
     * ******************* for loading test data *******************************
     */
    public Engine getCfEngine() {
        return cfEngine;
    }
}
