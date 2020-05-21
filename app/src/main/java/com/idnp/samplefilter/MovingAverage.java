package com.idnp.samplefilter;

public class MovingAverage {
    private float circularBuffer[];
    private float avg;
    private int circularIndex;
    private int count;
    public MovingAverage(int k)
    {
        circularBuffer = new float[k];
        count = 0;
        circularIndex = 0;
        avg = 0;
    }
    /* Get the current moving average. */
    public float getValue()
    {
        return avg;
    }

    public void pushValue(float x)
    {
        if (count++ == 0)
        {
            //initialization of the array
            primeBuffer(x);
        }
        float lastValue = circularBuffer[circularIndex];
        avg = avg + (x - lastValue) / circularBuffer.length;
        circularBuffer[circularIndex] = x;
        circularIndex = nextIndex(circularIndex);
    }
    public long getCount()
    {
        return count;
    }

    //each element of array(window) is assigned val, except the last element of the array
    //the avg is the same as val
    private void primeBuffer(float val)
    {
        for (int i = 0; i < circularBuffer.length; ++i)
        {
            circularBuffer[i] = val;
        }
        avg = val;
    }
    private int nextIndex(int curIndex)
    {
        if (curIndex + 1 >= circularBuffer.length)
        {
            return 0;
        }
        return curIndex + 1;
    }
}
