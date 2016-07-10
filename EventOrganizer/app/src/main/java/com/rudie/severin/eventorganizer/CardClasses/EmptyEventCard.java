package com.rudie.severin.eventorganizer.CardClasses;

import com.rudie.severin.eventorganizer.UtilityClasses.PH;

/**
 * Created by erikrudie on 7/10/16.
 */

// EventCard holds information about Events, displayed on EventsActivity (the launch activity)
public class EmptyEventCard extends SuperCard {

    public EmptyEventCard() {
        super("New Event", PH.PARAM_EMPTY_EVENT_CARD);
    }
}
