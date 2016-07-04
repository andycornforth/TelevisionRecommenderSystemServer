/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

//import java.sql.SQLException;

import testData.SHA1;

//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import testData.TestDataSets;
/**
 *
 * @author Andy
 */
public class CoreMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        Control control = new Control();
        
        SHA1 sha = new SHA1();
        
        control.addUser("Andy", sha.SHA1("football"), 21, 'm');
        control.addUser("Billy", sha.SHA1("hazard"), 20, 'm');
        control.addUser("Mercedes", sha.SHA1("benz"), 26, 'f');
        control.addUser("Julia", sha.SHA1("seeee"), 60, 'f');
        control.addUser("Seymour", sha.SHA1("football"), 21, 'm');
        control.addUser("Roselle", sha.SHA1("hazard"), 20, 'm');
        control.addUser("Alden", sha.SHA1("benz"), 26, 'f');
        control.addUser("Nathanial", sha.SHA1("seeee"), 60, 'f');
        control.addUser("Kellie", sha.SHA1("football"), 21, 'm');
        control.addUser("Josue", sha.SHA1("hazard"), 20, 'm');
        control.addUser("Tamera", sha.SHA1("benz"), 26, 'f');
        control.addUser("Jarrod", sha.SHA1("seeee"), 60, 'f');
        control.addUser("Hayden", sha.SHA1("football"), 21, 'm');
        control.addUser("Royce", sha.SHA1("hazard"), 20, 'm');
        control.addUser("Emanuel", sha.SHA1("benz"), 26, 'f');
        control.addUser("Rachel", sha.SHA1("seeee"), 60, 'f');
        control.addUser("Neal", sha.SHA1("football"), 21, 'm');
        control.addUser("Karla", sha.SHA1("hazard"), 20, 'm');
        control.addUser("Errol", sha.SHA1("benz"), 26, 'f');
        control.addUser("mary", sha.SHA1("mum"), 49, 'm');
        control.addUser("Alison", sha.SHA1("zac"), 15, 'f');

        
        
    }
}
/*
 * 
 * 
 * NEED TO LOAD TEST DATA INTO DATABASE
 * 
 * 
 * 
 */
//        TestDataSets test = new TestDataSets();
//
//        HashMap map = test.setMap();
//
//        Iterator it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            String name = (String) pair.getKey();
//            HashMap ratings = (HashMap) pair.getValue();
//            
//            Iterator it2 = ratings.entrySet().iterator();
//            while (it2.hasNext()) {
//                
//                Map.Entry pair2 = (Map.Entry) it2.next();
//                String show = (String) pair2.getKey();
//                int rating = (int) pair2.getValue();
//                
//                control.addRating(name, show, rating);
//            }
//        }
