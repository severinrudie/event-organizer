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

public class TransitDetailCard extends SuperDetailCard implements Serializable {

    public TransitDetailCard(String sub1, String sub2, String sub3, String sub4) {
        super(CardHolder.getCurrentEvent(), PH.PARAM_TRANSIT_DETAIL_CARD, "Transportation", sub1, sub2, sub3, sub4);
        setIconResource("@drawable/ic_directions_car_black_24dp");
    }
}
