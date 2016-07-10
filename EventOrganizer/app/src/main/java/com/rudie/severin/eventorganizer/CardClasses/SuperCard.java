package com.rudie.severin.eventorganizer.CardClasses;

/*  DetailAdapter will receive an event object from a mType which extends SuperCard.  getType will be
 *  checked against a list of hardcoded parameters, then the object will be casted to the appropriate
 *  mType.  DetailAdapter will then inflate the proper view and assign values.
 *
 *  Coding is so cool.
 */
public abstract class SuperCard {

    private String mHeader;
    private String mType;

    public SuperCard(String type, String head) {
        setHeader(head);
        setType(type);
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


}
