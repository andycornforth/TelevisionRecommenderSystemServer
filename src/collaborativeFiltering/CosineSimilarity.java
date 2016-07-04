/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborativeFiltering;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Andy
 */
public class CosineSimilarity extends Algorithm{

    public CosineSimilarity(HashMap h) {
        super(h);
    }

    private double cosine_similarity(HashMap<String, Integer> v1, HashMap<String, Integer> v2) {
        // clone so we dont loose all the data
        HashMap v2Clone = (HashMap) v2.clone();

        Set<String> both = v2Clone.keySet();
        both.retainAll(v1.keySet());

        double sclar = 0, norm1 = 0, norm2 = 0, r1, r2;

        for (String k : both) {
            r1 = v1.get(k);
            r2 = v2.get(k);
            sclar += r1 * r2;
        }
        for (String k : v1.keySet()) {
            r1 = v1.get(k);
            norm1 += r1 * r1;
        }
        for (String k : v2.keySet()) {
            r2 = v2.get(k);
            norm2 += r2 * r2;
        }
        return sclar / Math.sqrt(norm1 * norm2);
    }

    /*
     * find the best matched user for a specific user
     */
    @Override
    public String findBestMatch(String userName) {
        String bestMatch = null;
        double currentBest = -1;
        // get a set of all users
        Set<String> allUsers = map.keySet();
        // loop through the set of users
        for (String k : allUsers) {
            // as long as we don't compare the same user to itself
            if (!k.equals(userName)) {
                HashMap h1, h2;
                h1 = (HashMap) map.get(userName);
                h2 = (HashMap) map.get(k);
                // work out the cosine similarity between the 2 users
                double cosineS = cosine_similarity(h1,h2);
                // if its the best match so far, set it as current best and best match
                if(cosineS > currentBest){
                    currentBest = cosineS;
                    bestMatch = k;
                }
            }
        }
        return bestMatch;
    }
    
    @Override
    public Set getRecommendationSet(String userA){
        
        String userB = findBestMatch(userA);
        return compareSets(userA, userB);
    }
    
    private Set compareSets(String userA, String userB){
        // if user has no match
        if(userB == null){
            return null;
        }
        HashMap user1Shows = (HashMap) map.get(userA);
        HashMap user2Shows = (HashMap) map.get(userB);
        // clone so we don't loose all the data
        HashMap user2Clone = (HashMap) user2Shows.clone();

        Set userASet = user1Shows.keySet();
        Set userBSet = user2Clone.keySet();

        userBSet.removeAll(userASet);
        
        return userBSet;
    }
}
