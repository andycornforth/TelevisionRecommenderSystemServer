/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package collaborativeFiltering;

/**
 *
 * @author Andy
 */
public class testMatrixMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Engine matrix = new Engine();

        matrix.addUser("Andy");
        matrix.addRating("Andy", "Lost", 10);
        matrix.addRating("Andy", "The Mentalist", 8);
        matrix.addRating("Andy", "Football Live", 9);

        matrix.addUser("Joe");
        matrix.addRating("Joe", "Cricket", 8);
        matrix.addRating("Joe", "Hot Fuzz", 9);
        matrix.addRating("Joe", "Football Live", 7);

        matrix.addUser("Dave");
        matrix.addRating("Dave", "Smallville", 8);
        matrix.addRating("Dave", "Hot Fuzz", 6);
        matrix.addRating("Dave", "Cricket", 7);

        System.out.println(matrix.getRecommendations("Joe"));

//
//
//
//
//
//
//
//
//
//
//
//
//
//        /*
//         * add users
//         */
//        matrix.addUser("Andy");//sport/science fiction/drama
//
//        matrix.addRating("Andy", "MOTD", 8);
//        matrix.addRating("Andy", "Lost", 10);
//        matrix.addRating("Andy", "The Mentalist", 8);
//        matrix.addRating("Andy", "Football Live", 9);
//        matrix.addRating("Andy", "Smallville", 8);
//        matrix.addRating("Andy", "Homeland", 8);
//        matrix.addRating("Andy", "Game Of Thrones", 9);
//        matrix.addRating("Andy", "Cricket", 7);
//        matrix.addRating("Andy", "The Big Bang Theory", 8);
//        matrix.addRating("Andy", "Haven", 8);
//
//        matrix.addUser("Joe");//sport/movies/quizshows
//
//        matrix.addRating("Joe", "Cricket", 8);
//        matrix.addRating("Joe", "Hot Fuzz", 9);
//        matrix.addRating("Joe", "Pointless", 7);
//        matrix.addRating("Joe", "Tennis", 8);
//        matrix.addRating("Joe", "Darts", 9);
//        matrix.addRating("Joe", "Eggheads", 6);
//        matrix.addRating("Joe", "Kill Bill", 8);
//        matrix.addRating("Joe", "Scarface", 9);
//        matrix.addRating("Joe", "Deal Or No Deal", 6);
//
//        matrix.addUser("Ollie");//documenteries/movies/comedy
//
//        matrix.addRating("Ollie", "Alien Life", 8);
//        matrix.addRating("Ollie", "Hot Fuzz", 9);
//        matrix.addRating("Ollie", "QI", 7);
//        matrix.addRating("Ollie", "Space Weather", 8);
//        matrix.addRating("Ollie", "Horizon", 9);
//        matrix.addRating("Ollie", "Mock The Week", 7);
//        matrix.addRating("Ollie", "Scarface", 8);
//        matrix.addRating("Ollie", "Fight Club", 9);
//        matrix.addRating("Ollie", "Panorama", 7);
//
//        matrix.addUser("Dave");//science fiction/drama/news
//
//        matrix.addRating("Dave", "Smallville", 8);
//        matrix.addRating("Dave", "Breakfast", 6);
//        matrix.addRating("Dave", "Click", 7);
//        matrix.addRating("Dave", "Star Trek", 8);
//        matrix.addRating("Dave", "Homeland", 9);
//        matrix.addRating("Dave", "Warehouse 13", 7);
//        matrix.addRating("Dave", "5 News", 5);
//        matrix.addRating("Dave", "Kill Bill", 8);
//        matrix.addRating("Dave", "Supernatural", 8);
//
//        matrix.addUser("Mary");//quizshows/news
//
//        matrix.addRating("Mary", "Eggheads", 8);
//        matrix.addRating("Mary", "Bullseye", 9);
//        matrix.addRating("Mary", "Goldenballs", 6);
//        matrix.addRating("Mary", "Breakfast", 8);
//        matrix.addRating("Mary", "Question Time", 8);
//
//        matrix.addUser("Alice");//reality tv/quizshows/comedy
//
//        matrix.addRating("Alice", "QI", 7);
//        matrix.addRating("Alice", "Nevermind The Buzzcocks", 6);
//        matrix.addRating("Alice", "Big Brother", 8);
//        matrix.addRating("Alice", "The X Factor", 8);
//        matrix.addRating("Alice", "Hot Fuzz", 8);
//        matrix.addRating("Alice", "GoldenBalls", 7);
//        matrix.addRating("Alice", "Pointless", 7);
//        matrix.addRating("Alice", "I'm A Celebrity", 8);
//        matrix.addRating("Alice", "Come Dine With Me", 6);
//
//        matrix.addUser("Patrick");//drama/science fiction
//
//        matrix.addRating("Patrick", "Homeland", 7);
//        matrix.addRating("Patrick", "The Mentalist", 9);
//        matrix.addRating("Patrick", "Warehouse 13", 8);
//        matrix.addRating("Patrick", "The Walking Dead", 9);
//        matrix.addRating("Patrick", "American Horror Story", 7);
//        matrix.addRating("Patrick", "Sherlock", 9);
//        matrix.addRating("Patrick", "CSI", 9);
//        matrix.addRating("Patrick", "Star Trek", 7);
//
//        matrix.addUser("Lucy");//reality tv/drama
//
//        matrix.addRating("Lucy", "Homeland", 9);
//        matrix.addRating("Lucy", "Big Brother", 7);
//        matrix.addRating("Lucy", "The X Factor", 9);
//        matrix.addRating("Lucy", "Game Of Thrones", 9);
//        matrix.addRating("Lucy", "Eastenders", 9);
//        matrix.addRating("Lucy", "America's Next Top Model", 6);
//        matrix.addRating("Lucy", "Pointless", 9);
//        matrix.addRating("Lucy", "Waterloo Road", 9);
//
//        matrix.addUser("Nicholas");//movies/drama/sport
//
//        matrix.addRating("Nicholas", "Lost", 9);
//        matrix.addRating("Nicholas", "Castle", 8);
//        matrix.addRating("Nicholas", "The Mentalist", 9);
//        matrix.addRating("Nicholas", "Layer Cake", 7);
//        matrix.addRating("Nicholas", "The Godfather", 9);
//        matrix.addRating("Nicholas", "Scarface", 9);
//        matrix.addRating("Nicholas", "MOTD", 9);
//        matrix.addRating("Nicholas", "Darts", 9);
//        matrix.addRating("Nicholas", "Cricket", 9);
//
//
//        String name = "Andy";
//        System.out.println(matrix.getRecommendations(name));
//        System.out.println(matrix.getMatrix().getUsersRatings(name));
    }
}
