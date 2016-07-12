package com.rudie.severin.eventorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.EventsAdapter;
import com.rudie.severin.eventorganizer.UtilityClasses.SimpleLogger;

public class EventsActivity extends AppCompatActivity {

    ListView mEventListView;
    EventsAdapter eventsAdapter;
    SimpleLogger loggy;
    CardHolder cardHolder = null;

    String[] testArray;
    int testIterater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        cardHolder = CardHolder.getInstance();
        mEventListView = (ListView) findViewById(R.id.eventsListView);

        eventsAdapter = new EventsAdapter(this, cardHolder);
        mEventListView.setAdapter(eventsAdapter);
        loggy = new SimpleLogger(this);
        cardHolder.passEventsAdapter(eventsAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            cardHolder.notifyAdaptersDataChanged();
        } catch (NullPointerException e) {
            // If this fires before the user reaches the second activity, it will return a null
            // pointer exception but will not impact functionality
        }
    }
}
