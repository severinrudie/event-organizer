package com.rudie.severin.eventorganizer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mContext = this.getApplicationContext();

        cardHolder = CardHolder.getInstance();;
        CardHolder.getCurrentEvent().verifyThatEmptyDetailExists();

        ArrayList<SuperDetailCard> eventDetails = CardHolder.getCurrentEvent().getAttachedDetails();
        mGridView = (GridView) findViewById(R.id.detailsGridView);
        mDetailsAdapter = new DetailsAdapter(mContext, eventDetails);
        if (mGridView != null) {
            mGridView.setAdapter(mDetailsAdapter);
        }
        loggy = new SimpleLogger("DetailsActivity:" + CardHolder.getCurrentEvent().getType());
        cardHolder.passDetailsAdapter(mDetailsAdapter);

        //temp
        Log.i("DetailsActivity:SEVcre ", "detailsize:" + CardHolder.getCurrentEvent().attachedDetails.size());
        Log.i("DetailsActivity:SEVcre ", "cardholder.eventholder:" + cardHolder.getEventHolder().size());
//        Log.i("DetailsActivity:SEV ", "eventName:" + currentEvent.debugName);
        //endtemp

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("DetailsActivity:SEVres ", "detailsize:" + CardHolder.getCurrentEvent().attachedDetails.size());
        Log.i("DetailsActivity:SEVres ", "cardholder.eventholder:" + cardHolder.getEventHolder().size());
        mDetailsAdapter.notifyDataSetChanged();
        Log.i("DetailsActivity:SEVres ", "currentEvent:" + CardHolder.getCurrentEvent());
    }
}
