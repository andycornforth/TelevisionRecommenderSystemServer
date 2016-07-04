/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Andy
 */
public class Request {
    
    String[] request;
    String delimeters = "<";

    public Request(String str) {
        request = str.split(delimeters);
    }

    /**
     * Check that the string received from the client is a valid SOAP request
     * @param request
     * @return 
     */
    public boolean checkRequest() {
        boolean result = true;
        return result;
    }
    
    /**
     * Return the User who sent the request
     * @param request
     * @return 
     */
    public String getUser(){
        String[] temp = request[7].split(">");
        return temp[1];
    }
    
    /**
     * Return the type of request; getRecommendation, getListings=channel, 
     * getShowInformation=show
     * 
     * @param request
     * @return 
     */
    public String getRequest(){
        String[] temp = request[5].split(">");
        return temp[1];
    }

    public String getArg3(){
        String[] temp = request[9].split(">");
        return temp[1];
    }
    
    public String getArg4(){
        String[] temp = request[11].split(">");
        return temp[1];
    }
    
    public String getArg5(){
        String[] temp = request[13].split(">");
        return temp[1];
    }
    
    public String getArg6(){
        String[] temp = request[15].split(">");
        return temp[1];
    }
}
