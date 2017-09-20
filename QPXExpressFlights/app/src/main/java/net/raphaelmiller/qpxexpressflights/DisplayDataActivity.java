package net.raphaelmiller.qpxexpressflights;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.json.JsonParser;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.gson.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static com.google.api.client.util.ByteStreams.copy;

public class DisplayDataActivity extends AppCompatActivity {

    private static final String API_KEY = "AIzaSyDYCt-gXeVYNsrAfFk3J-OuJyEfhhLHxpg";


    private List<TripOption> tripResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_data_acitivty);

        Bundle bundle = getIntent().getExtras();

        //System.out.println(bundle.get("origin"));
        String destination = bundle.getString("destination");
        String origin = bundle.getString("origin");
        String departure = bundle.getString("departure");
        String returning = bundle.getString("returning");

        TextView resultsView = (TextView) findViewById(R.id.resultsView);

        //print to second activity
        resultsView.append(destination);
        resultsView.append(origin);
        resultsView.append(departure);
        resultsView.append(returning);

        FlightsClient fl = new FlightsClient(destination, origin, departure, returning);

        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(this).addApi(Drive.API).build();

        googleApiClient.connect();

        List<TripOption> tripOptions = fl.createTripOption();
        if(tripOptions == null){
            System.out.println("nothing here");
        }

    }


}
