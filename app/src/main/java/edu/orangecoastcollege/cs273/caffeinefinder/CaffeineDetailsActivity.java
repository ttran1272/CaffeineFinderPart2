package edu.orangecoastcollege.cs273.caffeinefinder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CaffeineDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private CaffeineLocation mSelectedCaffeineLocation;
    //TODO: Add member variable for Location mMyLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caffeine_details);

        TextView nameTextView = (TextView) findViewById(R.id.nameTextView);
        TextView addressTextView = (TextView) findViewById(R.id.addressTextView);
        TextView phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        TextView positionTextView = (TextView) findViewById(R.id.positionTextView);
        mSelectedCaffeineLocation = getIntent().getExtras().getParcelable("SelectedLocation");
        nameTextView.setText(mSelectedCaffeineLocation.getName());
        addressTextView.setText(mSelectedCaffeineLocation.getFullAddress());
        phoneTextView.setText(mSelectedCaffeineLocation.getPhone());
        positionTextView.setText(mSelectedCaffeineLocation.getFormattedLatLng());

        //TODO: Get the parcelable MyLocation from the intent and assign it to the member variable mMyLocation

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.caffeineDetailsMapFragment);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng coordinate = new LatLng(mSelectedCaffeineLocation.getLatitude(), mSelectedCaffeineLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(coordinate).title(mSelectedCaffeineLocation.getName()));

        //TODO: Add another LatLng coordinate named myCoordinate based off mMyLocation.
        //TODO: Add a custom marker at myCoordinate
        //TODO: Move the camera position to target myCoordinate

        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinate).zoom(18.0f).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        mMap.moveCamera(cameraUpdate);
    }
}
