/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package liveData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Andy
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, ParserConfigurationException, SAXException, FileNotFoundException, SQLException {
        
        // create a new instance of the live data facade class
        EngineLD test = new EngineLD();
        // update the channel listings for bbc1
        test.updateChannel("bbc1");
        
       
        
    }
}
