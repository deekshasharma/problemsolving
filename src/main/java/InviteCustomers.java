import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * We have some customer records in a text file (customers.json) -- one customer per line, JSON-encoded.
 * We want to invite any customer within 100km of our Dublin office (GPS coordinates 53.3381985, -6.2592576)
 * for some food and drinks on us. Write a program that will read the full list of customers and output the
 * names and user ids of matching customers (within 100km), sorted by user id (ascending).

 You can use the first formula from this Wikipedia article to calculate distance:
 https://en.wikipedia.org/wiki/Great-circle_distance -- don't forget, you'll need to convert degrees to radians.
 Your program should be fully tested too.

 Customer list is available here: https://gist.github.com/brianw/19896c50afa89ad4dec3
 */

public class InviteCustomers {
    private static final int EARTH_RADIUS= 6371;
    private static final double DUBLIN_LAT= Math.toRadians(53.3381985);
    private static final double DUBLIN_LONG= Math.toRadians(-6.2592576);
    private static final int WITHIN_DISTANCE = 100;


    public static void main(String[] args) throws IOException, ParseException {
        String path = "/Users/deeksha/IdeaProjects/problemsolving/src/main/resources/customer.json";
        List<Customer> selected = selectCustomers(path);
        for (Customer c: selected){
            System.out.println("Name:"+c.getName()+" , "+" userId:"+c.getUserId());
        }
    }

    /**
     * Select the customers that are within 100 km from Dublin Office
     * @return List of selected customers
     * @throws IOException
     * @throws ParseException
     */
     static List<Customer> selectCustomers(String path) throws IOException, ParseException {
        List<Customer> selectedCustomers = new ArrayList<Customer>();
        JSONParser jsonParser = new JSONParser();
        JSONArray customers = (JSONArray)jsonParser.parse(new FileReader(path));
        for (Object object: customers){
            JSONObject customer = (JSONObject) object;
            double latitude = new Double(customer.get("latitude").toString());
            double longitude = new Double(customer.get("longitude").toString());

            if (calculateDistance(Math.toRadians(latitude),Math.toRadians(longitude)) <= WITHIN_DISTANCE){
                String name = customer.get("name").toString();
                int userId = Integer.parseInt(customer.get("user_id").toString());
                selectedCustomers.add(new Customer(name,userId));
            }
        }
         Collections.sort(selectedCustomers);
        return selectedCustomers;
    }

    /**
     * Calculate distance between the Dublin Office and the customer's location
     * @param latitudeInRadian customer's latitude in radian
     * @param longitudeInRadian customer's longitude in radian
     * @return double value giving the distance
     */
     static double calculateDistance(double latitudeInRadian,double longitudeInRadian){
        double deltaLongitude = DUBLIN_LONG - longitudeInRadian;
        double centralAngle = Math.acos((Math.sin(DUBLIN_LAT) * Math.sin(latitudeInRadian)) +
                (Math.cos(DUBLIN_LAT) * Math.cos(latitudeInRadian) * Math.cos(deltaLongitude)));
        return EARTH_RADIUS*centralAngle;
    }

}
