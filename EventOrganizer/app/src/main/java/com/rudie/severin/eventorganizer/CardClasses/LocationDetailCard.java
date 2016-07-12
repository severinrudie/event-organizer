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

public class LocationDetailCard extends SuperDetailCard implements Serializable {

    public LocationDetailCard(String sub1, String sub2, String sub3) {
        super(CardHolder.getCurrentEvent(), PH.PARAM_LOCATION_DETAIL_CARD, "Location", sub1, sub2, sub3, "");
//        setIconResource("@drawable/ic_directions_black_24dp");
    }
}
