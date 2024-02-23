package DSA.Hashing;

import java.util.HashMap;
import java.util.Scanner;

public class FindItineraryTask
{
    public static void FindItinerary(HashMap<String, String> routes, String source)
    {
        System.out.print(source);
        while(routes.containsKey(source))
        {
            String dest = routes.get(source);
            System.out.print(" -> " + dest);
            source = dest;
        }
        return;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your source: ");
        String source = sc.nextLine();
        sc.close();

        HashMap<String, String> routes = new HashMap<>();
        routes.put("Chennai", "Bengaluru");
        routes.put("Mumbai", "Delhi");
        routes.put("Goa", "Chennai");
        routes.put("Delhi", "Goa");


        FindItinerary(routes, source);

    }
}
