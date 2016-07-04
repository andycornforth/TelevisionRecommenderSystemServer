/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package contentBasedFiltering;

/**
 *
 * @author Andy
 */
public class CBTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EngineCB control = new EngineCB();

        String show1 = "Breakfast";
        String description1 = "The latest news, sport, business and weather "
                + "from the BBCs Breakfast team. Also in HD. [S] Including"
                + " regional news at 25 and 55 minutes past each hour.";

        String show2 = "BBC News at One";
        String description2 = "The latest national and international news "
                + "stories from the BBC News team, followed by weather. "
                + "Also in HD. [S]";

        control.addShow(show1, description1);
        control.addShow(show2, description2);


        System.out.println(control.getRecommendation(show1));

        System.out.println(control.getCosineSimilarity(show1, show2));
        

    }
}
