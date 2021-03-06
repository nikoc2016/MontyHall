package com.nikoc.montyhallwiththoms;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class doorSelector_1 extends AppCompatActivity {

    ImageButton door1, door2, door3;
    int status; //0 - to click first door, 1 - to click last door, 2 - to show result
    int correctDoor; // 1-3
    int door1Status; // 1-4 1:close 2:lock 3:empty 4:found
    int door2Status; // 1-4 1:close 2:lock 3:empty 4:found
    int door3Status; // 1-4 1:close 2:lock 3:empty 4:found
    int doorClicked; //1-3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_selector_1);

        initGame();

        door1 = (ImageButton) findViewById(R.id.door1);
        door2 = (ImageButton) findViewById(R.id.door2);
        door3 = (ImageButton) findViewById(R.id.door3);

        status = 0;
        addListeners();

    }

    public void initGame(){

        //Generate Thoms
        Random r = new Random();
        int Low = 1;
        int High = 4;
        correctDoor = r.nextInt(High-Low) + Low;
        Log.d("Engine:","Thoms is behind door " + Integer.toString(correctDoor));

        status = 0;
        door1Status = door2Status = door3Status = 1;
    }

    public void addListeners() {
        final Context context = this;
        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doorClicked = 1;
                nextActivity(context);
            }
        });
        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doorClicked = 2;
                nextActivity(context);
            }
        });
        door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                doorClicked = 3;
                nextActivity(context);
            }
        });

        //RESTART
        Button button= (Button) findViewById(R.id.btn_restart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, doorSelector_1.class);
                startActivity(intent);
            }
        });
    }

    public void nextActivity(Context context){
        Intent intent = new Intent(context, doorSelector_2.class);
        intent.putExtra("status",status);
        intent.putExtra("correctDoor",correctDoor);
        intent.putExtra("door1Status",door1Status);
        intent.putExtra("door2Status",door2Status);
        intent.putExtra("door3Status",door3Status);
        intent.putExtra("doorClicked",doorClicked);
        startActivity(intent);
    }
}
