package com.huscii.fornow;

        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ExpandableListView;
        import android.widget.ExpandableListView.OnChildClickListener;
        import android.widget.ExpandableListView.OnGroupClickListener;
        import android.widget.ExpandableListView.OnGroupCollapseListener;
        import android.widget.ExpandableListView.OnGroupExpandListener;
        import android.widget.Toast;
        import com.firebase.client.Firebase;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, EventData> listDataChild;
    GPSTracker gps;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gps = new GPSTracker(MainActivity.this);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listens to whenever the EVENT TITLE is touched



        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listens to whenever the EVENT TITLE is collapsed
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });


    }

    public void createTheEventButtonHandler(View v)
    {
        Intent nextScreen = new Intent(getApplicationContext(), createNewEventActivity.class);
        startActivity(nextScreen);

    }

    /*
     * Preparing the list data
     */




    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, EventData>();

        // Firebase db listener:
        Firebase.setAndroidContext(this);
        Firebase firebase = new Firebase("https://fornow.firebaseio.com/events");

        // Example push (title, tag, desc, int time, int duration, double lat, double lon)
        EventData temp = new EventData("Planking","fun and games","I'm lonely. I need a planing partner",10,15,40.45,34.9);

        //Querying db:
        //Query queryRef = myRef.orderByChild("range");

        // Pulling (query contains all of our db data):
        // queryRef.addListenerForSingleValueEvent(new ValueEventListener(){
        //  @Override
        //  public void onDataChange(DataSnapShot dataSnapShot){
        //"string" is key for a row, "object" is an event
        //HashMap<String,Object> myEv = (HashMap<String, Object>).dataSnapshote.getValue();

        // for (String item: myEv.keySet()){
        // for(String x: item.keySet()){

        // Add Event Titles
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), temp); // Header, Child data
        listDataChild.put(listDataHeader.get(1), temp);
        listDataChild.put(listDataHeader.get(2), temp);
    }
}