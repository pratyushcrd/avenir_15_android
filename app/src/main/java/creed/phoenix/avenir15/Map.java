package creed.phoenix.avenir15;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

public class Map extends ActionBarActivity implements GoogleMap.OnMapClickListener {

    private GoogleMap map;
    LatLng nowhere = new LatLng(22.475806, 88.414739);
    static final CameraPosition SYDNEY = new CameraPosition.Builder()
            .target(new LatLng(22.475657, 88.41468)).zoom(15.5f).bearing(0)
            .tilt(25).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);
        try {
            if (map == null)
                map = ((SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map)).getMap();
            LatLng sydney = new LatLng(22.475806, 88.414739);

            map.setMyLocationEnabled(true);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(nowhere, 13));

            map.addMarker(
                    new MarkerOptions().rotation(5).title("Avenir")
                            .snippet("NSEC ,Kolkata").position(sydney))
                    .setFlat(true);
            map.setOnMapClickListener(this);
        } catch (OutOfMemoryError e) {

        }
    }

    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {

        map.animateCamera(update, Math.max(5000, 1), callback);

    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        changeCamera(CameraUpdateFactory.newCameraPosition(SYDNEY),
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(getBaseContext(), "Welcome to NSEC !!",
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }
}