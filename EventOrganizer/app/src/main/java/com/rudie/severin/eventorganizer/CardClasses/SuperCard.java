package com.rudie.severin.eventorganizer.CardClasses;

// DetailAdapter may have to get information from various classes, SuperCard will allow it to
// query their card type without raising a casting exception
public abstract class SuperCard {

    private String mHeader;
    private String type;

    public SuperCard(String type, String head) {
        this.mHeader = head;
        this.type = type;
    }

    public String returnType() {
        return this.type;
    }

    public String getHeader() {
        return mHeader;
    }

    public void setHeader(String header) {
        this.mHeader = header;
    }


}
