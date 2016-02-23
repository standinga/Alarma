package io.bastawa.alarma;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean mStarted = true;
    private boolean mIgnore = false;
    Button startstopBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        startTime = System.currentTimeMillis();

        startstopBtn = (Button)findViewById(R.id.button);
        startstopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
      startStop();

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });
    }

    long startTime;

    private void countDown() {
        Random randomGenerator = new Random();
        mIgnore = true;

        int randomInt = randomGenerator.nextInt(10);
        int randomTime = 1000 * randomInt;
        String t = "random time " + randomTime;

        Log.i("bojan" , t);
        new CountDownTimer(randomTime, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.i("bojan", "tick");
            }

            public void onFinish() {
                mIgnore = false;
                startStop();
            }
        }.start();
    }

    private void startStop() {
        if (!mIgnore) {
            if (!mStarted) {
                startTime = System.currentTimeMillis();
                startstopBtn.setBackgroundColor(0xFFFF0022);
                startstopBtn.setText("ALARMA");
                mStarted = true;
                String logText = "clicked, started: " + mStarted;
                Log.i("bojan", logText);
            } else {
                TextView textView = (TextView) findViewById(R.id.timeText);
                long stopTime = System.currentTimeMillis();
                long timePassed = stopTime - startTime;
                String timeText = "It took you: " + timePassed + " ms ";
                String time = timePassed + " ms";
                textView.setText(timeText);
                startstopBtn.setBackgroundColor(0xFF2233FF);
                startstopBtn.setText("WAIT" + time);
                mStarted = false;
                String logText = "clicked, started: " + mStarted;
                Log.i("Button", logText);
                countDown();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
