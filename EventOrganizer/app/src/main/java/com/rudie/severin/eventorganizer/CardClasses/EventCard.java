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
import java.util.logging.Logger;

/**
 * Created by erikrudie on 7/10/16.
 */

// EventCard holds information about Events, displayed on EventsActivity (the launch activity)
public class EventCard extends SuperCard  {
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
        boolean noEmpty = true;
        for (SuperDetailCard card : attachedDetails) {
            Log.i("EventCard:SEV ", "card.type==" + card.getType());
            if (card.getType().equals(PH.PARAM_EMPTY_DETAIL_CARD)) {
                attachedDetails.remove(card);
            }
        }
            addEmptyDetailCard();
    }

    public void addPeopleDetailCard(String sub1, String sub2, String sub3,
                                    String sub4) {
        PeopleDetailCard newDetail = new PeopleDetailCard(CardHolder.getCurrentEvent(), sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        Log.i("EventCard:SEV ", "" + newDetail.getParentEvent());
        Log.i("EventCard:SEV ", "attachedDetails.size==" + CardHolder.getCurrentEvent().getAttachedDetails().size());
    }

}
