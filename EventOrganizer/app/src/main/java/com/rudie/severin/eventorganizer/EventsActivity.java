package com.rudie.severin.eventorganizer;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.EventsAdapter;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;
import com.rudie.severin.eventorganizer.UtilityClasses.SimpleLogger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EventsActivity extends AppCompatActivity {

    ListView mEventListView;
    EventsAdapter eventsAdapter;
    SimpleLogger loggy;
    CardHolder cardHolder = null;
    File saveFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        saveFile = getApplicationContext().getFileStreamPath("CardHolder.ser");

        if (cardHolder == null) {
            try {
                cardHolder = loadSerializedObject(saveFile);
                if (cardHolder == null) {
                    cardHolder = CardHolder.getInstance();
                } else {
                    cardHolder.holderInstance = cardHolder;
                }
            } catch (Exception e) {
                cardHolder = CardHolder.getInstance();
            }
        }

        mEventListView = (ListView) findViewById(R.id.eventsListView);

        eventsAdapter = new EventsAdapter(this, cardHolder);
        mEventListView.setAdapter(eventsAdapter);
        loggy = new SimpleLogger(this);
        cardHolder.passEventsAdapter(eventsAdapter);
    }  // end onCreate

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < cardHolder.getEventHolder().size(); i++) {
            if (cardHolder.getEventHolder().get(i).getType().equals(PH.PARAM_EVENT_CARD)) {
                EventCard thisCard = (EventCard) cardHolder.getEventHolder().get(i);
                thisCard.populateSubtext();

                System.out.println("");
            }
        }

        try {
            cardHolder.notifyAdaptersDataChanged();
        } catch (Exception e) {
            // If this fires before the user reaches the second activity, it will return a null
            // pointer exception but will not impact functionality
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveCardHolder(CardHolder.getInstance());
    }

    // CardHolder contains all information needed to restart the application
    public void saveCardHolder(CardHolder cardHolder){
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile));
            oos.writeObject(cardHolder);
            oos.flush();
            oos.close();
            Log.d("Serial Write Success : ", "true");
        }
        catch(Exception ex)
        {
            Log.d("Serial Write Error : ", "" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public CardHolder loadSerializedObject(File saveFile) throws Exception
    {
        try
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile));
            cardHolder = (CardHolder) ois.readObject();
            Log.d("Serial Read Success : ", "true");
            return cardHolder;
        }
        catch(Exception ex)
        {
            Log.d("Serial Read Error : ", "" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
