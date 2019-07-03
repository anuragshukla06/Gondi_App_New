package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import flite.*;



public class Dashboard extends AppCompatActivity {


    public static final int WRITE_REQUEST_CODE = 1;

    // Data variables
    private ArrayList<Voice> mVoices;
    public static Dashboard dashboard_activity;

    private TextToSpeech mTts;
    private int mSelectedVoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dashboard_activity = this;

        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.INTERNET};
        requestPermissions(permissions, WRITE_REQUEST_CODE);

        CardView mediaSwaraCardView = findViewById(R.id.mediaSwaraCardView);
        mediaSwaraCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MediaSwara.class);
                startActivity(intent);
            }
        });

    }

    public void initVoices() {
        ArrayList<Voice> allVoices = CheckVoiceData.getVoices();
        mVoices = new ArrayList<Voice>();
        for(Voice vox:allVoices) {
            if (vox.isAvailable()) {
                mVoices.add(vox);
                System.out.println(vox.getVariant());
            }
        }

        if (mVoices.isEmpty()) {
            // We can't demo anything if there are no voices installed.
            /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Flite voices not installed. Please add voices in order to run the demo");
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    finish();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();*/
            Snackbar.make(findViewById(R.id.dashboard_layout), "Flite voices not installed. Please add voices in order to run the demo", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        else {
            // Initialize the TTS
            if (android.os.Build.VERSION.SDK_INT >=
                    android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                mTts = new TextToSpeech(dashboard_activity, dashboard_activity, "edu.cmu.cs.speech.tts.flite");
            }
            else {
                mTts = new TextToSpeech(dashboard_activity, dashboard_activity);
            }
            mSelectedVoice = 0;

        }
    }

}
