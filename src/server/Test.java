/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Andy
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        String message = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" >"
                + "<env:Body>"
                + "<Request>"
                + "<RequestType>Listing</RequestType>"
                + "<Username>andy</Username>"
                + "<Channel>bbc1</Channel>"
                + "</Request>"
                + "</env:Body>"
                + "</env:Envelope>";
        
        Request request = new Request(message);
        
        System.out.println(request.getUser());
        System.out.println(request.getRequest());
        System.out.println(request.getArg3());
    }
}
