package com.rudie.severin.eventorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rudie.severin.eventorganizer.CardClasses.FoodDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.LocationDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.OtherDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.PeopleDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.TimeDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.TransitDetailCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditDetailActivity extends AppCompatActivity {

    Button cancelButton;
    Button saveButton;
    Button deleteButton;
    EditText editText1, editText2, editText3, editText4, 
            editText5, editText6, editText7, editText8;
    List<EditText> editList;
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
        // array used to populate current spinner index
        EditText[] tempArray = {editText1, editText2, editText3, editText4,
                editText5, editText6, editText7, editText8};
        editList = Arrays.asList(tempArray);

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
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteButton();
            }
        });
        setSpinnerDefault();
    }  // end onCreate

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SEVTEST ", "" + CardHolder.getCurrentDetail().getEnteredText());
        for (int i = 0; i < editList.size(); i++) {
            if (CardHolder.getCurrentDetail().getEnteredText().size() - 1 >= i) {
                editList.get(i).setText(CardHolder.getCurrentDetail().getEnteredText().get(i));
            }
        }
    }

    private void cancelButton() {
        this.finish();
    }

    private void saveButton() {
        String spinText = spinner.getSelectedItem().toString();
        ArrayList<String> enteredText = collectText();
        ArrayList<String> displayText = new ArrayList<>();
        displayText.addAll(enteredText);
        displayText = padToFour(displayText);

        if (spinText.equals("People Attending")) {
            displayText = setEllipsis(displayText);
            PeopleDetailCard newCard = CardHolder.getCurrentEvent().addPeopleDetailCard(displayText.get(0),
                    displayText.get(1), displayText.get(2), displayText.get(3));
            newCard.setEnteredText(enteredText, displayText);
        } else if (spinText.equals("Event Location")) {
            LocationDetailCard newCard = CardHolder.getCurrentEvent().addLocationDetailCard(displayText.get(0),
                    displayText.get(1), displayText.get(2));
            newCard.setEnteredText(enteredText, displayText);
        } else if (spinText.equals("Event Time")) {
            TimeDetailCard newCard = CardHolder.getCurrentEvent().addTimeDetailCard(displayText.get(0),
                    displayText.get(1));
            newCard.setEnteredText(enteredText, displayText);
        } else if (spinText.equals("Food")) {
            displayText = setEllipsis(displayText);
            FoodDetailCard newCard = CardHolder.getCurrentEvent().addFoodDetailCard(displayText.get(0),
                    displayText.get(1), displayText.get(2), displayText.get(3));
            newCard.setEnteredText(enteredText, displayText);
        } else if (spinText.equals("Transportation")) {
            displayText = setEllipsis(displayText);
            TransitDetailCard newCard = CardHolder.getCurrentEvent().addTransitDetailCard(displayText.get(0),
                    displayText.get(1), displayText.get(2), displayText.get(3));
            newCard.setEnteredText(enteredText, displayText);
        } else if (spinText.equals("Other")) {
            displayText = setEllipsis(displayText);
            OtherDetailCard newCard = CardHolder.getCurrentEvent().addOtherDetailCard(displayText.get(0),
                    displayText.get(1), displayText.get(2), displayText.get(3));
            newCard.setEnteredText(enteredText, displayText);
        }
        CardHolder cardHolder = CardHolder.getInstance();
        cardHolder.getCurrentEvent().attachedDetails.remove(cardHolder.getCurrentDetail());
        cardHolder.notifyAdaptersDataChanged();
        cancelButton();
    }

    private ArrayList<String> padToFour(ArrayList<String> displayText) {
        while (displayText.size() < 4) {
            displayText.add("");
        }
        return displayText;
    }

    private ArrayList<String> collectText() {
        SuperDetailCard detail = CardHolder.getCurrentDetail();
        ArrayList<String> enteredText = new ArrayList<>();

        for (EditText editText : editList) {
            if (!editText.getText().toString().isEmpty()){
                enteredText.add(editText.getText().toString());
                }
            }
        enteredText = padToFour(enteredText);
        detail.setSubtext1(enteredText.get(0));
        detail.setSubtext2(enteredText.get(1));
        detail.setSubtext3(enteredText.get(2));
        detail.setSubtext4(enteredText.get(3));

        return enteredText;
    }
    
    private ArrayList<String> setEllipsis(ArrayList<String> enteredText) {
        ArrayList<String> displayText = enteredText;
        if (displayText.size() > 4) {
            displayText.set(3, "...");
        }
        return  (displayText);
    }

    private void setSpinnerDefault() {
//         people, location, time, food, transit, other
        String type = CardHolder.getCurrentDetail().getType();

        if (type.equals(PH.PARAM_PEOPLE_DETAIL_CARD)) {
            spinner.setSelection(0);
        } else if (type.equals(PH.PARAM_LOCATION_DETAIL_CARD)) {
            spinner.setSelection(1);
        } else if (type.equals(PH.PARAM_TIME_DETAIL_CARD)) {
            spinner.setSelection(2);
        } else if (type.equals(PH.PARAM_FOOD_DETAIL_CARD)) {
            spinner.setSelection(3);
        } else if (type.equals(PH.PARAM_TRANSIT_DETAIL_CARD)) {
            spinner.setSelection(4);
        } else if (type.equals(PH.PARAM_OTHER_DETAIL_CARD)) {
            spinner.setSelection(5);
        }
    }

    private void deleteButton() {
        CardHolder.getCurrentEvent().attachedDetails.remove(CardHolder.getCurrentDetail());
        finish();
    }
}
