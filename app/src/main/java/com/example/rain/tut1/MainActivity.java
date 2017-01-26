package com.example.rain.tut1;

import android.hardware.SensorEvent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.View;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private TextView xText, yText, zText;
    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        //create sensor manager

        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_FASTEST);

        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);

        final MediaPlayer musicPlayer = MediaPlayer.create(this, R.raw.bib);
        Button playSound = (Button) this.findViewById(R.id.play_sound);
        playSound.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                musicPlayer.start();
            }
        });

        Button stopSound = (Button) this.findViewById(R.id.stop_play);
        stopSound.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                musicPlayer.stop();
            }
        });



    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        xText.setText("X: " + sensorEvent.values[0]);
        yText.setText("Y: " + sensorEvent.values[1]);
        zText.setText("Z: " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // no use
    }
}
