package com.example.cs422layne;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DestinationInputActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private AutoCompleteTextView startDestination;
    private AutoCompleteTextView endDestination;
    private PlaceAutocompleteAdapter placeAutocompleteAdapter;
    private View nextButton;
    //private GoogleApiClient googleApiClient;

    private static final String TAG = DestinationInputActivity.class.getSimpleName();
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng( -40,-168), new LatLng(71,136));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.destination_input_layout);

        nextButton = findViewById(R.id.next_bike);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DestinationInputActivity.this, RecommendedRoutes.class));
            }
        });

        /*
        googleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this,this)
                .build();
        */

        startDestination = (AutoCompleteTextView) findViewById(R.id.start_dest_input);
        endDestination = (AutoCompleteTextView) findViewById(R.id.end_dest_input);
        placeAutocompleteAdapter = new PlaceAutocompleteAdapter(this, Places.getGeoDataClient(this), LAT_LNG_BOUNDS, null);

        startDestination.setAdapter(placeAutocompleteAdapter);
        startDestination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    geoLocate(v.getText().toString());
                }
                return false;
            }
        });

        endDestination.setAdapter(placeAutocompleteAdapter);
        endDestination.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    geoLocate(v.getText().toString());
                }
                return false;
            }
        });

    }

    private void geoLocate(String search){
        Geocoder geocoder = new Geocoder(DestinationInputActivity.this);
        List<Address> list = new ArrayList<>();
        try{
            list = geocoder.getFromLocationName(search,1);
        }
        catch (IOException e){
            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
        }

        if(list.size() > 0){
            Address address = list.get(0);

            Log.i(TAG, "geoLocate: found a location: " + address.toString());
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
