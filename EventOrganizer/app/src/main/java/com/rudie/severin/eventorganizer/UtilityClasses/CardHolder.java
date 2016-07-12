package com.rudie.severin.eventorganizer.UtilityClasses;

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

    public CardHolder() {
        mEventHolder = new ArrayList<>();
        mEventHolder.add(new EmptyEventCard());
    }

    public void passEventsAdapter(EventsAdapter eventsAdapter) {
        mEventsAdapter = eventsAdapter;
    }

    public void passDetailsAdapter(DetailsAdapter detailsAdapter) {
        mDetailsAdapter = detailsAdapter;
    }

    public ArrayList<SuperCard> getEventHolder() {
        return mEventHolder;
    }

    public void addEventCard(EventCard newCard) {

        mEventHolder.remove(mEventHolder.size() - 1);

        mEventHolder.add(newCard);
        mEventHolder.add(new EmptyEventCard());

        mEventsAdapter.notifyDataSetChanged();
    }
}
