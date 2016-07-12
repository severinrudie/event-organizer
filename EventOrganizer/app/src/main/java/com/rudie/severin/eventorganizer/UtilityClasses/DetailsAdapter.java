package com.rudie.severin.eventorganizer.UtilityClasses;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.EditDetailActivity;
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
//    CardHolder cardHolder;
    EventCard parentCard;

    public DetailsAdapter(Context mContext, ArrayList<SuperDetailCard> detailCards) {
        this.mContext = mContext;
        this.mDetailCards = detailCards;
        logger = new SimpleLogger("DetailsAdapter");
    }

    public void setDetailCards(ArrayList<SuperDetailCard> mDetailCards) {
        this.mDetailCards = mDetailCards;
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

        SuperDetailCard card = mDetailCards.get(position);
        EventCard parentEvent = card.getParentEvent();

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
        setListener(v, type, card, parentEvent);

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
                Drawable backgroundGreyed = mContext.getResources().getDrawable(R.drawable.black_border_greyed);
                viewHolder.linearLayout.setBackground(backgroundGreyed);
            } else {
                Drawable primaryBackground = mContext.getResources().getDrawable(R.drawable.black_border);
                viewHolder.linearLayout.setBackground(primaryBackground);
            }
            viewHolder.header.setText(card.getHeader());
            viewHolder.subtext1.setText(card.getSubtext1());
            viewHolder.subtext2.setText(card.getSubtext2());
            viewHolder.subtext3.setText(card.getSubtext3());
            if (card.getEnteredText().size() > 4){
                viewHolder.subtext4.setText("...");
            } else {
                viewHolder.subtext4.setText(card.getSubtext4());
            }

            int imageResource = mContext.getResources().getIdentifier(card.getIconResource(), null, mContext.getPackageName());
            Drawable image = mContext.getResources().getDrawable(imageResource);
            viewHolder.icon.setImageDrawable(image);
        }
    }

    private void setListener(View view, String type, final SuperDetailCard detailCard,
                             EventCard parentEvent) {

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CardHolder.setCurrentDetail(detailCard);
                    Intent intent = new Intent(mContext, EditDetailActivity.class);
                    //TODO: Ask the instructors about this
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    //TODO: android.util.AndroidRuntimeException: Calling startActivity() from
                    //TODO: outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag.
                    mContext.startActivity(intent);
                }
            });

        }






}

