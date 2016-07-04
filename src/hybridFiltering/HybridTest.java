/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hybridFiltering;

/**
 *
 * @author Andy
 */
public class HybridTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HybridFiltering hybrid = new HybridFiltering();
        
        hybrid.addUser("Andy");
        hybrid.addUser("John");
        hybrid.addUser("Jane");
        hybrid.addUser("Henry");
        hybrid.addUser("Dave");

        hybrid.addRating("Andy", "Breakfast", 6);
        hybrid.addRating("Andy", "BBC News at One",5);
        hybrid.addRating("Andy", "Fake Britain",4);
        hybrid.addRating("Andy", "QI XL", 8);
        hybrid.addRating("Andy", "Click", 4);
        hybrid.addRating("Andy", "The Simpsons", 7);
        hybrid.addRating("Andy", "The Big Bang Theory", 9);
        hybrid.addRating("Andy", "Tennis: World Tour Finals", 7);

        hybrid.addRating("John", "Tennis: World Tour Finals", 5);
        hybrid.addRating("John", "Breakfast", 6);
        hybrid.addRating("John", "Fake Britain",5);
        hybrid.addRating("John", "American Football Live",7);
        hybrid.addRating("John", "The Mentalist",9);
    
        hybrid.addRating("Jane", "The Mentalist",9);
        hybrid.addRating("Jane", "New: Castle",8);
        hybrid.addRating("Jane", "New: Under The Dome",7);
        hybrid.addRating("Jane", "Homeland",9);
        hybrid.addRating("Jane", "Perfection",5);
        hybrid.addRating("Jane", "Pointless",6);

        hybrid.addRating("Henry", "American Football Live",8);
        hybrid.addRating("Henry", "Volleyball",7);
        hybrid.addRating("Henry", "Cycling: Track World Cup",6);
        hybrid.addRating("Henry", "MOTD2 Extra",8);
        
        hybrid.addRating("Dave", "Homeland",9);
        hybrid.addRating("Dave", "The Mentalist",8);
        hybrid.addRating("Dave", "The Gadget Show",7);
        
        System.out.println(hybrid.getHybridRecommendation("Henry",0));
    }
}
