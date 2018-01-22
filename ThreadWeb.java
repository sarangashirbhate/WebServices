package in.bitcode.webservicesdemo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by VISHAL on 04/09/17.
 */

public class ThreadWeb extends AsyncTask<Object, Object, ArrayList<Place>  > {

    private Handler mHandler;

    public ThreadWeb( Handler handler ) {
        mHandler = handler;
    }

    @Override
    protected ArrayList<Place> doInBackground(Object... objects) {

        ArrayList<Place> places = WebUtil.getPlaces();

        if( places != null ) {
            return places;
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Place> places) {
        super.onPostExecute(places);

        Message message = new Message();
        message.obj = places;

        mHandler.sendMessage( message );
    }
}
