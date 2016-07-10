package com.rudie.severin.eventorganizer.UtilityClasses;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rudie.severin.eventorganizer.CardClasses.EmptyEventCard;
import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperCard;
import com.rudie.severin.eventorganizer.R;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by erikrudie on 7/10/16.
 */
// Inflates child views for ListView in activity_events.xml
public class EventsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<SuperCard> mEventCards;
    SimpleLogger logger;

    public EventsAdapter(Context mContext, ArrayList<SuperCard> mEventCards) {
        this.mContext = mContext;
        this.mEventCards = mEventCards;
        logger = new SimpleLogger("EventsAdapter");
    }

    @Override
    public int getCount() {
        return mEventCards.size();
    }

    @Override
    public Object getItem(int position) {
        return mEventCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View child, ViewGroup parent) {

        View v = child;
        CompleteListViewHolder viewHolder;
        String type = mEventCards.get(position).getType();
        logger.error(type);
        logger.error("EventCards.size() == " + mEventCards.size());
        if (type == null) {
            logger.debug("Position == " + position);
        }

        if (child == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (type.equals(PH.PARAM_EVENT_CARD)) {
                v = inflater.inflate(R.layout.event_list_item, null);
            } else if (type.equals(PH.PARAM_EMPTY_EVENT_CARD)) {
                v = inflater.inflate(R.layout.empty_event_list_item, null);
            }

            viewHolder = new CompleteListViewHolder(v, type);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }

        populateView(viewHolder, position, type);


        /*
        TODO: add onclick listener here. Pass over relevent <T extends SuperCard> object and set new
        activity (DetailsActivity),
         */


        return v;
    }

    // collects all necessary view IDs all at once
    class CompleteListViewHolder {
        public TextView header;
        public TextView subtext1;
        public TextView subtext2;
        public LinearLayout linearLayout;

        public CompleteListViewHolder(View base, String type) {
//            if (type.equals(PH.PARAM_EVENT_CARD)) {
                header = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_HEADER);
                subtext1 = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_SUBTEXT1);
                subtext2 = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_SUBTEXT2);
                linearLayout = (LinearLayout) base.findViewById(R.id.PARAM_ID_EVENT_OVERALL);
//            } else if (type.equals(PH.PARAM_EMPTY_EVENT_CARD)){
//                header = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_HEADER);
//            }
        }

    }

    // populates view according to card type
    private void populateView(CompleteListViewHolder viewHolder, int position, String type) {
        if (type != null) {

            if (type.equals(PH.PARAM_EVENT_CARD)) {
                EventCard card = (EventCard) mEventCards.get(position);

                viewHolder.header.setText(card.getHeader());

                viewHolder.subtext1.setText(card.getSubtext1());
                viewHolder.subtext2.setText(card.getSubtext2());
                int primaryBackground = mContext.getResources().getColor(R.color.colorPrimary);
                viewHolder.linearLayout.setBackgroundColor(primaryBackground);
            } else if (type.equals(PH.PARAM_EMPTY_EVENT_CARD)) {
                EmptyEventCard card = (EmptyEventCard) mEventCards.get(position);

                viewHolder.header.setText(card.getHeader());
                viewHolder.subtext1.setText("");
                viewHolder.subtext2.setText("");
                int greyedBackground = mContext.getResources().getColor(R.color.colorPrimaryGreyed);
                viewHolder.linearLayout.setBackgroundColor(greyedBackground);
            }
        }
    }




}

