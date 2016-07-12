package com.rudie.severin.eventorganizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.rudie.severin.eventorganizer.CardClasses.PeopleDetailCard;
import com.rudie.severin.eventorganizer.CardClasses.SuperDetailCard;
import com.rudie.severin.eventorganizer.UtilityClasses.CardHolder;
import com.rudie.severin.eventorganizer.UtilityClasses.PH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditDetailActivity extends AppCompatActivity {

    SuperDetailCard currentDetail;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SEVTEST ", "" + CardHolder.getCurrentDetail().getEnteredText());
        for (int i = 0; i < editList.size(); i++) {
            if (CardHolder.getCurrentDetail().getEnteredText().size() - 1 >= i) {
                editList.get(i).setText(CardHolder.getCurrentDetail().getEnteredText().get(i));
            }
        }
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
        ArrayList<String> enteredText = collectText();

        if (spinText.equals("People Attending")) {
            detailType = PH.PARAM_PEOPLE_DETAIL_CARD;
            PeopleDetailCard newCard = CardHolder.getCurrentEvent().addPeopleDetailCard(enteredText.get(0),
                    enteredText.get(1), enteredText.get(2), enteredText.get(3));
            newCard.setEnteredText(enteredText);
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


        CardHolder cardHolder = CardHolder.getInstance();
        cardHolder.getCurrentEvent().attachedDetails.remove(cardHolder.getCurrentDetail());
        cardHolder.notifyAdaptersDataChanged();
        cancelButton();

    }

    private ArrayList<String> collectText() {
        SuperDetailCard detail = CardHolder.getCurrentDetail();
//        ArrayList<String> enteredText = detail.getEnteredText();
        ArrayList<String> enteredText = new ArrayList<>();

        for (EditText editText : editList) {
            if (!editText.getText().toString().isEmpty()){
                enteredText.add(editText.getText().toString());
            }
            // if more than 4 items exist, the final display should be an ellipsis
            }
            while (enteredText.size() < 4) {
                enteredText.add("");
            }
            if (enteredText.size() > 4) {
                enteredText.set(3, "...");
        }

            detail.setSubtext1(enteredText.get(0));
            detail.setSubtext2(enteredText.get(1));
            detail.setSubtext3(enteredText.get(2));
            detail.setSubtext4(enteredText.get(3));

        return enteredText;
    }

}
