package com.rudie.severin.eventorganizer.UtilityClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.R;

import java.util.ArrayList;

/**
 * Created by erikrudie on 7/10/16.
 */
public class EventsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<EventCard> mEventCards;

    public EventsAdapter(Context mContext, ArrayList<EventCard> mEventCards) {
        this.mContext = mContext;
        this.mEventCards = mEventCards;
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
        if (child == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.event_list_item, null);
            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }
        viewHolder.header.setText(mEventCards.get(position).getHeader());
        viewHolder.subtext1.setText(mEventCards.get(position).getSubtext1());
        viewHolder.subtext2.setText(mEventCards.get(position).getSubtext2());

        /*
        TODO: add onclick listener here
         */


        return v;
    }

    // collects all necessary view IDs all at once
    class CompleteListViewHolder {
        public TextView header;
        public TextView subtext1;
        public TextView subtext2;

        public CompleteListViewHolder(View base) {
            header = (TextView) base.findViewById(R.id.eventHeader);
            subtext1 = (TextView) base.findViewById(R.id.eventSubtext1);
            subtext2 = (TextView) base.findViewById(R.id.eventSubtext2);
        }

    }

}

