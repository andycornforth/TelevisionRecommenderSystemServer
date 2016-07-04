/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liveData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Andy
 */
/*example of ulr: http://www.bleb.org/tv/data/listings/0/bbc1.xml
 */
public class Xmltv {

    // variable to build valid url
    private URL url;
    private String urlStart = "http://www.bleb.org/tv/data/listings/";
    private int day;
    private String channel, end = ".xml", file;
    // other variables for class
    private int numberOfDays = 7;
    private int numberOfChannels = 4;
    // instance of database class
    Database db;

    // empty constuctor
    public Xmltv() {
        db = new Database();
    }

    /*
     * add channel with 7 days worth of listings
     */
    public void addChannel(String channelName) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, FileNotFoundException, SQLException {
        // set channel name
        channel = channelName;
        // loop for the number of days
        for (int i = 0; i < numberOfDays; i++) {
            // set day to start on
            file = textFile(i);//create text file from xml
            db.addToDatabase(file);// add all information to database
        }
    }

    /*
     * add last day of channel listings
     */
    public void upDateChannel(String channelName) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, FileNotFoundException, SQLException {
        // set channel name
        channel = channelName;
        // set day to start on
        file = textFile(6);//create text file from xml
        db.addToDatabase(file);// add all information to database
    }

    /*
     * create a text file from xml
     */
    private String textFile(int a) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
        // set the input variable to the desired day, e.g. 1 = tomorrow
        day = a;
        // create url, e.g. http://www.bleb.org/tv/data/listings/1/bbc1.xml
        url = new URL(urlStart + day + "/" + channel + end);
        // create connection to the url
        URLConnection conn = url.openConnection();
        // DOM to customise the xml format
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        // read the xml document to a DOM document
        org.w3c.dom.Document doc = dBuilder.parse(conn.getInputStream());
        doc.getDocumentElement().normalize();
        // extract the date from the XML DOM tree
        String date = doc.getDocumentElement().getAttribute("date");
        date = convertDate(date);
        // create the output text file
        file = channel + "-" + date + ".txt";
        // now write data to text file extracting the neccessary fields
        try (PrintWriter writer = new PrintWriter(file)) {
            NodeList nList = doc.getElementsByTagName("programme");
            // loop until file is complete
            for (int i = 0; i < nList.getLength(); i++) {
                // get node at i
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    // write desired data to file- title, start time, end time and description
                    writer.write(eElement.getElementsByTagName("title").item(0).getTextContent() + System.getProperty("line.separator"));
                    writer.write(eElement.getElementsByTagName("start").item(0).getTextContent() + System.getProperty("line.separator"));
                    writer.write(eElement.getElementsByTagName("end").item(0).getTextContent() + System.getProperty("line.separator"));
                    try {
                        writer.write(eElement.getElementsByTagName("desc").item(0).getTextContent() + System.getProperty("line.separator"));
                    }catch(NullPointerException e){
                        writer.write("." + System.getProperty("line.separator"));
                    }
                }
            }
        }//end of try 
        return file;
    }

    /*
     * remove "/" from date because "/" can not be used in file names
     */
    private String convertDate(String str) {
        str = str.replace("/", "");
        return str;
    }
}
