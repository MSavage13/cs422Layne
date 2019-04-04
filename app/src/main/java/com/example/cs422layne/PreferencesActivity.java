package com.example.cs422layne;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.Arrays;

public class PreferencesActivity extends AppCompatActivity {

    private CheckBox lessTrafficCB;
    private CheckBox protectCB;
    private CheckBox wellLitCB;
    private CheckBox hazCB;
    private CheckBox fewerTurnsCB;
    private CheckBox fewerIntCB;
    private CheckBox fewerStopsCB;
    private Button saveButton;

    private ArrayList<CheckBox> prefList;
    private boolean[] checkedVals;

    private static final String TAG = PreferencesActivity.class.getSimpleName();
    private final String checkedValsKey = "CHECKED_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        lessTrafficCB = (CheckBox) findViewById(R.id.PrefLessTrafficCB);
        protectCB = (CheckBox) findViewById(R.id.PrefProtectCB);
        wellLitCB = (CheckBox) findViewById(R.id.PrefWellLitCB);
        hazCB = (CheckBox) findViewById(R.id.PrefHazCB);
        fewerTurnsCB = (CheckBox) findViewById(R.id.PrefFewerTurnsCB);
        fewerIntCB = (CheckBox) findViewById(R.id.PrefFewerIntCB);
        fewerStopsCB = (CheckBox) findViewById(R.id.PrefFewerStopsCB);
        saveButton = (Button) findViewById(R.id.PrefSaveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < checkedVals.length; i++){
                    checkedVals[i] = prefList.get(i).isChecked();
                }
                startActivity(new Intent(PreferencesActivity.this,MapsActivity.class));
            }
        });

        prefList = new ArrayList<CheckBox>(
                Arrays.asList(lessTrafficCB,
                        protectCB,
                        wellLitCB,
                        hazCB,
                        fewerTurnsCB,
                        fewerIntCB,
                        fewerStopsCB
                ));

        Log.i(TAG, "onCreate: Got here");

        if (savedInstanceState != null) {
            Log.i(TAG, "onCreate: There is a saved instance");
            checkedVals = savedInstanceState.getBooleanArray(checkedValsKey);

            for(int i = 0; i < checkedVals.length; i++){
                prefList.get(i).setChecked(checkedVals[i]);
            }
        }
        else{
            checkedVals = new boolean[7];
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState: Saving instance");
        outState.putBooleanArray(checkedValsKey, checkedVals);
    }
}
