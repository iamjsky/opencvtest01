package com.soundleader.opencvtest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.widget.TextView;

import edu.washington.cs.touchfreelibrary.sensors.CameraGestureSensor;
import edu.washington.cs.touchfreelibrary.sensors.ClickSensor;
import edu.washington.cs.touchfreelibrary.utilities.LocalOpenCV;
import edu.washington.cs.touchfreelibrary.utilities.PermissionUtility;

public class MainActivity extends AppCompatActivity implements CameraGestureSensor.Listener {
    private final static String TAG = MainActivity.class.getSimpleName();
    TextView tv_gesture;
    Handler handler;
    PowerManager pm = null;
    boolean isScreenOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_gesture  = (TextView)findViewById(R.id.tv_gesture);
        handler = new Handler();
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        isScreenOn = pm.isScreenOn();
        if(isScreenOn){
            if (PermissionUtility.checkCameraPermission(this)) {
                //The third passing in represents a separate click sensor which is not required if you just want the hand motions
                LocalOpenCV loader = new LocalOpenCV(this, this);
            }
        }

    }
    @Override
    public void onResume() {
        super.onResume();
        if(pm == null){
            pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        }else{
            isScreenOn = pm.isScreenOn();
        }


        if(isScreenOn){
            if (PermissionUtility.checkCameraPermission(this)) {
                //The third passing in represents a separate click sensor which is not required if you just want the hand motions
                LocalOpenCV loader = new LocalOpenCV(this, this);
            }
        }
    }


    @Override
    public void onGestureUp(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "onGestureUp");
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_gesture.setText("onGestureUp");
            }
        });


    }

    @Override
    public void onGestureDown(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "onGestureDown");
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_gesture.setText("onGestureDown");
            }
        });

    }

    @Override
    public void onGestureLeft(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "onGestureLeft");
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_gesture.setText("onGestureLeft");
            }
        });



    }

    @Override
    public void onGestureRight(CameraGestureSensor caller, long gestureLength) {
        Log.i(TAG, "onGestureRight");
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv_gesture.setText("onGestureRight");
            }
        });

    }
}