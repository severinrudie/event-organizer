package com.rudie.severin.eventorganizer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.DetailsAdapter;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;
import com.rudie.severin.eventorganizer.UtilityClasses.SimpleLogger;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    GridView mGridView;
    DetailsAdapter mDetailsAdapter;
    SimpleLogger loggy;
    CardHolder cardHolder;
    EventCard currentEvent;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mContext = this.getApplicationContext();
        Bundle extras = getIntent().getExtras();
        currentEvent = null;
        cardHolder = null;

        if (extras != null) {
            currentEvent = (EventCard) extras.getSerializable(PH.PARAM_INTENT_CARD);
            cardHolder = (CardHolder) extras.getSerializable(PH.PARAM_INTENT_CARDHOLDER);
        }
        currentEvent.verifyThatEmptyDetailExists();

        ArrayList<SuperDetailCard> eventDetails = currentEvent.getAttachedDetails();
        mGridView = (GridView) findViewById(R.id.detailsGridView);
        mDetailsAdapter = new DetailsAdapter(mContext, eventDetails);
        if (mGridView != null) {
            mGridView.setAdapter(mDetailsAdapter);
        }
        loggy = new SimpleLogger("DetailsActivity:" + currentEvent.getType());
        cardHolder.passDetailsAdapter(mDetailsAdapter);
        mDetailsAdapter.notifyDataSetChanged();

    }


}
