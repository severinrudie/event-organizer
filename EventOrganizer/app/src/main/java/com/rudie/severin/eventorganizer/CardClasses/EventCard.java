package com.rudie.severin.eventorganizer.CardClasses;

import android.util.Log;

import com.rudie.severin.eventorganizer.DetailsActivity;
import com.rudie.severin.eventorganizer.R;
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
public class EventCard extends SuperCard implements Serializable {

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
        SuperDetailCard newDetail = new EmptyDetailCard(this);
        attachedDetails.add(newDetail);
        Log.i("EventCard: ", "" + newDetail.getParentEvent());
    }

    public void verifyThatEmptyDetailExists() {
        boolean noEmpty = true;
        for (SuperDetailCard card : attachedDetails) {
            if (card.getType() == PH.PARAM_EMPTY_DETAIL_CARD) {
                noEmpty = false;
                return;
            }
        }
        if (noEmpty) {
            addEmptyDetailCard();
        }
    }

    public void addPeopleDetailCard(String sub1, String sub2, String sub3,
                                    String sub4) {
        PeopleDetailCard newDetail = new PeopleDetailCard(this, sub1, sub2, sub3, sub4);
        attachedDetails.add(newDetail);
    }

}
