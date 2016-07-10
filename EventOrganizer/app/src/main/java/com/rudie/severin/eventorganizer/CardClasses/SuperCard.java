package com.rudie.severin.eventorganizer.CardClasses;

/*  DetailAdapter will receive an event object from a type which extends SuperCard.  getType will be
 *  checked against a list of hardcoded parameters, then the object will be casted to the appropriate
 *  type.  DetailAdapter will then inflate the proper view and assign values.
 *
 *  Coding is so cool.
 */
public abstract class SuperCard {

    private String mHeader;
    private String type;

    public SuperCard(String type, String head) {
        this.mHeader = head;
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        this.mHeader = header;
    }


}
