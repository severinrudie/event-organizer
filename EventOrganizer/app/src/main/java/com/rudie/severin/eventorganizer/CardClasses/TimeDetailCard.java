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

public class TimeDetailCard extends SuperDetailCard implements Serializable {

    public TimeDetailCard(String sub1, String sub2) {
        super(CardHolder.getCurrentEvent(), PH.PARAM_TIME_DETAIL_CARD, "Time", sub1, sub2, "", "");
//        setIconResource("@drawable/ic_schedule_black_24dp");
    }
}
