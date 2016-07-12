package com.rudie.severin.eventorganizer.CardClasses;

import android.util.Log;

import com.rudie.severin.eventorganizer.DetailsActivity;
import com.rudie.severin.eventorganizer.R;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.DetailsAdapter;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;
import com.rudie.severin.eventorganizer.UtilityClasses.SimpleLogger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.logging.Logger;

/**
 * Created by erikrudie on 7/10/16.
 */

// EventCard holds information about Events, displayed on EventsActivity (the launch activity)
public class EventCard extends SuperCard implements Serializable {
    // implements serializable

    private String mSubtext1;
    private String mSubtext2;
    public ArrayList<SuperDetailCard> attachedDetails;



    public EventCard(String head, String sub1, String sub2) {
        super(PH.PARAM_EVENT_CARD, head, sub1, sub2);
        attachedDetails = new ArrayList<>();
    }

//  Begin getters & setters
    public ArrayList<SuperDetailCard> getAttachedDetails() {
        return attachedDetails;
    }
//  End getters & setters


    private void addEmptyDetailCard() {
        SuperDetailCard newDetail = new EmptyDetailCard(CardHolder.getCurrentEvent());
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        Log.i("EventCard:SEV ", "" + CardHolder.getCurrentEvent());
        Log.i("EventCard:SEV ", "attachedDetails.size==" + CardHolder.getCurrentEvent().getAttachedDetails().size());

    }

    public void verifyThatEmptyDetailExists() {
        if (CardHolder.getCurrentEvent().getAttachedDetails().size() > 0) {
            ArrayList<SuperDetailCard> details = CardHolder.getCurrentEvent().getAttachedDetails();
            // can throw ConcurrentModificationException.  If so, waits for 5 MS then tries again
            try {
                for (SuperDetailCard card : details) {
                    Log.i("EventCard:SEV ", "card.type==" + card.getType());
                    if (card.getType().equals(PH.PARAM_EMPTY_DETAIL_CARD)) {
                        details.remove(card);
                        Log.i("EventCard:SEV ", "removing card");
                    }
                }
                addEmptyDetailCard();
            } catch (ConcurrentModificationException e) {
                try {
                    Thread.sleep(5);
                } catch (Exception j) {
                }
                verifyThatEmptyDetailExists();
            }
        } else {
            addEmptyDetailCard();
        }
    }

    public PeopleDetailCard addPeopleDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        PeopleDetailCard newDetail = new PeopleDetailCard(CardHolder.getCurrentEvent(), sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public LocationDetailCard addLocationDetailCard(String sub1, String sub2, String sub3) {
        LocationDetailCard newDetail = new LocationDetailCard(CardHolder.getCurrentEvent(), sub1, sub2, sub3);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public TimeDetailCard addTimeDetailCard(String sub1, String sub2) {
        TimeDetailCard newDetail = new TimeDetailCard(CardHolder.getCurrentEvent(), sub1, sub2);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public FoodDetailCard addFoodDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        FoodDetailCard newDetail = new FoodDetailCard(CardHolder.getCurrentEvent(), sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public TransitDetailCard addTransitDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        TransitDetailCard newDetail = new TransitDetailCard(CardHolder.getCurrentEvent(), sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public OtherDetailCard addOtherDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        OtherDetailCard newDetail = new OtherDetailCard(CardHolder.getCurrentEvent(), sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }

}
