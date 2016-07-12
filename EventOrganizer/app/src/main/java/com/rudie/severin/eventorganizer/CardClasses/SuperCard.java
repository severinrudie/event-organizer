package com.rudie.severin.eventorganizer.CardClasses;

/*  DetailAdapter will receive an event object from a mType which extends SuperCard.  getType will be
 *  checked against a list of hardcoded parameters, then the object will be casted to the appropriate
 *  mType.  DetailAdapter will then inflate the proper view and assign values.
 *
 *  Coding is so cool.
 */

import java.io.Serializable;

public abstract class SuperCard implements Serializable {

    private String mHeader;
    private String mType;
    private String mSubtext1;
    private String mSubtext2;

    //temp
    public String debugName;
    //temp

    public SuperCard(String type, String head, String sub1, String sub2) {
        setHeader(head);
        setType(type);
        setSubtext1(sub1);
        setSubtext2(sub2);
    }

    public String getType() {
        return this.mType;
    }

    public void setType(String type) {
        this.mType = type;
    }

    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        this.mHeader = header;
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
