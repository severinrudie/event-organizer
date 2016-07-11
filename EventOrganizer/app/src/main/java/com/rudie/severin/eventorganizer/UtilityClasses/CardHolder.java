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
    ArrayList<SuperDetailCard> mDetailHolder;
    transient DetailsAdapter mDetailsAdapter;

    public CardHolder() {
        mEventHolder = new ArrayList<>();
        mEventHolder.add(new EmptyEventCard());
        mDetailHolder = new ArrayList<>();
        //TEST START
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));


        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        mDetailHolder.add(new PeopleDetailCard(new EventCard("hi", "there", "people"),
                "John", "Joe", "", ""));
        mDetailHolder.add(new LocationDetailCard(new EventCard("hi", "there", "people"),
                "123", "Seattle", "98122"));
        mDetailHolder.add(new TimeDetailCard(new EventCard("hi", "there", "people"),
                "7/12/16", "8:00 PM"));
        mDetailHolder.add(new FoodDetailCard(new EventCard("hi", "there", "people"),
                "Pizza", "Hot Dogs", "", ""));
        mDetailHolder.add(new TransitDetailCard(new EventCard("hi", "there", "people"),
                "Joe - Joe's Car", "John - Joe's Car", "", ""));
        mDetailHolder.add(new OtherDetailCard(new EventCard("hi", "there", "people"),
                "Bring a basketball", "", "", ""));
        //TEST END
        mDetailHolder.add(new EmptyDetailCard());
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

    public ArrayList<SuperDetailCard> getDetailHolder() {
        return mDetailHolder;
    }

    public void addEventCard(EventCard newCard) {

        mEventHolder.remove(mEventHolder.size() - 1);

        mEventHolder.add(newCard);
        mEventHolder.add(new EmptyEventCard());

        mEventsAdapter.notifyDataSetChanged();
    }

    // TODO: set this up
//    public void addDetailCard(EventCard newCard) {
//
//        mEventHolder.remove(mEventHolder.size() - 1);
//
//        mEventHolder.add(newCard);
//        mEventHolder.add(new EmptyEventCard());
//
//        mEventsAdapter.notifyDataSetChanged();
//    }

}
