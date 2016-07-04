/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentBasedFiltering;

/**
 *
 * @author Andy
 */
public class EngineCB {
    
    private ContentBasedMatrix shows;
    private CBCosine cosine;
    private TFIDF tfidf;
    
    public EngineCB(){
        shows = new ContentBasedMatrix();
    }
    
    public void addShow(String showName, String showDescription){
        shows.addShow(showName, showDescription);
    }
    
    public String getRecommendation(String showName){
        String result;
        // apply term frequency - inverse document frequency to all shows and words
        tfidf = new TFIDF(shows.getContentMatrix());
        // create an instance of the cosine similarity algorithm passing in all the shows
        cosine = new CBCosine(tfidf.getShows());
        // find the best match using the cosine algorithm
        result = cosine.findBestMatch(showName);
        // return the show name that best matches 
        return result;
    }
    
    public double getCosineSimilarity(String showA, String showB){
        // work out the similarity between the 2 desired shows and return
        return cosine.getSimilarity(showA, showB);
    }
    
    public void upDate(){
        // apply term frequency - inverse document frequency to all shows and words
        tfidf = new TFIDF(shows.getContentMatrix());
        // create an instance of the cosine similarity algorithm
        cosine = new CBCosine(tfidf.getShows());
    }
}
