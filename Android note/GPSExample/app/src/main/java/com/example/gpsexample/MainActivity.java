package com.example.gpsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button myButton;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String myPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker myGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            if (ActivityCompat.checkSelfPermission(this, myPermission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{myPermission}, REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        myButton = (Button) findViewById(R.id.button);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myGPS = new GPSTracker(MainActivity.this);
                if (myGPS.canGetLocation()) {
                    double latitude = myGPS.getLatitude();
                    double longitude = myGPS.getLongitude();
                    Toast.makeText(getApplicationContext(), "당신의 위치는 경도: " + latitude + " " + "위도: " + longitude, Toast.LENGTH_LONG).show();
                } else {
                    myGPS.showSettingAlert();
                }
            }
        });
    }
}