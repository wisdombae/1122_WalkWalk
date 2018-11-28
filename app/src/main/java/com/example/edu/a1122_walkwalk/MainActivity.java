package com.example.edu.a1122_walkwalk;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SeekBar seekBar;
    TextView textViewThreshold, textViewStep;
    TextView textViewX, textViewY, textViewZ;
    int threshold, step;
    float previousX, previousY, previousZ, currentX, currentY, currentZ;
    float acceleration;

    SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textViewStep = findViewById(R.id.textViewStep);

        textViewX = findViewById(R.id.textView_X);
        textViewY = findViewById(R.id.textView_Y);
        textViewZ = findViewById(R.id.textView_Z);

        threshold = 3;
        previousX = currentX = previousY = currentY = previousZ = currentZ = step = 0;
        acceleration = 0.0f;
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];
        currentX = x;
        currentY = y;
        currentZ = z;

        if(Math.abs(currentY - previousY) > threshold) {
            step++;
            textViewStep.setText(String.valueOf(step));
        }

        textViewX.setText(String.valueOf(x));
        textViewY.setText(String.valueOf(y));
        textViewZ.setText(String.valueOf(z));
        previousX = x;
        previousY = y;
        previousZ = z;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
