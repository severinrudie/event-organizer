package com.example.severinrudie.mockupproject1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MockUpEventActivity extends AppCompatActivity {

    LinearLayout myEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_up_event);

        myEvent = (LinearLayout) findViewById(R.id.mockupEvent);
        myEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MockUpDetailActivity.class);
                startActivity(intent);
            }
        });

    }
}
