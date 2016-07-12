package com.rudie.severin.eventorganizer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

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
    Button buttonSaveTitle;
    EditText editTextSaveTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mContext = this.getApplicationContext();

        cardHolder = CardHolder.getInstance();;
        CardHolder.getCurrentEvent().verifyThatEmptyDetailExists();

        ArrayList<SuperDetailCard> eventDetails = CardHolder.getCurrentEvent().getAttachedDetails();
        mGridView = (GridView) findViewById(R.id.detailsGridView);
        buttonSaveTitle = (Button) findViewById(R.id.buttonNewEventTitle);
        editTextSaveTitle = (EditText) findViewById(R.id.edittextNewEventTitle);
        mDetailsAdapter = new DetailsAdapter(mContext, eventDetails);
        if (mGridView != null) {
            mGridView.setAdapter(mDetailsAdapter);
        }
        loggy = new SimpleLogger("DetailsActivity:" + CardHolder.getCurrentEvent().getType());
        cardHolder.passDetailsAdapter(mDetailsAdapter);

        buttonSaveTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEventTitle();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("DetailsActivity:SEVres ", "detailsize:" + CardHolder.getCurrentEvent().attachedDetails.size());
        Log.i("DetailsActivity:SEVres ", "cardholder.eventholder:" + cardHolder.getEventHolder().size());
        mDetailsAdapter.notifyDataSetChanged();
        Log.i("DetailsActivity:SEVres ", "currentEvent:" + CardHolder.getCurrentEvent());
    }

    public void saveEventTitle() {
        Log.i("SEVTEST ", "iyrcutc" + editTextSaveTitle.getText().toString());
        if (editTextSaveTitle.getText().toString().length() != 0) {
            CardHolder.getCurrentEvent().setHeader(editTextSaveTitle.getText().toString());
            editTextSaveTitle.setText("");
            String message = "New title saved";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        } else {
            String message = "Please enter text before saving";
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

}
