package com.rudie.severin.eventorganizer.CardClasses;

import com.rudie.severin.eventorganizer.R;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

/**
 * Created by erikrudie on 7/10/16.
 */

// EventCard holds information about Events, displayed on EventsActivity (the launch activity)
public class EventCard extends SuperCard {

    private String mSubtext1;
    private String mSubtext2;

    public EventCard(String head, String sub1, String sub2) {
        super(PH.PARAM_EVENT_CARD, head, sub1, sub2);

    }


}
