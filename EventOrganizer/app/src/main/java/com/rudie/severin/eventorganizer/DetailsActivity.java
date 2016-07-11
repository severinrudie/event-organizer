package com.rudie.severin.eventorganizer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.GridView;

import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.DetailsAdapter;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;
import com.rudie.severin.eventorganizer.UtilityClasses.SimpleLogger;

public class DetailsActivity extends AppCompatActivity {

    GridView mGridView;
    DetailsAdapter mDetailsAdapter;
    SimpleLogger loggy;
    CardHolder cardHolder;
    EventCard card;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mContext = this.getApplicationContext();
        Bundle extras = getIntent().getExtras();
        card = null;
        cardHolder = null;

        if (extras != null) {
            card = (EventCard) extras.getSerializable(PH.PARAM_INTENT_CARD);
            cardHolder = (CardHolder) extras.getSerializable(PH.PARAM_INTENT_CARDHOLDER);
        }

        mGridView = (GridView) findViewById(R.id.detailsGridView);
        mDetailsAdapter = new DetailsAdapter(mContext, cardHolder);
        mGridView.setAdapter(mDetailsAdapter);
        loggy = new SimpleLogger("DetailsActivity:" + card.getType());
        cardHolder.passDetailsAdapter(mDetailsAdapter);

    }
}
