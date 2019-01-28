package com.example.sharedpreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /* ------------------------------------
           Menu
       ------------------------------------ */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.settings :
                Log.i("Menu Item selected: ", "Settings");

            case R.id.help :
                Log.i("Menu Item selected: ", "Help");

            default :
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ------------------------------------
                   Shared Preferences
           ------------------------------------ */
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.sharedpreferences", Context.MODE_PRIVATE); //Initialize shared preferences

//        sharedPreferences.edit().putString("username", "Rob").apply(); //saves to shared Preferences
//        String username = sharedPreferences.getString("username", ""); // read value

        ArrayList<String> friends = new ArrayList<String>();

        friends.add("Monica");
        friends.add("Chandler");
        try {
            sharedPreferences.edit().putString("friends", ObjectSerializer.serialize(friends)).apply();

        }catch(Exception e) {
            e.printStackTrace();
        }

        try {
            ArrayList<String> newFriends = new ArrayList<String>();
            newFriends = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<String>())));
            Log.i("New friends ", newFriends.toString());

        }catch(Exception e) {
            e.printStackTrace();
        }


        /* ------------------------------------
                   Alert Dialogs
           ------------------------------------ */
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you Sure?")
                .setMessage("Do you definetely want to do this?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "It's done!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
