package in.bitcode.webservicesdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Place> mListPlaces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListPlaces = new ArrayList<>();

        new ThreadWeb( new PlacesHandler() )
                .execute( (Object[]) null);

    }

    class PlacesHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if( msg.obj != null ) {

                mListPlaces.addAll( (ArrayList<Place>) msg.obj );

            }
        }
    }
}
