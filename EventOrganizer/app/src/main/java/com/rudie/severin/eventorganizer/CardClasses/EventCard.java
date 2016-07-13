package com.rudie.severin.eventorganizer.CardClasses;

import android.util.Log;

import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

/**
 * Created by erikrudie on 7/10/16.
 */

// EventCard holds information about Events, displayed on EventsActivity (the launch activity)
public class EventCard extends SuperCard implements Serializable {
    // implements serializable

    public ArrayList<SuperDetailCard> attachedDetails;
    private String lastUsed = null;



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
        SuperDetailCard newDetail = new EmptyDetailCard();
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        Log.i("EventCard:SEV ", "" + CardHolder.getCurrentEvent());
        Log.i("EventCard:SEV ", "attachedDetails.size==" + CardHolder.getCurrentEvent().getAttachedDetails().size());

    }

    public void verifyThatEmptyDetailExists() {
        if (CardHolder.getCurrentEvent().getAttachedDetails().size() > 0) {
            ArrayList<SuperDetailCard> details = CardHolder.getCurrentEvent().getAttachedDetails();
            // can throw ConcurrentModificationException.  If so, waits for 5 MS then tries again
            try {
                for (SuperDetailCard card : details) {
                    Log.i("EventCard:SEV ", "card.type==" + card.getType());
                    if (card.getType().equals(PH.PARAM_EMPTY_DETAIL_CARD)) {
                        details.remove(card);
                        Log.i("EventCard:SEV ", "removing card");
                    }
                }
                addEmptyDetailCard();
            } catch (ConcurrentModificationException e) {
                try {
                    Thread.sleep(5);
                } catch (Exception j) {
                }
                verifyThatEmptyDetailExists();
            }
        } else {
            addEmptyDetailCard();
        }
    }

    public PeopleDetailCard addPeopleDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        PeopleDetailCard newDetail = new PeopleDetailCard(sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public LocationDetailCard addLocationDetailCard(String sub1, String sub2, String sub3) {
        LocationDetailCard newDetail = new LocationDetailCard(sub1, sub2, sub3);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public TimeDetailCard addTimeDetailCard(String sub1, String sub2) {
        TimeDetailCard newDetail = new TimeDetailCard(sub1, sub2);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public FoodDetailCard addFoodDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        FoodDetailCard newDetail = new FoodDetailCard(sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public TransitDetailCard addTransitDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        TransitDetailCard newDetail = new TransitDetailCard(sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }
    public OtherDetailCard addOtherDetailCard(String sub1, String sub2, String sub3,
                                                String sub4) {
        OtherDetailCard newDetail = new OtherDetailCard(sub1, sub2, sub3, sub4);
        CardHolder.getCurrentEvent().attachedDetails.add(newDetail);
        verifyThatEmptyDetailExists();

        return newDetail;
    }

    public void populateSubtext() {
        int cardsFound = 0;
        //                      people, location, time, food, transit, other
        boolean[] containsType = {false, false, false, false, false, false};

        // if the current detail contains text, and is of a valid type, continue
        for (int i = 0; i < attachedDetails.size(); i++) {
            if (attachedDetails.get(i).getEnteredText().size() > 0) {
//                if (attachedDetails.get(i).getType().equals(PH.PARAM_PEOPLE_DETAIL_CARD)) {
//                    containsType[0] = true;
//                    cardsFound++;
//                    continue;
//                } else
                if (attachedDetails.get(i).getType().equals(PH.PARAM_LOCATION_DETAIL_CARD)) {
                    containsType[1] = true;
                    cardsFound++;
                    continue;
                } else if (attachedDetails.get(i).getType().equals(PH.PARAM_TIME_DETAIL_CARD)) {
                    containsType[2] = true;
                    cardsFound++;
                    continue;
                }
//                else if (attachedDetails.get(i).getType().equals(PH.PARAM_FOOD_DETAIL_CARD)) {
//                    containsType[3] = true;
//                    cardsFound++;
//                    continue;
//                } else if (attachedDetails.get(i).getType().equals(PH.PARAM_TRANSIT_DETAIL_CARD)) {
//                    containsType[4] = true;
//                    cardsFound++;
//                    continue;
//                } else if (attachedDetails.get(i).getType().equals(PH.PARAM_OTHER_DETAIL_CARD)) {
//                    containsType[5] = true;
//                    cardsFound++;
//                    continue;
//                }
            }
            lastUsed = null;
            if (cardsFound > 0) {
                setSubtext1(pickSubtext(containsType));
                cardsFound--;
            } else {
                setSubtext1("");
            }
            if (cardsFound > 0) {
                setSubtext2(pickSubtext(containsType));
                cardsFound--;
            } else {
                setSubtext2("");
            }
        }
    }

    // selects which text to display first from location and time
    private String pickSubtext(boolean[] boolArray) {
        //         people, location, time, food, transit, other

        if (boolArray[1] && (lastUsed == null || !(lastUsed.equals(PH.PARAM_LOCATION_DETAIL_CARD)))){
            //location
            for (SuperDetailCard detail : attachedDetails) {
                if (detail.getType().equals(PH.PARAM_LOCATION_DETAIL_CARD)) {
                    lastUsed = PH.PARAM_LOCATION_DETAIL_CARD;
                    return(detail.getEnteredText().get(0));
                }
            }
        } else if (boolArray[2] && (lastUsed == null || !(lastUsed.equals(PH.PARAM_TIME_DETAIL_CARD)))){
            //time
            for (SuperDetailCard detail : attachedDetails) {
                if (detail.getType().equals(PH.PARAM_TIME_DETAIL_CARD)) {
                    lastUsed = PH.PARAM_TIME_DETAIL_CARD;
                    return(detail.getEnteredText().get(0));
                }
            }
        }
        return null;
    }


}
