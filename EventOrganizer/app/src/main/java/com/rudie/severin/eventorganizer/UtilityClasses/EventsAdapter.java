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


/*

package com.genassembly.dotdashdot.listexample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class SimpleAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private final ArrayList<MadLibs> libs;
    private int sortBy;
    private final Context mContext;

    public SimpleAdapter(Context mContext, ArrayList<MadLibs> libs) {
        //super();
        inflater = LayoutInflater.from(mContext);
        this.libs = libs;
        this.sortBy = R.string.SORT_BY_WORDS;
        this.mContext = mContext;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    @Override
    public int getCount() {
        return libs.size();
    }

    @Override
    public Object getItem(int position) {
        return libs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View child, ViewGroup parent) {

        Log.d("Postion: " , "" + position);

        View v = child;
        TextView words, genre, spaces;

        if (v == null) {
            v = inflater.inflate(R.layout.list_item, parent, false);
        }
        if (this.sortBy == R.string.SORT_BY_WORDS) {
            words = (TextView) v.findViewById(R.id.FIELD1);
            genre = (TextView) v.findViewById(R.id.FIELD2);
            spaces = (TextView) v.findViewById(R.id.FIELD3);
        } else if (this.sortBy == R.string.SORT_BY_GENRE) {
            genre = (TextView) v.findViewById(R.id.FIELD1);
            spaces = (TextView) v.findViewById(R.id.FIELD2);
            words = (TextView) v.findViewById(R.id.FIELD3);
        } else if (this.sortBy == R.string.SORT_BY_SPACES) {
            spaces = (TextView) v.findViewById(R.id.FIELD1);
            words = (TextView) v.findViewById(R.id.FIELD2);
            genre = (TextView) v.findViewById(R.id.FIELD3);
        } else {
            Log.d("MAIN", "SORT_BY conditional not met, using default");
            words = (TextView) v.findViewById(R.id.FIELD1);
            genre = (TextView) v.findViewById(R.id.FIELD2);
            spaces = (TextView) v.findViewById(R.id.FIELD3);
        }

        words.setText(String.valueOf(libs.get(position).getWords()));
        genre.setText(String.valueOf(libs.get(position).getGenre()));
        spaces.setText(String.valueOf(libs.get(position).getSpaces()));

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Adapter", "Clicked " + position);
                Intent intent = new Intent(mContext.getApplicationContext(), EnterMadLibInfo.class);
                intent.putExtra("MAD_LIB", libs.get(position).madLibs);
                mContext.startActivity(intent);
            }
        });


        return v;
    }
}



 */