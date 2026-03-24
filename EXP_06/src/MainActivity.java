package com.example.gpslocation;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private TextView txtLat, txtLong;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLat = findViewById(R.id.txtLat);
        txtLong = findViewById(R.id.txtLong);
        Button btnGetLocation = findViewById(R.id.btnGetLocation);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                } else {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, MainActivity.this);
                    Toast.makeText(MainActivity.this, "Searching for GPS...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        txtLat.setText("Latitude: " + location.getLatitude());
        txtLong.setText("Longitude: " + location.getLongitude());
    }

    @Override public void onStatusChanged(String provider, int status, Bundle extras) {}
    @Override public void onProviderEnabled(String provider) {}
    @Override public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Please Enable GPS", Toast.LENGTH_SHORT).show();
    }
}