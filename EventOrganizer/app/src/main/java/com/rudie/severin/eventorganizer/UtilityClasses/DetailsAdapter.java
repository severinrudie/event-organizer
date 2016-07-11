package com.rudie.severin.eventorganizer.UtilityClasses;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rudie.severin.eventorganizer.CardClasses.EmptyDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.EmptyEventCard;
import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.PeopleDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.R;

import java.util.ArrayList;

/**
 * Created by erikrudie on 7/10/16.
 */
// Inflates child views for ListView in activity_events.xml
public class DetailsAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<SuperDetailCard> mDetailCards;
    SimpleLogger logger;
    CardHolder cardHolder;

    public DetailsAdapter(Context mContext, CardHolder holder) {
        this.mContext = mContext;
        this.mDetailCards = holder.getDetailHolder();
        logger = new SimpleLogger("DetailsAdapter");
        cardHolder = holder;
    }

    @Override
    public int getCount() {
        return mDetailCards.size();
    }

    @Override
    public Object getItem(int position) {
        return mDetailCards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View child, ViewGroup parent) {

        View v = child;
        CompleteListViewHolder viewHolder;
        String type = mDetailCards.get(position).getType();

        if (type == null) {
            logger.debug("Type == null. Position == " + position);
        }

        if (child == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = inflater.inflate(R.layout.detail_list_item, null);

            viewHolder = new CompleteListViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (CompleteListViewHolder) v.getTag();
        }

        populateView(viewHolder, position, type);
        setListener(v, type);

        return v;
    }

    // collects all necessary view IDs all at once
    class CompleteListViewHolder {
        public TextView header;
        public TextView subtext1;
        public TextView subtext2;
        public TextView subtext3;
        public TextView subtext4;
        public ImageButton icon;
        public LinearLayout linearLayout;

        public CompleteListViewHolder(View base) {
            header = (TextView) base.findViewById(R.id.PARAM_ID_DETAIL_HEADER);
            subtext1 = (TextView) base.findViewById(R.id.PARAM_ID_DETAIL_SUBTEXT1);
            subtext2 = (TextView) base.findViewById(R.id.PARAM_ID_DETAIL_SUBTEXT2);
            subtext3 = (TextView) base.findViewById(R.id.PARAM_ID_DETAIL_SUBTEXT3);
            subtext4 = (TextView) base.findViewById(R.id.PARAM_ID_DETAIL_SUBTEXT4);
            icon = (ImageButton) base.findViewById(R.id.PARAM_ID_DETAIL_ICON);
            linearLayout = (LinearLayout) base.findViewById(R.id.PARAM_ID_DETAIL_OVERALL);
        }

    }

    // populates view according to card type
    private void populateView(CompleteListViewHolder viewHolder, int position, String type) {
        if (type != null) {

            SuperDetailCard card = mDetailCards.get(position);

            if (type.equals(PH.PARAM_EMPTY_DETAIL_CARD)) {
                int greyedBackground = mContext.getResources().getColor(R.color.colorPrimaryGreyed);
                viewHolder.linearLayout.setBackgroundColor(greyedBackground);
            } else {
                int primaryBackground = mContext.getResources().getColor(R.color.colorPrimary);
                viewHolder.linearLayout.setBackgroundColor(primaryBackground);
            }

            viewHolder.header.setText(card.getHeader());
            viewHolder.subtext1.setText(card.getSubtext1());
            viewHolder.subtext2.setText(card.getSubtext2());
            viewHolder.subtext3.setText(card.getSubtext3());
            viewHolder.subtext4.setText(card.getSubtext4());

            int imageResource = mContext.getResources().getIdentifier(card.getIconResource(), null, mContext.getPackageName());
            Drawable image = mContext.getResources().getDrawable(imageResource);
            viewHolder.icon.setImageDrawable(image);

        }
    }

    private void setListener(View view, String type) {

        if (type.equals(PH.PARAM_PEOPLE_DETAIL_CARD)) {
            // TODO: put activity changes here
            view.setOnClickListener(null);
        }

    }




}

