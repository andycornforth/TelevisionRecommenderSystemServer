/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testData;

import collaborativeFiltering.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Andy
 *
 * holds the user test data sets
 *
 */
public class TestDataSets {

    /*
     * class variables - all users
     */
    private ArrayList<User> users;
    // specific user data sets
    private User animated1 = new User();
    private User comedyDrama1 = new User(), comedyDrama2 = new User();
    private User comedyQuiz1 = new User();
    private User dayTime1 = new User(), dayTime2 = new User();
    private User documentary1 = new User();
    private User gameShow1 = new User();
    private User historical1 = new User();
    private User procedural1 = new User();
    private User drama1 = new User(), drama2 = new User(), drama3 = new User();
    private User scifi1 = new User();
    private User soap1 = new User();
    private User supernatural1 = new User();
    private User talkShow1 = new User();
    private User sport1 = new User(), sport2 = new User();
    // mixed user sets
//    private User mixed1 = new User(), mixed2 = new User(), mixed3 = new User(),
//            mixed4 = new User(), mixed5 = new User(), mixed6 = new User(),
//            mixed7 = new User(), mixed8 = new User(), mixed9 = new User(),
//            mixed10 = new User();

    /*
     * Constructor *************************************************************
     */
    public TestDataSets() {
        users = new ArrayList<>();
        createUsers();
    }

    private void createUsers() {

        addUsers();

        String[] names = getNames();
        int[] ages = getAges();
        char[] genders = getGenders();

        for (int i = 0; i < users.size(); i++) {
            users.get(i).setName(names[i]);
            users.get(i).setAge(ages[i]);
            users.get(i).setGender(genders[i]);
        }
    }

    private String[] getNames() {
        // string holding all the names of the users, 70 names
        String names = "Mercedes\nJulia\nSeymour\nRoselle\nAlden\nNathanial\n"
                + "Kellie\nJosue\nTamera\nEpifania\nJarrod\nHayden\nRoyce\nEmanuel"
                + "\nRachel\nNeal\nKarla\nErrol\nEnedina\nGay\nFreddy\nJosphine"
                + "\nSpencer\nDean\nSamatha\nMonte\nSheila\nClaudio\nKieth\n"
                + "Georgiana\nCaitlyn\nShawnda\nCarlee\nAnglea\nWinifred\nLowell"
                + "\nNoe\nMyrtice\nGretchen\nKieth\nKristeen\nDenisha\nCleo\nAsa"
                + "\nKeturah\nSuanne\nAdrian\nJeff\nOswaldo\nGerald\nLanita\n"
                + "Dorthey\nJoey\nShanti\nNatosha\nSherron\nFloretta\nBenton\n"
                + "Morris\nMac\nEvette\nRosaria\nLeland\nQuinton\nPura\nDonny\n"
                + "Morgan\nAndreas\nLacresha\nBoyd\nEverett\n";

        String[] allNames = names.split("\n");
        return allNames;
    }

    private int[] getAges() {
        GendersAndAges a = new GendersAndAges();
        return a.getAges();
    }

    private char[] getGenders() {
        GendersAndAges g = new GendersAndAges();
        return g.getGenders();
    }

    private void addUsers() {
        users.add(animated1);
        users.add(comedyDrama1);
        users.add(comedyDrama2);
        users.add(comedyQuiz1);
        users.add(dayTime1);
        users.add(dayTime2);
        users.add(documentary1);
        users.add(gameShow1);
        users.add(historical1);
        users.add(procedural1);
        users.add(drama1);
        users.add(drama2);
        users.add(drama3);
        users.add(scifi1);
        users.add(soap1);
        users.add(supernatural1);
        users.add(talkShow1);
        users.add(sport1);
        users.add(sport2);
//        users.add(mixed1);
//        users.add(mixed2);
//        users.add(mixed3);
//        users.add(mixed4);
//        users.add(mixed5);
//        users.add(mixed6);
//        users.add(mixed7);
//        users.add(mixed8);
//        users.add(mixed9);
//        users.add(mixed10);
    }

