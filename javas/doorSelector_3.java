package com.nikoc.montyhallwiththoms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class doorSelector_3 extends AppCompatActivity {

    ImageButton door1, door2, door3;
    int status; //0 - to click first door, 1 - to click last door, 2 - to show result
    int correctDoor; // 1-3
    int door1Status; // 1-4 1:close 2:lock 3:empty 4:found
    int door2Status; // 1-4 1:close 2:lock 3:empty 4:found
    int door3Status; // 1-4 1:close 2:lock 3:empty 4:found
    int doorClicked; //1-3
    int doorLocked; //1-3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door_selector_1);

        status = 2;

        Intent intent = getIntent();
        status = intent.getIntExtra("status",0);
        correctDoor = intent.getIntExtra("correctDoor",0);
        door1Status = intent.getIntExtra("door1Status",0);
        door2Status = intent.getIntExtra("door2Status",0);
        door3Status = intent.getIntExtra("door3Status",0);
        doorClicked = intent.getIntExtra("doorClicked",0);
        doorLocked = intent.getIntExtra("doorLocked",0);

        door1 = (ImageButton) findViewById(R.id.door1);
        door2 = (ImageButton) findViewById(R.id.door2);
        door3 = (ImageButton) findViewById(R.id.door3);
        TextView mainText = (TextView)findViewById(R.id.prompt);

        //Show locked door
        if (doorLocked == 1) {
            door1.setImageResource(R.drawable.lock);
        } else if (doorLocked == 2) {
            door2.setImageResource(R.drawable.lock);
        } else if (doorLocked == 3) {
            door3.setImageResource(R.drawable.lock);
        }

        if (doorClicked == correctDoor) {
            mainText.setText(R.string.door3_found);
            if (doorClicked == 1) {
                door1.setImageResource(R.drawable.found);
            } else if (doorClicked == 2) {
                door2.setImageResource(R.drawable.found);
            } else if (doorClicked == 3) {
                door3.setImageResource(R.drawable.found);
            }
        } else {
            mainText.setText(R.string.door3_empty);
            Toast.makeText(doorSelector_3.this,
                    R.string.hint, Toast.LENGTH_SHORT).show();
            if (doorClicked == 1) {
                door1.setImageResource(R.drawable.empty);
            } else if (doorClicked == 2) {
                door2.setImageResource(R.drawable.empty);
            } else if (doorClicked == 3) {
                door3.setImageResource(R.drawable.empty);
            }
        }

        addListeners();

    }

    public void addListeners() {
        door1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            }
        });
        door2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            }
        });
        door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
            }
        });

        //RESTART
        Button button= (Button) findViewById(R.id.btn_restart);
        final Context context = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, doorSelector_1.class);
                startActivity(intent);
            }
        });
    }

    public void nextActivity(){
        final Context context = this;
        Intent intent = new Intent(context, doorSelector_1.class);
        startActivity(intent);
    }
}
