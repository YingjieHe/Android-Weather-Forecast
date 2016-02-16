package com.example.heyin_000.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

public class result extends AppCompatActivity {
    Intent intent;
    JSONObject jsonObj, currently;
    String json;
    String street;
    String city;
    String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        intent = getIntent();
        json  = intent.getStringExtra("jsonString");
        try
        {
            jsonObj = new JSONObject(json);
            currently = jsonObj.optJSONObject("currently");
            street = jsonObj.getString("streetaddress");
            city = jsonObj.getString("city");
            state = jsonObj.getString("state");

            ImageView img= (ImageView) findViewById(R.id.result_img);
            img.setImageResource(getImgId(currently.getString("iconurl")));
            ((TextView)findViewById(R.id.result_summary)).setText(currently.getString("summary"));
            ((TextView)findViewById(R.id.result_temperature)).setText(currently.getString("temperature"));
            ((TextView)findViewById(R.id.result_lowhight)).setText("L:"+currently.getString("temperatureMin")+" | H:"+currently.getString("temperatureMax"));
            ((TextView)findViewById(R.id.result_Precipitation)).setText(currently.getString("precipIntensity"));
            ((TextView)findViewById(R.id.result_chanceofrain)).setText(currently.getString("precipProbability"));
            ((TextView)findViewById(R.id.result_windspeed)).setText(currently.getString("windSpeed"));
            ((TextView)findViewById(R.id.result_dewpoint)).setText(currently.getString("dewPoint"));
            ((TextView)findViewById(R.id.result_Humidity)).setText(currently.getString("humidity"));
            ((TextView)findViewById(R.id.result_Visibility)).setText(currently.getString("visibility"));
            ((TextView)findViewById(R.id.result_Sunrise)).setText(currently.getString("sunriseTime"));
            ((TextView)findViewById(R.id.result_Sunset)).setText(currently.getString("sunsetTime"));

        }
        catch (Exception e)
        {
            Log.i("exception", e.getMessage());
        }
    }

    private int getImgId(String iconurl)
    {
        switch (iconurl)
        {
            case "clear.png":
                return R.drawable.clear;
            case "clear_night.png":
                return R.drawable.clear_night;
            case "cloud_day.png":
                return R.drawable.cloud_day;
            case "cloud_night.png":
                return R.drawable.cloud_night;
            case "cloudy.png":
                return R.drawable.cloudy;
            case "fog.png":
                return R.drawable.fog;
            case "rain.png":
                return R.drawable.rain;
            case "sleet.png":
                return R.drawable.sleet;
            case "snow.png":
                return R.drawable.snow;
            case "wind.png":
                return R.drawable.wind;
            default:
                return R.drawable.clear;
        }
    }

    public void viewmap(View view) {
        Intent intent = new Intent(this, map.class);
        intent.putExtra("jsonString",json);
        startActivity(intent);
    }

    public void moredetails(View view)
    {
        Intent intent = new Intent(this, details.class);
        intent.putExtra("jsonString",json);
        startActivity(intent);
    }

}
