package com.example.android.bloodbank;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class donorActivity extends MainActivity {
    Calendar myCalendar = Calendar.getInstance();
    private RadioGroup radioGroup1, radioGroup2;
    private RadioGroup bloodGroup;
    private RadioButton radioButton;


    private RadioGroup.OnCheckedChangeListener listener1 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup2.setOnCheckedChangeListener(null);
            radioGroup2.clearCheck();
            radioGroup2.setOnCheckedChangeListener(listener2);
        }
    };

    private RadioGroup.OnCheckedChangeListener listener2 = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            radioGroup1.setOnCheckedChangeListener(null);
            radioGroup1.clearCheck();
            radioGroup1.setOnCheckedChangeListener(listener1);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donor); // show UI


        radioGroup1 = (RadioGroup) findViewById(R.id.radGroup1);
        radioGroup2 = (RadioGroup) findViewById(R.id.radGroup2);


        radioGroup1.setOnCheckedChangeListener(listener1);

        radioGroup2.setOnCheckedChangeListener(listener2);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        EditText edittext = (EditText) findViewById(R.id.date);
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(donorActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        TextView send = (TextView) findViewById(R.id.btn_send);
        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (view == view.findViewById(R.id.btn_send)) {

                    EditText name = (EditText) findViewById(R.id.editname);
                    String strname = name.getText().toString();

                    EditText id = (EditText) findViewById(R.id.editid);
                    String strid = id.getText().toString();


                    RadioButton maleRadioButton = (RadioButton) findViewById(R.id.male);
                    boolean isMale = maleRadioButton.isChecked();
                    Log.v("MainActivity", "Gender:" + isMale);

                    RadioButton femaleRadioButton = (RadioButton) findViewById(R.id.female);
                    boolean isFemale = femaleRadioButton.isChecked();
                    Log.v("MainActivity", "Gender:" + isFemale);


                    // get selected radio button from radioGroup
                    String selectedId = maleRadioButton.getText().toString();
                    String selectFemale = femaleRadioButton.getText().toString();

                    EditText age = (EditText) findViewById(R.id.editage);
                    String strage = age.getText().toString();

                    EditText date = (EditText) findViewById(R.id.date);
                    String strdate = date.getText().toString();

                    EditText hospital = (EditText) findViewById(R.id.edithospital);
                    String strhosp = hospital.getText().toString();

                    EditText city = (EditText) findViewById(R.id.editcity);
                    String strcity = city.getText().toString();


                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    String result;
                    if (isMale)
                        result = "Name: " + strname + System.getProperty("line.separator")
                                + "ID: " + strid + System.getProperty("line.separator")
                                + "Gender: " + selectedId + System.getProperty("line.separator")
                                + "Age: " + strage + System.getProperty("line.separator")
                                + "Date: " + strdate + System.getProperty("line.separator")
                                + "Hospital: " + strhosp + System.getProperty("line.separator")
                                + "City: " + strcity + System.getProperty("line.separator");
                    else
                        result = "Name: " + strname + System.getProperty("line.separator")
                                + "ID: " + strid + System.getProperty("line.separator")
                                + "Gender:" + selectFemale + System.getProperty("line.separator")
                                + "Age: " + strage + System.getProperty("line.separator")
                                + "Date: " + strdate + System.getProperty("line.separator")
                                + "Hospital: " + strhosp + System.getProperty("line.separator")
                                + "City: " + strcity + System.getProperty("line.separator");
                    sendIntent.putExtra(Intent.EXTRA_TEXT, result);
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            }
        });
    }

    private void updateLabel() {
        EditText edittext = (EditText) findViewById(R.id.date);
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }
}

