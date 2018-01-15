package com.a1108software.brian.fly_safe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {

    //set the icon titles in the list
    private final String[] menuItems = new String[]{"Can I Fly Here?", "Clubs and Flying Sites", "My Models", "Rules and important information", "Drone Registration"};
    //Set the icon adjacent to their title counterparts
    private final int[] menuImages = new int[]{R.drawable.help_icon, R.drawable.flag_icon, R.drawable.rc_icon, R.drawable.rules_info_icon5, R.drawable.registraion_icon};

    private GridView gridView;
    private Context mContext = this;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String mUserId;
   /* private ArrayList<Club> clubs = new ArrayList<Club>();
    private ArrayList<AirportMarker> aMkr = new ArrayList<AirportMarker>();
    private ArrayList<Model> mods = new ArrayList<Model>();*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu1);

        // Initialize Firebase Auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        //Get reference to the database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Check if the user is logged in
        if (mFirebaseUser == null) {
            // Not logged in, launch the Log In activity
            loadLogInView();
        } else {

            //Get the users id
            mUserId = mFirebaseUser.getUid();

            //Get all the users models
            /*Runnable r = new Runnable() {
                @Override
                public void run() {
                    mods = getModels(mUserId);
                    //Toast.makeText(context, mods.toString(), Toast.LENGTH_LONG).show();
                }
            };
            Thread t = new Thread(r);
            t.start();
            try {
                t.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //Get all the users models
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    clubs = getClubs();
                    //Toast.makeText(context, mods.toString(), Toast.LENGTH_LONG).show();
                }
            };
            Thread thread = new Thread(run);
            thread.start();
            try {
                thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //
            //Get all the airport locations and details for distributing to other classes and place them in a list
            new Thread(new Runnable() {
                public void run() {
                    // Use Firebase to get airport values.
                    mDatabase.child("Airports").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                            if (dataSnapshot.child("name").getValue() != null) {
                                AirportMarker item = new AirportMarker();
                                item.setName((String) dataSnapshot.child("name").getValue());

                                try {
                                    double nLat = (double) dataSnapshot.child("lat").getValue();
                                    item.setLat(nLat);
                                } catch (NumberFormatException e) {
                                    System.out.println((String) dataSnapshot.child("lat").getValue());
                                }

                                try {
                                    double nLon = (double) dataSnapshot.child("long").getValue();
                                    item.setLon(nLon);
                                } catch (NumberFormatException e) {
                                    System.out.println((String) dataSnapshot.child("lat").getValue());
                                }


                                aMkr.add(item);
                            }
                        }

                        @Override
                        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onChildRemoved(DataSnapshot dataSnapshot) {
                        }

                        @Override
                        public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                }
            }).start();

*/
            //Set up the grid-view
            gridView = (GridView) findViewById(R.id.gridView1);

            //Set up the adapter for the grid list passing the titles and icons to be placed in the adapter
            gridView.setAdapter(new MainMenuListAdapter(this, menuItems, menuImages));

            //Set the onClickListener to determine which item is chosen when clicked
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {
                        //Create an intent and put the airport locations inside then start the activity
                        /*Intent intent = new Intent(mContext.getApplicationContext(), MapsActivity.class);
                        intent.putExtra("AllAirports_dataProvider", aMkr);
                        Bundle b = new Bundle();
                        b.putSerializable("AllModels_dataProvider", mods);
                        intent.putExtras(b);
                        intent.putExtra("Clubs_dataProvider", clubs);
                        mContext.startActivity(intent);*/
                    } else if (position == 1) {
                        //Create an intent and put the airport locations inside then start the activity
                        /*Intent intent = new Intent(mContext.getApplicationContext(), ClubsAndSitesNew.class);
                        intent.putExtra("AllAirports_dataProvider", aMkr);
                        intent.putExtra("Clubs_dataProvider", clubs);
                        mContext.startActivity(intent);*/
                    } else if (position == 2) {
                        //Create an intent and put the users models inside then start the activity
                        /*Intent modIntent = new Intent(mContext, Models.class);
                        Bundle b = new Bundle();
                        b.putSerializable("AllModels_dataProvider", mods);
                        modIntent.putExtras(b);
                        mContext.startActivity(modIntent);*/
                    } else if (position == 3) {
                        //Create an intent then start the activity
                       /* Intent infoIntent = new Intent(mContext.getApplicationContext(), InfoMenuActivity.class);
                        mContext.startActivity(infoIntent);*/
                    } else if (position == 4) {
                        //Create an intent and start the activity to initialise the web browser and direct to the registration site
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.iaa.ie/general-aviation/drones/drone-registration"));
                        mContext.startActivity(browserIntent);
                    }


                }
            });


        }


    }

    //Create options menu for logout function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds clubs to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Created when the option menu item is selected
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        // Handle action bar item clicks here
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mFirebaseAuth.signOut();
            loadLogInView();
        }

        return super.onOptionsItemSelected(item);
    }

    //Load the login view if the user is not logged in
    private void loadLogInView() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "Service destroyed", Toast.LENGTH_LONG).show();

    }

   /* private void writeNewModel(String userId, Model model) {
        //String id = model.getModelId() + "";
        //mDatabase.child("users").child(userId).child("models").push().setValue(model);
    }

    private ArrayList<Model> getModels(final String userId) {
        final ArrayList<Model> models = new ArrayList<Model>();

        mDatabase.child("users").child(userId).child("models").addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                if (dataSnapshot.child("name").getValue() != null) {
                    Model m = new Model();

                    m.setName((String) dataSnapshot.child("name").getValue());
                    m.setFuelType((String) dataSnapshot.child("fuelType").getValue());
                    double len = Double.parseDouble(dataSnapshot.child("length").getValue().toString());
                    m.setLength(len);
                    double width = (double) Double.parseDouble(dataSnapshot.child("width").getValue().toString());
                    m.setWidth(width);
                    m.setModelType((String) dataSnapshot.child("modelType").getValue());
                    long reg = (long) dataSnapshot.child("registrationId").getValue();
                    m.setRegistrationId((int) reg);

                    models.add(m);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        return models;
    }

    private ArrayList<Club> getClubs() {
        final ArrayList<Club> nClubs = new ArrayList<Club>();

        // Use Firebase to populate the list of clubs and sites.
        mDatabase.child("FlyingSites").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.child("name").getValue() != null) {

                    //Add the name of the club to the list
                    //adapter.add((String) dataSnapshot.child("name").getValue());

                    //Create a Club object and populate its fields
                    Club club = new Club();
                    club.setShortName((String) dataSnapshot.getKey());
                    club.setClubId((String) dataSnapshot.child("club_id").getValue());
                    club.setName((String) dataSnapshot.child("name").getValue());
                    club.setContact((String) dataSnapshot.child("contact").getValue());
                    club.setCounty((String) dataSnapshot.child("county").getValue());
                    club.setRestriction((String) dataSnapshot.child("restriction").getValue());

                    try {
                        double nLat = (double) dataSnapshot.child("lat").getValue();
                        club.setLat(nLat);
                    } catch (NumberFormatException e) {
                        System.out.println((String) dataSnapshot.child("lat").getValue());
                    }

                    try {
                        double nLon = (Double) dataSnapshot.child("lng").getValue();
                        club.setLon(nLon);
                    } catch (NumberFormatException e) {
                        System.out.println((String) dataSnapshot.child("lng").getValue());
                    }

                    String url = (String) dataSnapshot.child("url").getValue();
                    club.setUrl(url);

                    nClubs.add(club);
                    Log.wtf("Brian", nClubs.toString());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                if (dataSnapshot.child("name").getValue() != null) {
                    //adapter.remove((String) dataSnapshot.child("name").getValue());
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return nClubs;
    }*/
}
