/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Andy
 */
public class Response {
    
    private final static String SOAP_START = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" >"
                + "<env:Body><Response><ResponseType>";
    private final static String SOAP_MIDDLE = "</ResponseBody><Username>";
    private final static String SOAP_END = "</Username></Response></env:Body></env:Envelope>";
    String username, responseBody, type;
    int responseType;
    
    public Response(String message, String user, int type){
        responseBody = message;
        username = user;
        responseType = type;
    }
    
    public String createResponse(){
        type = responseType(responseType);
        return SOAP_START + type + "</ResponseType><ResponseBody>" + 
                responseBody + SOAP_MIDDLE + username + SOAP_END;
    }
    
    private String responseType(int i){
        String type;
        switch (i) {
                case 1:
                    type = "login";
                    break;
                case 2:
                    type = "signup";
                    break;
                case 3:
                    type = "recommendation";
                    break;
                case 4:
                    type = "listing";
                    break;
                case 5:
                    type = "show";
                    break;
                case 6:
                    type = "rate";
                    break;
                case 7:
                    type = "profile";
                    break;
                case 8:
                    type = "ratings";
                    break;
                case 9:
                    type = "update";
                    break;
                case 10:
                    type = "episode";
                    break;
                default:
                    type = "error";
            }
        return type;
    }
}
