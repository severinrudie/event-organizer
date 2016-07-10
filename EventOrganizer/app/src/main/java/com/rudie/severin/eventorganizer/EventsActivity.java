package com.rudie.severin.eventorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.rudie.severin.eventorganizer.CardClasses.EmptyEventCard;
import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperCard;
import com.rudie.severin.eventorganizer.UtilityClasses.EventsAdapter;

import java.util.ArrayList;

public class EventsActivity extends AppCompatActivity {

    ArrayList<SuperCard> mEventHolder;
    ListView mEventListView;
    EventsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mEventHolder = new ArrayList<SuperCard>();
        mEventListView = (ListView) findViewById(R.id.eventsListView);

        adapter = new EventsAdapter(this, mEventHolder);
        mEventListView.setAdapter(adapter);

    }

    public void tempTest(View view) {

        mEventHolder.add(new EventCard("Hey", "this", "worked!"));
        mEventHolder.add(new EventCard("Holy", "shit", "cool!"));
        mEventHolder.add(new EmptyEventCard());

        adapter.notifyDataSetChanged();

    }

}
