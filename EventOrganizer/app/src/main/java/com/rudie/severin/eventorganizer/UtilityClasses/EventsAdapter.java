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
import com.rudie.severin.eventorganizer.EventsActivity;
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
    CardHolder cardHolder;

    public EventsAdapter(Context mContext, CardHolder holder) {
        this.mContext = mContext;
        this.mEventCards = holder.getEventHolder();
        logger = new SimpleLogger("EventsAdapter");
        cardHolder = holder;
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

        if (type == null) {
            logger.debug("Type == null. Position == " + position);
        }

        if (child == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = inflater.inflate(R.layout.event_list_item, null);

            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }

        populateView(viewHolder, position, type);
        setListener(v, type);


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

        public CompleteListViewHolder(View base) {
                header = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_HEADER);
                subtext1 = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_SUBTEXT1);
                subtext2 = (TextView) base.findViewById(R.id.PARAM_ID_EVENT_SUBTEXT2);
                linearLayout = (LinearLayout) base.findViewById(R.id.PARAM_ID_EVENT_OVERALL);
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

    private void setListener(View view, String type) {

        if (type.equals(PH.PARAM_EVENT_CARD)) {
            // TODO: put activity changes here
            view.setOnClickListener(null);
        } else if (type.equals(PH.PARAM_EMPTY_EVENT_CARD)) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cardHolder.addEventCard(new EventCard("Click here to add details", "", ""));
                }
            });
        }

    }




}

