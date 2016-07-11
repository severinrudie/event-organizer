package com.rudie.severin.eventorganizer.CardClasses;

/*  DetailAdapter will receive an event object from a mType which extends SuperCard.  getType will be
 *  checked against a list of hardcoded parameters, then the object will be casted to the appropriate
 *  mType.  DetailAdapter will then inflate the proper view and assign values.
 *
 *  Coding is so cool.
 */

import com.rudie.severin.eventorganizer.UtilityClasses.PH;

import java.io.Serializable;

public class FoodDetailCard extends SuperDetailCard implements Serializable {

    public FoodDetailCard(EventCard event, String head, String sub1, String sub2, String sub3, String sub4) {
        super(event, PH.PARAM_FOOD_DETAIL_CARD, "Food", sub1, sub2, sub3, sub4);
        setSubtext3(sub3);
        setSubtext4(sub4);
        setIconResource("@drawable/ic_local_dining_black_24dp");
    }
}