    private String[] shows() {
        String[] shows = new String[users.size()];

        String ani1 = "Peppa Pig,Roary The race car,bananas in pajamas,"
                + "city of friends,little princess,make way for noddy,"
                + "mr men,angelina ballerina,rupert bear,lazy town";
        shows[0] = ani1;
        String com1 = "miranda,are you being served?,allo allo!,dads army,"
                + "everybody loves raymond,frasier,the big bang theory,"
                + "the simpsons,night at the museum,fresh meat";
        shows[1] = com1;
        String com2 = "the middle,yonderland,trollied,futurama,according to jim,"
                + "will & grace,allo allo!,the big bang theory,fresh meat,frasier";
        shows[2] = com2;
        String comq1 = "have i got news for you,live at the apollo,qi xl,8 out of 10 cats,"
                + "a league of their own,8 out of 10 cats: uncut,the ricky gervais show";
        shows[3] = comq1;
        String day1 = "sunday politics,bargain hunt,escape to the country,homes "
                + "under the hammer,flog it!,antiques roadshow,coast,countdown,"
                + "come dine with me,neighbours";
        shows[4] = day1;
        String day2 = "road wars,airline,dog the bounty hunter,uk border force,"
                + "homes under the hammer,flog it!,come dine with me";
        shows[5] = day2;
        String doc1 = "countryfile,the nazi killers,obsesive compulsive cleaners,"
                + "the sound of musicals,googlebox";
        shows[6] = doc1;
        String game1 = "pointless,countdown,eggheads,pointless celebrities,"
                + "university challenge,pressure pad,a question of sport,"
                + "a league of their own,was it something i said";
        shows[7] = game1;
        String hist1 = "britain and the sea,great continental railway journeys,"
                + "days that shock the world,nazi quest for the holy grail";
        shows[8] = hist1;
        String proc1 = "ncis,ncis: los angeles,new: the mentalist,the mentalist,"
                + "new: castle,new tricks,new: person of interest,body of proof";
        shows[9] = proc1;
        String dram1 = "the paradise,ripper street,new tricks,holby city,the mentalist,"
                + "ncis,body of proof,hawaii five-o,buffy the vampire slayer,v";
        shows[10] = dram1;
        String dram2 = "defiance,marvels agents of s.h.i.e.l.d.,lethal weapon 4,"
                + "the fugative,arrow,new: arrow,alphas,homeland";
        shows[11] = dram2;
        String dram3 = "homeland,life of pi,new: haven,a town called eureka,warehouse 13";
        shows[12] = dram3;
        String sci1 = "buffy the vampire slayer,new: haven,warehouse 13,"
                + "a town called eureka,v,defiance,alphas,star trek: the next generation,"
                + "smallville";
        shows[13] = sci1;
        String sop1 = "eastenders,neighbours,hollyoaks,casualty,home and away";
        shows[14] = sop1;
        String sup1 = "buffy the vampire slayer,haven,devil,warehouse 13";
        shows[15] = sup1;
        String talk1 = "the one show,the wright stuff,news talk live,"
                + "breakfast,bbc news,daily politics";
        shows[16] = talk1;
        String spor1 = "a question of sport,good morning sports fans,ringside,"
                + "the american football show,nfl live,american football live,"
                + "goals on sunday,rugby club";
        shows[17] = spor1;
        String spor2 = "game changers,snf - match choice,the fantasy football club,"
                + "champions league weekly,football gold,goals on sunday,nfl live";
        shows[18] = spor1;
        String mix1 = "";
        String mix2 = "";
        String mix3 = "";
        String mix4 = "";
        String mix5 = "";
        String mix6 = "";
        String mix7 = "";
        String mix8 = "";
        String mix9 = "";
        String mix10 = "";

        return shows;
    }

    private HashMap<String, Integer> ratings(String str) {
        // split string
        String[] shows = str.split(",");
        HashMap<String, Integer> ratings = new HashMap<>();

        Random random = new Random();

        for (int i = 0; i < shows.length; i++) {
            int showRating = random.nextInt(5) + 5;
            String showName = uppercase(shows[i]);
            ratings.put(showName, showRating);
        }
        return ratings;
    }

    public HashMap<String, HashMap<String, Integer>> setMap() {

        HashMap<String, HashMap<String, Integer>> matrix = new HashMap<>();

        String[] shows = shows();

        for (int i = 0; i < users.size(); i++) {
            String name = users.get(i).getName();
            HashMap ratings = ratings(shows[i]);
            matrix.put(name, ratings);
        }

        return matrix;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private String uppercase(String str) {
        String[] words = str.split(" ");
        str = "";

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            char firstLetter = word.charAt(0);
            firstLetter = Character.toUpperCase(firstLetter);
            word = word.substring(1);
            word = firstLetter + word;
            if (i != words.length - 1) {
                str += word + " ";
            }else{
                str += word;
            }
        }

        return str;
    }
}
