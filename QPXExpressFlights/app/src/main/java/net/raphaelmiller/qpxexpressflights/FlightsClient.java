package net.raphaelmiller.qpxexpressflights;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.qpxExpress.QPXExpress;
import com.google.api.services.qpxExpress.QPXExpressRequestInitializer;
import com.google.api.services.qpxExpress.model.AircraftData;
import com.google.api.services.qpxExpress.model.AirportData;
import com.google.api.services.qpxExpress.model.CarrierData;
import com.google.api.services.qpxExpress.model.CityData;
import com.google.api.services.qpxExpress.model.FlightInfo;
import com.google.api.services.qpxExpress.model.LegInfo;
import com.google.api.services.qpxExpress.model.PassengerCounts;
import com.google.api.services.qpxExpress.model.PricingInfo;
import com.google.api.services.qpxExpress.model.SegmentInfo;
import com.google.api.services.qpxExpress.model.SliceInfo;
import com.google.api.services.qpxExpress.model.SliceInput;
import com.google.api.services.qpxExpress.model.TripOption;
import com.google.api.services.qpxExpress.model.TripOptionsRequest;
import com.google.api.services.qpxExpress.model.TripsSearchRequest;
import com.google.api.services.qpxExpress.model.TripsSearchResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static com.google.api.client.util.ByteStreams.copy;

/**
 * Created by raphael on 9/18/17.
 */

public class FlightsClient {

    //QPX Express API defined lists
    public List<CityData> tripData = null;
    public List<AircraftData> aircraftData = null;
    public List<CarrierData> carrierData = null;
    public List<AirportData> airportData = null;
    public List<TripOption> tripResults = null;

    public List<CityData> outboundCityData;
    public List<AircraftData> outBoundAircraftData;
    public List<AirportData> outboundAirportData;
    public List<CarrierData> outBoundCarrierData;

    public List<CityData> inboundCityData;
    public List<AircraftData> inboundAircraftData;
    public List<AirportData> inboundAirportData;
    public List<CarrierData> inboundCarrierData;

    public TripOption outboundFlightChoice = null;
    public TripOption returnFlightChoice = null;

    private static final int HR_CONVERT = 60;

    //Constants for google developers console
    private static final String APPLICATION_NAME = "AndroidQPX";
    private static final String API_KEY = "AIzaSyDYCt-gXeVYNsrAfFk3J-OuJyEfhhLHxpg";
    private static final String AREO_API_KEY = "c429fa56ff4b7f3eca49a6fbaec2fcc3";

    private final String USER_AGENT = "Mozilla/5.0";

    //Global Instance of HTTP Transport
    private static HttpTransport httpTransport;

    //global instance of JSON factory
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    //user manipulated objects
    private String destination;
    private String origin;
    private String departure;
    private String returning;

    //Main Class Constructor
    public FlightsClient(){

    }

    public FlightsClient(String destination, String origin, String departure, String returning) {
        this.destination = destination;
        this.origin = origin;
        this.departure = departure;
        this.returning = returning;
    }

    public List<TripOption> createTripOption() {

        try {

            //send generic http transport
            httpTransport = AndroidHttp.newCompatibleTransport();

            int passengerNo = 1;

            //set number of passengers
            PassengerCounts passengers = new PassengerCounts();
            passengers.setAdultCount(passengerNo);

            //create slices
            List<SliceInput> slices = new ArrayList<>();

            SliceInput slice = new SliceInput();
            slice.setOrigin(origin);
            slice.setDestination(destination);
            slice.setDate(departure);

            //create tripOptions request
            TripOptionsRequest tripOptionsRequest = new TripOptionsRequest();
            tripOptionsRequest.setSolutions(10);
            tripOptionsRequest.setSaleCountry("US");
            tripOptionsRequest.setPassengers(passengers);
            tripOptionsRequest.setSlice(slices);

            TripsSearchRequest searchRequest = new TripsSearchRequest();
            searchRequest.setRequest(tripOptionsRequest);
            QPXExpress.Builder qpxBuilder = new QPXExpress.Builder(httpTransport, JSON_FACTORY, null);
            qpxBuilder.setApplicationName(APPLICATION_NAME);
            QPXExpress qpxExpress = qpxBuilder.setGoogleClientRequestInitializer(new QPXExpressRequestInitializer(API_KEY)).build();
            System.out.println("sending request to QPX Express");


            TripsSearchResponse list = qpxExpress.trips().search(searchRequest).execute();

            //get response
            tripResults = list.getTrips().getTripOption();
            tripData = list.getTrips().getData().getCity();
            aircraftData = list.getTrips().getData().getAircraft();
            carrierData = list.getTrips().getData().getCarrier();
            airportData = list.getTrips().getData().getAirport();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return tripResults;
    }
}



