package com.idnp.samplefilter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

public class AcceSensorEventListener implements SensorEventListener {
    private static final String TAG = "AcceSensorEventListener";
    private float x, y, z;
    private float mLowPass1, mLowPass2;
    private float mLowSMA1, mLowSMA2, mLowSMA3;
    private float a1 = 0.1f;
    private float a2 = 0.2f;
    private MovingAverage movingAverage1;
    private MovingAverage movingAverage2;
    private MovingAverage movingAverage3;

    public AcceSensorEventListener() {
        movingAverage1 = new MovingAverage(4);
        movingAverage2 = new MovingAverage(20);
        movingAverage3 = new MovingAverage(100);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        x = sensorEvent.values[0];
        y = sensorEvent.values[1];
        z = sensorEvent.values[2];

        mLowPass1 = lowPass1(x, mLowPass1);
        mLowPass2 = lowPass2(x, mLowPass2);

        movingAverage1.pushValue(x);
        movingAverage2.pushValue(x);
        movingAverage3.pushValue(x);

        mLowSMA1=movingAverage1.getValue();
        mLowSMA2=movingAverage2.getValue();
        mLowSMA3=movingAverage3.getValue();

        Log.d(TAG, x+"," + mLowPass1+","+mLowPass2+","+mLowSMA1+","+mLowSMA2+","+mLowSMA3);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    // simple low-pass filter
    // percentage of sharing
    private float lowPass1(float current, float last) {
        return last * (1.0f - a1) + current * a1;//80% 0.8, 20% last
    }

    private float lowPass2(float current, float last) {
        return last * (1.0f - a2) + current * a2;
    }
}
