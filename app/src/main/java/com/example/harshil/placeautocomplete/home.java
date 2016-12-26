package com.example.harshil.placeautocomplete;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class home extends AppCompatActivity {
    private EditText location;
    private TextView lat, lon;
    private String use;
    private RadioButton loc, pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loc = (RadioButton) findViewById(R.id.loc);
        pick = (RadioButton) findViewById(R.id.pick);
        loc.performClick();

        location = (EditText) findViewById(R.id.location);
        location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (use != null && use.equals("Location")) {
                    startActivity(new Intent(home.this, location.class));
                } else if (use != null && use.equals("Picker")) {
                    startActivity(new Intent(home.this, picker.class));
                } else {
                    Toast.makeText(home.this, "Please select a method.", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        lat = (TextView) findViewById(R.id.lat);
        lon = (TextView) findViewById(R.id.lon);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getStringExtra("address") != null) {
            location.setText(getIntent().getStringExtra("address"));
            lat.setText("Latitude: " + getIntent().getStringExtra("lat"));
            lon.setText("Longitude: " + getIntent().getStringExtra("lon"));
        }
    }

    public void onClick(View view) {
        RadioButton radioButton = (RadioButton) view;
        if (Integer.parseInt(radioButton.getTag().toString()) == 1) {
            use = "Location";
        } else if (Integer.parseInt(radioButton.getTag().toString()) == 2) {
            use = "Picker";
        }
    }
}
