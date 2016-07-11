package com.rudie.severin.eventorganizer.CardClasses;

/*  DetailAdapter will receive an event object from a mType which extends SuperCard.  getType will be
 *  checked against a list of hardcoded parameters, then the object will be casted to the appropriate
 *  mType.  DetailAdapter will then inflate the proper view and assign values.
 *
 *  Coding is so cool.
 */

import com.rudie.severin.eventorganizer.UtilityClasses.PH;

import java.io.Serializable;

public class OtherDetailCard extends SuperDetailCard implements Serializable {

    public OtherDetailCard(EventCard event, String sub1, String sub2, String sub3, String sub4) {
        super(event, PH.PARAM_OTHER_DETAIL_CARD, "Other", sub1, sub2, sub3, sub4);
//        setIconResource("@drawable/ic_open_in_new_black_24dp");
    }
}
