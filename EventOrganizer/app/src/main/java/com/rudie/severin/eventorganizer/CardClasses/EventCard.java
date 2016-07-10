package com.rudie.severin.eventorganizer.CardClasses;

import com.rudie.severin.eventorganizer.R;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

/**
 * Created by erikrudie on 7/10/16.
 */
public class EventCard extends SuperCard {

    private String mSubtext1;
    private String mSubtext2;

    public EventCard(String head, String sub1, String sub2) {
        super(PH.PARAM_EVENT_CARD, head);
        this.mSubtext1 = sub1;
        this.mSubtext2 = sub2;
    }

    public String getSubtext1() {
        return mSubtext1;
    }

    public void setSubtext1(String subtext1) {
        this.mSubtext1 = subtext1;
    }

    public String getSubtext2() {
        return mSubtext2;
    }

    public void setSubtext2(String subtext2) {
        this.mSubtext2 = subtext2;
    }
}
