package com.rudie.severin.eventorganizer.UtilityClasses;

import android.os.Parcelable;
import android.util.Log;

import com.rudie.severin.eventorganizer.CardClasses.EmptyDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.EmptyEventCard;
import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.FoodDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.LocationDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.OtherDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.PeopleDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.TimeDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.TransitDetailCard;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by erikrudie on 7/10/16.
 */
public class CardHolder implements Serializable {

    ArrayList<SuperCard> mEventHolder;
    transient EventsAdapter mEventsAdapter;
    transient DetailsAdapter mDetailsAdapter;

    public static CardHolder holderInstance;
    public static EventCard currentEvent;
    public static SuperDetailCard currentDetail;



    public CardHolder() {
        mEventHolder = new ArrayList<>();
        mEventHolder.add(new EmptyEventCard());
        Log.i("CardHolder: ", "new CardHolder created");
    }

    // Begin singleton cheats
    public static CardHolder getInstance() {
        if (holderInstance == null)
        {
            // Create the instance
            holderInstance = new CardHolder();
        }
        return holderInstance;
    }

    public static void setCurrentEvent(EventCard event) {
        currentEvent = event;
    }

    public static void setCurrentDetail(SuperDetailCard detail) {
        currentDetail = detail;
    }

    public static EventCard getCurrentEvent() {
        if (currentEvent == null) {
            Log.d("CardHolder.getevent:", "currentEvent == null");
        }
        return currentEvent;
    }

    public static SuperDetailCard getCurrentDetail() {
        if (currentDetail == null) {
            Log.d("CardHolder.getdetail:", "currentDetail == null");
        }
        return currentDetail;
    }
    // End singleton cheats


    public void passEventsAdapter(EventsAdapter eventsAdapter) {
        mEventsAdapter = eventsAdapter;
        Log.d("CardHolder", "Events adapter passed");
    }

    public void passDetailsAdapter(DetailsAdapter detailsAdapter) {
        mDetailsAdapter = detailsAdapter;
        Log.d("CardHolder", "Details adapter passed");
    }

    public void notifyAdaptersDataChanged() {
        // If this fires before the user reaches the second activity, it will return a null
        // pointer exception but will not impact functionality
        mEventsAdapter.notifyDataSetChanged();
        try {
            mDetailsAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("CardH:NotifyDetailAdap", "" + e.getMessage());
        }
    }

    public void notifyEventAdapterOnly() {
        mEventsAdapter.notifyDataSetChanged();
    }

    public ArrayList<SuperCard> getEventHolder() {
        return mEventHolder;
    }

    public void addEventCard(EventCard newCard) {

        mEventHolder.remove(mEventHolder.size() - 1);

        mEventHolder.add(newCard);
        mEventHolder.add(new EmptyEventCard());

        notifyEventAdapterOnly();
    }
}
