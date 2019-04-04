package com.example.cs422layne;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RouteOverview extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_overview);

        Button roBackButton = findViewById(R.id.ROBackButton);
        roBackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RouteOverview.this, RecommendedRoutes.class));
            }

        });

        Button roGoButton = findViewById(R.id.ROGoButton);
        roGoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RouteOverview.this, MapsActivity.class));
            }

        });

        ImageButton roPrefButton = findViewById(R.id.ROMenuButton);
        roPrefButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(RouteOverview.this, PreferencesActivity.class));
            }

        });
    }
}