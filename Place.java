package in.bitcode.webservicesdemo;

/**
 * Created by VISHAL on 07/09/17.
 */

public class Place {

    public String name;
    public double lat, lng;
    public String vicinity;
    public double rating;
    public String iconUrl;

    @Override
    public String toString() {
        return name + "\n" + vicinity + "\n" + lat + " , " + lng  + "\n" + rating + "\n" + iconUrl + "\n\n";
    }
}
