package com.a1108software.brian.fly_safe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by brian on 15/01/2018.
 */

public class StartUpLogic extends Activity {

    SharedPreferences mPrefs;
    final String screenShown = "welcomeScreenShown";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        // second argument is the default to use if the preference can't be found
        Boolean welcomeScreenShown = mPrefs.getBoolean(screenShown, false);

        if (welcomeScreenShown) {

            Intent myIntent = new Intent(StartUpLogic.this, MainActivity.class);
            startActivity(myIntent);
            finish();
        }
        else
        {
            Intent myIntent = new Intent(StartUpLogic.this, WelcomeScreen.class);
            startActivity(myIntent);
        }

    }
}
