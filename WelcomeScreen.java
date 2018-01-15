package com.a1108software.brian.fly_safe;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

/**
 * Created by brian on 22/06/2017.
 */

public class WelcomeScreen extends Activity {

    SharedPreferences mPrefs;
    final String screenShown = "welcomeScreenShown";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome To Fly-Safe");
        builder.setMessage("**Warning**\nThis application is intended to help locate areas which are safe to fly rc models, " +
                "this being said good judgement on behalf of users is also required and so we ask you to be cautious when choosing your flying site");

        builder.setPositiveButton("Got It", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putBoolean(screenShown, true);
                editor.commit(); // Save the preference
                Intent intent = new Intent(WelcomeScreen.this,MainMenuActivity.class);
                startActivity(intent);

            }
        });

        builder.show();
    }
}
