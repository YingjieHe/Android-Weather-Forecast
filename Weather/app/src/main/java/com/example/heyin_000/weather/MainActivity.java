package com.example.heyin_000.weather;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] states = new String[]{"Select","AL",
                "AK",
                "AZ",
                "AR",
                "CA",
                "CO",
                "CT",
                "DE",
                "DC",
                "FL",
                "GA",
                "HI",
                "ID",
                "IL",
                "IN",
                "IA",
                "KS",
                "KY",
                "LA",
                "ME",
                "MD",
                "MA",
                "MI",
                "MN",
                "MS",
                "MO",
                "MT",
                "NE",
                "NV",
                "NH",
                "NJ",
                "NM",
                "NY",
                "NC",
                "ND",
                "OH",
                "OK",
                "OR",
                "PA",
                "RI",
                "SC",
                "SD",
                "TN",
                "TX",
                "UT",
                "VT",
                "VA",
                "WA",
                "WV",
                "WI",
                "WY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, states);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner);
        dropdown.setAdapter(adapter);
    }

    public void search(View view){
        String street = ((EditText)findViewById(R.id.street)).getText().toString().trim();
        String city = ((EditText)findViewById(R.id.city)).getText().toString().trim();
        String state = ((Spinner)findViewById(R.id.spinner)).getSelectedItem().toString();
        boolean flag = false;
        if(street.equals("")){
            ((TextView)findViewById(R.id.streetcheck)).setVisibility(view.VISIBLE);
            flag = true;
        }
        if(city.equals("")){
            ((TextView)findViewById(R.id.citycheck)).setVisibility(view.VISIBLE);
            flag = true;
        }
        if(state.equals("Select")){
            ((TextView)findViewById(R.id.statecheck)).setVisibility(view.VISIBLE);
            flag = true;
        }
        if(flag) return;
        String degree;
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        RadioButton radioButton = (RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        int radioButtonID = radioGroup.indexOfChild(radioButton);
        if(radioButtonID == 0){
            degree = "F";
        }
        else{
            degree = "C";
        }
    }

    public void aboutinformation(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    public void gotoforecast(View view)
    {
        Uri uriUrl = Uri.parse("http://forecast.io/");
        Intent launch = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launch);
    }

    public void clear(View view){
        ((TextView)findViewById(R.id.streetcheck)).setVisibility(view.GONE);
        ((TextView)findViewById(R.id.citycheck)).setVisibility(view.GONE);
        ((TextView)findViewById(R.id.statecheck)).setVisibility(view.GONE);
        ((TextView)findViewById(R.id.street)).setText("");
        ((TextView)findViewById(R.id.city)).setText("");
        ((Spinner)findViewById(R.id.spinner)).setSelection(0);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        radioGroup.check(R.id.F);
    }
}
