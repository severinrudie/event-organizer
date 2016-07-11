package com.rudie.severin.eventorganizer.UtilityClasses;

import com.rudie.severin.eventorganizer.CardClasses.EmptyEventCard;
import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperCard;

import java.util.ArrayList;

/**
 * Created by erikrudie on 7/10/16.
 */
public class CardHolder {

    ArrayList<SuperCard> mEventHolder;
    EventsAdapter mEventsAdapter;

    public CardHolder() {
        mEventHolder = new ArrayList<>();
        mEventHolder.add(new EmptyEventCard());
    }

    public void passEventsAdapter(EventsAdapter eventsAdapter) {
        mEventsAdapter = eventsAdapter;
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
