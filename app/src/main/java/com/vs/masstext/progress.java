package com.vs.masstext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class progress extends AppCompatActivity {

    //TODO should the array list hold hashmaps???
    private ListView lv;
    ArrayList<HashMap<String, String>> progressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        lv = (ListView) findViewById(R.id.list);
        progressList = new ArrayList<>();
    }


    // TODO populate progressList with updated progress





    protected void displayTheList(boolean filtered){

        /*
        if (!filtered) {
            // Combine the 2 lists into 1
            progressList.clear();
            progressList.addAll(announcementList);
            progressList.addAll(eventList);
        } else {
            // results are filtered
            ArrayList<HashMap<String, String>> tempAnnouncementAndEventList = new ArrayList<>();
            tempAnnouncementAndEventList.addAll(announcementList);
            tempAnnouncementAndEventList.addAll(eventList);

            progressList.clear();


            // update tagSet
            tagSet.clear();
            tagSet.addAll(modifiedTagsList);

            // add the announcements and events for the chosen tags
            for (HashMap<String, String> map : tempAnnouncementAndEventList) {
                for (String tag : tagSet) {
                    if (map.get("tags").contains(tag) && !progressList.contains(map)) {
                        progressList.add(map);
                    }
                }
            }

        }
        */

        //TODO change list item to horizontal maybe

        ListAdapter announcementAdapter = new SimpleAdapter(progress.this, progressList,
                R.layout.list_item, new String[]{ "datetime","title"},
                new int[]{R.id.datetime, R.id.title});


        lv.setAdapter(announcementAdapter);
    }
}
