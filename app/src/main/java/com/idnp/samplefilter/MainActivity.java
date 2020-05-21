package com.idnp.samplefilter;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSensor();
    }

    private void initSensor() {
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        AcceSensorEventListener acceSensorEventListener = new AcceSensorEventListener();
        sensorManager.registerListener(
                acceSensorEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);

    }
}
