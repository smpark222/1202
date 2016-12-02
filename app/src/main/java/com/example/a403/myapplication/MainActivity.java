package com.example.a403.myapplication;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button start, complete;
    RadioGroup group;
    RadioButton radioButton, radioButton2;
    CalendarView date;
    TimePicker time;
    TextView result;
    String result_a;
    String result_b;
    int a= 0, b = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        start = (Button) findViewById(R.id.start);
        complete = (Button) findViewById(R.id.complete);
        group = (RadioGroup) findViewById(R.id.group);
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        date = (CalendarView) findViewById(R.id.date);
        time = (TimePicker) findViewById(R.id.time);
        result = (TextView) findViewById(R.id.result);

        start.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                group.setVisibility(VISIBLE);
                chronometer.start();
            }
        });
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int i) {
                switch (i) {
                    case R.id.date:
                        date.setVisibility(VISIBLE);
                        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                            @Override
                            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                                result_a = (year + "년" + month + "월" + dayOfMonth + "일" );
                                a = 1;
                            }
                        });
                        break;
                    case R.id.time:
                        time.setVisibility(VISIBLE);
                        time.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                            @Override
                            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                                result_b = (hourOfDay + "시" + minute + "분");
                                b = 1;
                            }
                        });
                        break;
                }

                if(a == 1 && b== 1){
                    complete.setVisibility(VISIBLE);
                }
            }
        });
        complete.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                a = b = 0;
                result.setText(result_a + result_b);
                chronometer.stop();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
