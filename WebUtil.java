package in.bitcode.webservicesdemo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by VISHAL on 04/09/17.
 */

public class WebUtil {

    public static void simpleWebComm() {

        try {
            URL url = new URL("http://bitcode.in");
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();

            httpURLConnection.setDoOutput( true );
            OutputStream out = httpURLConnection.getOutputStream();
            out.close();

            httpURLConnection.connect();

            Log.e("tag", "Response Code : " + httpURLConnection.getResponseCode() );
            Log.e("tag", "Response Msg : " + httpURLConnection.getResponseMessage() );
            Log.e("tag", "Req Method : " + httpURLConnection.getRequestMethod() );
            Log.e("tag", "Header fields : " + httpURLConnection.getHeaderFields() );

            InputStream in = httpURLConnection.getInputStream();



            byte [] arr = new byte[1024];
            int count;

            while( ( count = in.read( arr ) ) != -1 ) {
                Log.e("tag", new String( arr, 0, count ) );
            }

            in.close();
            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    public static ArrayList<Place> getPlaces() {
        try {

            URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670,151.1957&radius=500&types=food&name=cruise&key=AIzaSyBSH-NvmHwD4IocKlBxX2yCzAVFZKrGHfs");
            HttpURLConnection httpURLConnection =
                    (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            if(  httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK ) {
                return null;
            }

            InputStream in =
                new BufferedInputStream(
                    httpURLConnection.getInputStream()
                );


            byte [] arr = new byte[2048];
            int count;

            StringBuffer response = new StringBuffer();

            while( ( count = in.read( arr )) != -1 ) {
                response.append( new String( arr, 0, count ) );
            }

            in.close();
            Log.e("tag", response.toString() );

            //to parse json and get java objects

            JSONObject jResponse = new JSONObject( response.toString() );

            if( !jResponse.getString("status").equals("OK") ) {
                return null;
            }

            JSONArray jPlaces = jResponse.getJSONArray("results");

            ArrayList<Place> places = new ArrayList<>();

            for( int i =0; i < jPlaces.length(); i++ ) {

                Place place = new Place();

                JSONObject jPlace = jPlaces.getJSONObject( i );

                place.name = jPlace.getString("name");
                place.iconUrl = jPlace.getString("icon");
                place.rating = jPlace.getDouble("rating");
                place.vicinity = jPlace.getString("vicinity");

                JSONObject jGeo = jPlace.getJSONObject("geometry");
                JSONObject jLoc = jGeo.getJSONObject("location");

                place.lat = jLoc.getDouble("lat");
                place.lng = jLoc.getDouble("lng");

                places.add( place );

            }

            return places;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}










