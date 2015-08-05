package com.cetur.platinum;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.cetur.model.HttpConnection;
import com.cetur.model.PathJSONParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DemandDetailActivity extends ActionBarActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected Location mCurrentLocation;
    private GoogleMap googleMap;
    private LatLng userPosition;
    private TextView demandDetailActivityNameTV, demandDetailActivityDateTV, demandDetailActivityFromTV,
            demandDetailActivityToTV, demandDetailActivityPriceTV, demandDetailActivityAcceptTV, demandDetailActivityDenyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_detail);
        setToolbar();
        initViews();
        buildGoogleApiClient();
        mGoogleApiClient.connect();


    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar.setTitle("");
        TextView toolbarHeader = (TextView) toolbar.findViewById(R.id.toolbarMainHeaderTV);
        toolbarHeader.setText("Atatür Havalimanı / Sarıyer");
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");
        toolbarHeader.setTypeface(face);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void initViews() {

        demandDetailActivityNameTV = (TextView) findViewById(R.id.demandDetailActivityNameTV);
        demandDetailActivityDateTV = (TextView) findViewById(R.id.demandDetailActivityDateTV);
        demandDetailActivityFromTV = (TextView) findViewById(R.id.demandDetailActivityFromTV);
        demandDetailActivityToTV = (TextView) findViewById(R.id.demandDetailActivityToTV);
        demandDetailActivityPriceTV = (TextView) findViewById(R.id.demandDetailActivityPriceTV);
        demandDetailActivityAcceptTV = (TextView) findViewById(R.id.demandDetailActivityAcceptTV);
        demandDetailActivityDenyTV = (TextView) findViewById(R.id.demandDetailActivityDenyTV);

        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");

        demandDetailActivityNameTV.setTypeface(face);
        demandDetailActivityDateTV.setTypeface(face);
        demandDetailActivityFromTV.setTypeface(face);
        demandDetailActivityToTV.setTypeface(face);
        demandDetailActivityPriceTV.setTypeface(face);
        demandDetailActivityAcceptTV.setTypeface(face);
        demandDetailActivityDenyTV.setTypeface(face);


        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.googleMapView)).getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(41.025232, 29.017080), 6.0f));

    }

    private void buildGoogleApiClient() {
        Log.i("XXXXXXXXXXXXXX", "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    protected void startLocationUpdates() {

        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demand_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("XXXXXXXXXX", "Connected to GoogleApiClient");
        if (mCurrentLocation == null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        }
        if (mCurrentLocation == null)
            startLocationUpdates();
        else {
            userPosition = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());

        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        userPosition = new LatLng(location.getLatitude(), location.getLongitude());

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        startLocationUpdates();
    }

    private void drawRoute(LatLng userLocation, LatLng hospitalLocaion) {

        String url = getMapsApiDirectionsUrl(userLocation, hospitalLocaion);
        ReadTask downloadTask = new ReadTask();
        downloadTask.execute(url);
        // fixZoom();
    }

    private String getMapsApiDirectionsUrl(LatLng userLotcaion, LatLng hospitalLocation) {
        String userLoc = String.valueOf(userLotcaion.latitude) + "," + String.valueOf(userLotcaion.longitude);
        String hospLoc = String.valueOf(hospitalLocation.latitude) + "," + String.valueOf(hospitalLocation.longitude);
        String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + userLoc + "&destination=" + hospLoc + "&sensor=false";
        return url;
    }


    private class ReadTask extends AsyncTask<String, Void, String> {

        final ProgressDialog progressDialog = new ProgressDialog(DemandDetailActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setCancelable(false);
            progressDialog.setTitle("");
            progressDialog.setMessage(getString(R.string.loading));
            progressDialog.show();
        }


        @Override
        protected String doInBackground(String... url) {
            String data = "";
            try {
                HttpConnection http = new HttpConnection();
                data = http.readUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.hide();
            new ParserTask().execute(result);
        }
    }

    private class ParserTask extends
            AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(
                String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                PathJSONParser parser = new PathJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }


        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> routes) {
            ArrayList<LatLng> points = null;
            PolylineOptions polyLineOptions = null;

            // traversing through routes
            for (int i = 0; i < routes.size(); i++) {
                points = new ArrayList<LatLng>();
                polyLineOptions = new PolylineOptions();
                List<HashMap<String, String>> path = routes.get(i);
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
                    points.add(position);
                }
                polyLineOptions.addAll(points);
                polyLineOptions.width(2);
                polyLineOptions.color(Color.BLUE);
            }
            googleMap.addPolyline(polyLineOptions);
        }
    }
}
