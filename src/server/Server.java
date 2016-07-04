package server;

import core.Control;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {

    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static InputStreamReader inputStreamReader;
    private static BufferedReader bufferedReader;
    private static BufferedWriter out;
    private static String message, address, user, requestType;
    private Control control;
    private int recommendationNumber = 0;

    public Server() throws SQLException, Exception {
        control = new Control();
    }

    private String run(String message) throws SQLException {
        String response = null;
        // Create a request from the client message
        Request request = new Request(message);

        // check that the request is valid
        if (request.checkRequest()) {
            //get request type
            requestType = request.getRequest();
            int i;
            // do request
            switch (requestType) {
                case "login":
                    i = 1;
                    break;
                case "signup":
                    i = 2;
                    break;
                case "recommendation":
                    i = 3;
                    break;
                case "listing":
                    i = 4;
                    break;
                case "show":
                    i = 5;
                    break;
                case "rate":
                    i = 6;
                    break;
                case "profile":
                    i = 7;
                    break;
                case "ratings":
                    i = 8;
                    break;
                case "update":
                    i = 9;
                    break;
                case "episode":
                    i = 10;
                    break;
                default:
                    i = -1;
            }
            response = createResponse(request, i);
        }
        return response;
    }

    private String createResponse(Request request, int i) throws SQLException {
        String result = null, argument3, argument4, argument5;
        int age;
        char gender;
        user = request.getUser();
        switch (i) {
            // log in
            case 1:
                // get the password
                argument3 = request.getArg3();
                result = "<Result>" + control.LOG_IN(user, argument3) + "</Result>";
                break;
            // sign up
            case 2:
                // get the password
                argument3 = request.getArg3();
                // get age
                age = Integer.parseInt(request.getArg4());
                // get gender
                gender = request.getArg5().charAt(0);
                result = "<Result>" + control.addUser(user, argument3, age, gender) + "</Result>";
                break;
            // get a recommendation
            case 3:
                result = control.getRecommendation(user, recommendationNumber);
                recommendationNumber++;
                break;
            case 4:
                // view a channel schedule for a date
                argument3 = request.getArg3();
                argument4 = request.getArg4();
                result = control.getChannelSchedule(argument3, argument4);
                break;
            case 5:
                // view a shows description
                argument3 = request.getArg3();
                result = control.getShowDescription(argument3);
                break;
            case 6:
                argument3 = request.getArg3();
                argument4 = request.getArg4();
                int rating = Integer.parseInt(argument4);
                result = "" + control.addRating(user, argument3, rating);
                break;
            case 7:
                result = control.getUserDetails(user);
                break;
            case 8:
                result = control.getUsersRatings(user, true);
                break;
            case 9:
                // get the password
                argument3 = request.getArg3();
                // get age
                age = Integer.parseInt(request.getArg4());
                // get gender
                gender = request.getArg5().charAt(0);
                result = "<Result>" + control.updateUser(user, argument3, age, gender) + "</Result>";
                break;
            case 10:
                // get the show
                argument3 = request.getArg3();
                // get channel
                argument4 = request.getArg4();
                //get date
                argument5 = request.getArg5();
                // get start time
                String argument6 = request.getArg6();

                result = "<Result>" + control.getEpisode(argument3, argument4, argument5, argument6) + "</Result>";
                break;
        }

        Response response = new Response(result, user, i);
        result = response.createResponse();
        return result;
    }

    public static void main(String[] args) throws SQLException, Exception {

        Server server = new Server();
        System.out.println("Loaded system");
        int port = 21111;

        try {
            serverSocket = new ServerSocket(port);  //Server socket
        } catch (IOException e) {
            System.out.println("Could not listen on port: " + port);
        }

        try {
            address = (InetAddress.getLocalHost()).toString();
        } catch (Exception e) {
            address = "can't obtain address";
        }

        System.out.println("Server started.\nThis address:\t" + address
                + "\nListening to the port:\t" + port);

        while (true) {
            try {
                System.out.println("\nWaiting for client application....");
                clientSocket = serverSocket.accept();   //accept the client connection
                System.out.println("\nConnected to client");
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader); //get the client message
                out = new BufferedWriter(new OutputStreamWriter(
                        clientSocket.getOutputStream()));
                // get request message from the client
                message = bufferedReader.readLine();
                System.out.println("\nClient message: " + message);

                // deal with the request
                message = server.run(message);

                //send back to client
                out.write(message + "\n");
                out.flush();
                System.out.println("Server message: " + message);
                System.out.println("\nResponse sent to client.");
                inputStreamReader.close();

            } catch (IOException ex) {
                System.out.println("Error reading message");
            }
        }

    }
}