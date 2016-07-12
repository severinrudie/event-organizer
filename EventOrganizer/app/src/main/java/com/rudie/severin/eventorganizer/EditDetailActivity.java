package com.rudie.severin.eventorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rudie.severin.eventorganizer.CardClasses.EventCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

public class EditDetailActivity extends AppCompatActivity {

    SuperDetailCard currentDetail;
    EventCard parentEvent;
    Button cancelButton;
    Button saveButton;
    Button deleteButton;
    EditText editText1, editText2, editText3, editText4, 
            editText5, editText6, editText7, editText8;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        cancelButton = (Button) findViewById(R.id.EDIT_DETAIL_BUTTON_CANCEL);
        saveButton = (Button) findViewById(R.id.EDIT_DETAIL_BUTTON_SAVE);
        deleteButton = (Button) findViewById(R.id.EDIT_DETAIL_BUTTON_DELETE);
        spinner = (Spinner) findViewById(R.id.EDIT_DETAIL_SPINNER);

        editText1 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT1);
        editText2 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT2);
        editText3 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT3);
        editText4 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT4);
        editText5 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT5);
        editText6 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT6);
        editText7 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT7);
        editText8 = (EditText) findViewById(R.id.EDIT_DETAIL_EDITTEXT8);

        Bundle extras = getIntent().getExtras();
        currentDetail = null;
        if (extras != null) {
            currentDetail = (SuperDetailCard) extras.getSerializable(PH.PARAM_CURRENT_DETAIL);
        }
        parentEvent = currentDetail.getParentEvent();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelButton();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveButton();
            }
        });
        

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("EdtDtlActv:SEVres ", "currentEvent:" + CardHolder.currentEvent);
    }

    private void cancelButton() {
        this.finish();
    }

    private void collectValues() {

    }

    private void saveButton() {

        String spinText = spinner.getSelectedItem().toString();

        String detailType = null;

        if (spinText.equals("People Attending")) {
            //temp
            Log.i("EditDetailActivity:SEV ", "detailsize:" + CardHolder.getCurrentEvent().attachedDetails.size());
//            Log.i("EditDetailActivity:SEV ", "eventName:" + parentEvent.debugName);
//            parentEvent.setDebugName();
            //endtemp
            detailType = PH.PARAM_PEOPLE_DETAIL_CARD;
            CardHolder.getCurrentEvent().addPeopleDetailCard("there", "person", "hi", "hello");
            Log.i("EdtDtlActv:SEVres ", "currentEvent:" + CardHolder.currentEvent);
        } else if (spinText.equals("Event Location")) {
            detailType = PH.PARAM_LOCATION_DETAIL_CARD;
        } else if (spinText.equals("Event Time")) {
            detailType = PH.PARAM_TIME_DETAIL_CARD;
        } else if (spinText.equals("Food")) {
            detailType = PH.PARAM_FOOD_DETAIL_CARD;
        } else if (spinText.equals("Transportation")) {
            detailType = PH.PARAM_TRANSIT_DETAIL_CARD;
        } else if (spinText.equals("Other")) {
            detailType = PH.PARAM_OTHER_DETAIL_CARD;
        }
//        parentEvent.attachedDetails.remove(currentDetail);
//        parentEvent.verifyThatEmptyDetailExists();

//        cancelButton();
    }
}
