package com.rudie.severin.eventorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.rudie.severin.eventorganizer.CardClasses.EmptyEventCard;
import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.EventsAdapter;
import com.rudie.severin.eventorganizer.UtilityClasses.SimpleLogger;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Logger;

public class EventsActivity extends AppCompatActivity {

    ListView mEventListView;
    EventsAdapter adapter;
    String[] testArray;
    int testIterater;
    SimpleLogger loggy;
    CardHolder cardHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        cardHolder = new CardHolder();
        mEventListView = (ListView) findViewById(R.id.eventsListView);

        adapter = new EventsAdapter(this, cardHolder);
        mEventListView.setAdapter(adapter);
        loggy = new SimpleLogger(this);
        cardHolder.passEventsAdapter(adapter);

        testArray = new String[] {"first", "second", "third", "fourth", "fifth", "sixth", "seventh",
                "eighth", "ninth", "tenth", "eleventh", "12th", "thirteenth"};
        testIterater = 0;

    }

//    public void tempTest(View view) {
//
//        String[] holder = new String[3];
//
//        for (int i = 0; i < 3; i++) {
//            if (testIterater < testArray.length) {
//                holder[i] = testArray[testIterater];
//                testIterater++;
//            } else {
//                testIterater = 0;
//            }
//        }
//
//        cardHolder.addEventCard(new EventCard(holder[0], holder[1], holder[2]));
//
//    }

}
