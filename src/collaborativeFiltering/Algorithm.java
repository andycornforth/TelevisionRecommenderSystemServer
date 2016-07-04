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
public abstract class Algorithm {

    protected HashMap map;

    public Algorithm(HashMap h) {
        map = h;
    }

    public abstract Set getRecommendationSet(String userName);

    public abstract String findBestMatch(String userName);
}
