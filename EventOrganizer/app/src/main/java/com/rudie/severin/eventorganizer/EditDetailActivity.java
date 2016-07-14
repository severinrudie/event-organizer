package com.rudie.severin.eventorganizer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

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
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditDetailActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button cancelButton, saveButton, deleteButton, setTime, setDate;
    EditText editText1, editText2, editText3, editText4,
            editText5, editText6, editText7, editText8;
    TextView dateView, timeView;
    List<EditText> editList;
    Spinner spinner;
    boolean timeDetailIsSet, locationDetailIsSet;
    Calendar calendar;
    int year, month, day;
    static String date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_detail);

        cancelButton = (Button) findViewById(R.id.EDIT_DETAIL_BUTTON_CANCEL);
        saveButton = (Button) findViewById(R.id.EDIT_DETAIL_BUTTON_SAVE);
        deleteButton = (Button) findViewById(R.id.EDIT_DETAIL_BUTTON_DELETE);
        setTime = (Button) findViewById(R.id.button_set_time);
        setDate = (Button) findViewById(R.id.button_set_date);
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
                confirmDeletion();
            }
        });
        setSpinnerDefault();

        dateView = (TextView) findViewById(R.id.TIME_EDIT_TEXTVIEW1);
        timeView = (TextView) findViewById(R.id.TIME_EDIT_TEXTVIEW2);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        date = CardHolder.getCurrentDetail().getDate();
        time = CardHolder.getCurrentDetail().getTime();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                verifyDetailSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // not used
            }
        });

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDate();
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTime();
            }
        });

    }  // end onCreate

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < editList.size(); i++) {
            if (CardHolder.getCurrentDetail().getEnteredText().size() - 1 >= i) {
                editList.get(i).setText(CardHolder.getCurrentDetail().getEnteredText().get(i));
            }
        }
        verifyDetailSelection();
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
            if (date.equals("") || time.equals("")) {
                String message = "Enter date and time before saving";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                return;
            } else {
                displayText.clear();
                displayText.add(generateDateTime());
                enteredText.clear();
                enteredText.add(time);
                enteredText.add(date);
                TimeDetailCard newCard = CardHolder.getCurrentEvent().addTimeDetailCard(time, date);
                newCard.setEnteredText(enteredText, displayText);
            }
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
            if (!editText.getText().toString().isEmpty()) {
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
        return (displayText);
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

    private void confirmDeletion() {
        deleteButton.setText("Confirm Deletion?");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteButton();
            }
        });
    }

    private void deleteButton() {
        CardHolder.getCurrentEvent().attachedDetails.remove(CardHolder.getCurrentDetail());
        CardHolder.getCurrentEvent().verifyThatEmptyDetailExists();
        finish();
    }

    private void setTimeDetail() {

        for (int i = 0; i < editList.size(); i++) {
            editList.get(i).setVisibility(View.GONE);
        }
        dateView.setVisibility(View.VISIBLE);
        timeView.setVisibility(View.VISIBLE);
        setTime.setVisibility(View.VISIBLE);
        setDate.setVisibility(View.VISIBLE);
    }

    private void removeCustomDetail() {
        for (int i = 0; i < editList.size(); i++) {
            editList.get(i).setVisibility(View.VISIBLE);
        }
        dateView.setVisibility(View.GONE);
        timeView.setVisibility(View.GONE);
        setTime.setVisibility(View.GONE);
        setDate.setVisibility(View.GONE);
    }

    private void setLocationDetail() {

        for (int i = 0; i < 2; i++) {
            editList.get(i).setVisibility(View.VISIBLE);
        }
        for (int i = 2; i < editList.size(); i++) {
            editList.get(i).setVisibility(View.GONE);
        }
        dateView.setVisibility(View.GONE);
        timeView.setVisibility(View.GONE);
        setTime.setVisibility(View.GONE);
        setDate.setVisibility(View.GONE);
    }

//    DatePicker code below

    @SuppressWarnings("deprecation")
    public void setDate() {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        date = new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year).toString();
        dateView.setText(date);
    }

