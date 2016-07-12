package com.rudie.severin.eventorganizer.CardClasses;

/*  DetailAdapter will receive an event object from a mType which extends SuperCard.  getType will be
 *  checked against a list of hardcoded parameters, then the object will be casted to the appropriate
 *  mType.  DetailAdapter will then inflate the proper view and assign values.
 *
 *  Coding is so cool.
 */

import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

import java.io.Serializable;

public class EmptyDetailCard extends SuperDetailCard implements Serializable {

    public EmptyDetailCard(EventCard event) {
        super(CardHolder.getCurrentEvent(), PH.PARAM_EMPTY_DETAIL_CARD, "Click to add detail", "", "");
        setIconResource("@drawable/ic_note_add_black_24dp");
    }
}