//    TimePicker code below

    // calls to show TimePicker dialog
    public void setTime() {
        DialogFragment new_fragment = new TimePickerFragment();
        new_fragment.show(getSupportFragmentManager(), "timePicker");

    }

    public void onTimeSet(TimePicker view, int hour, int minute) {
        String Hour;
        // rounds minute down to the nearest 5
        String displayTime;
        minute = Math.round(minute / 5) * 5;
        if (hour > 12) {
            Hour = String.valueOf(hour - 12);
        } else {
            Hour = String.valueOf(hour);
        }

        // rules for making display text more human readable
        String Minute = String.valueOf(minute);
        if (Hour.equals("0")) {
            Hour = "00";
        }
        if (Minute.equals("0")) {
            Minute = "00";
        }

        if (Minute.length() < 2) {
            Minute = "0" + Minute;
        }

        if (hour == 0 && minute == 0) {
            displayTime = "Midnight";
        } else if (hour < 12) {
            String temporary_text = Hour + ":" + Minute + " AM";
            displayTime = temporary_text;
        } else if (hour == 12 && minute == 0) {
            displayTime = "Noon";
        } else {
            String temporary_text = Hour + ":" + Minute + " PM";
            displayTime = temporary_text;
        }
        CardHolder.getCurrentDetail().setTime(displayTime);
        timeView.setText(displayTime);
        time = displayTime;
    }

    public String generateDateTime() {
        // Regex insanity.  Works, but replaced with lines 360-361
//        Pattern pattern = Pattern.compile("(\\d+:)(\\d+)(\\s..)");
//        Matcher matcher = pattern.matcher(time);
//
//        if (matcher.find()) {
//            String found0 = ("Found value: " + matcher.group(0));
//            String found1 = ("Found value: " + matcher.group(1));
//            String found2 = ("Found value: " + matcher.group(2));
//            String found3 = ("Found value: " + matcher.group(3));
//
//            String wait = "wait here";
//        } else {
//            String noFind = ("nothing found");
//        }
//
//
//        StringBuilder builder = new StringBuilder(matcher.group(2));
//        if (matcher.group(2).length() < 2) {
//            builder.append(matcher.group(2));
//            builder.replace(builder.length() - 2, builder.length() - 1, "0");
//            time = matcher.group(1) + builder.toString() + matcher.group(3);
//        }
            return (time + ", " + date);
    }

    // overloaded method, accepts a position, or will determine based on card type
    public void verifyDetailSelection(int position) {
        if (position == 2 && !timeDetailIsSet) {
            setTimeDetail();
            timeDetailIsSet = true;
        } else if (position != 2 && timeDetailIsSet) {
            removeCustomDetail();
            timeDetailIsSet = false;
        }
        if (position == 1 && !locationDetailIsSet) {
            setLocationDetail();
            locationDetailIsSet = true;
        } else if (position != 1 && locationDetailIsSet && !timeDetailIsSet) {
            removeCustomDetail();
            locationDetailIsSet = false;
        }
    }

    // overloaded method, accepts a position, or will determine based on card type
    public void verifyDetailSelection() {
        if (!(CardHolder.getCurrentDetail().getType().equals(PH.PARAM_TIME_DETAIL_CARD))) {
            removeCustomDetail();
            timeDetailIsSet = false;
        } else {
            setTimeDetail();
            timeDetailIsSet = true;
        }
        if (CardHolder.getCurrentDetail().getType().equals(PH.PARAM_TIME_DETAIL_CARD)) {
            setTimeDetail();
            timeDetailIsSet = true;
            locationDetailIsSet = false;
        } else if (CardHolder.getCurrentDetail().getType().equals(PH.PARAM_LOCATION_DETAIL_CARD)) {
            setLocationDetail();
            timeDetailIsSet = false;
            locationDetailIsSet = true;
        } else {
            removeCustomDetail();
            locationDetailIsSet = false;
            timeDetailIsSet = false;
        }

    }
}