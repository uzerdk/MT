package com.vs.masstext;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<HashMap<String, String>> contacts;
    final int REQUESTCODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>();



        final Button getContacts = (Button) findViewById(R.id.recepientsBtn);
        getContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContacts();
            }
        });

        final Button showProgress = (Button) findViewById(R.id.progressBtn);
        showProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgress();
            }
        });

        final Button sendMsg = (Button) findViewById(R.id.sendBtn);
        sendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMsg();
            }
        });

    }


    // TODO maybe add code to populate text view for recipients

    protected void getContacts() {
        Intent people = new Intent(this, recipients.class);
        // TODO should this be startactivityforresult
        startActivityForResult(people, REQUESTCODE);
    }


    protected void showProgress() {
        Intent showProg = new Intent(this, progress.class);
        startActivity(showProg);
    }


    protected void sendMsg() {


        // TODO write send method here
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent announcementListIntent) {
        if (resultCode == RESULT_OK && requestCode == REQUESTCODE) {  /*
            if (announcementListIntent.hasExtra("modifiedTagList")) {

                Bundle tagBundle = announcementListIntent.getExtras();
                if(tagBundle != null) {
                    modifiedTagsList = (ArrayList) tagBundle.getSerializable("modifiedTagList");
                }

                displayTheList(true);
            } else {
                Log.e(TAG, "Modified Tags List not returned from child intent");
            }  */
        }
    }






    // ---------- helper methods below --------------------- //

    private String getMsg(){
        EditText msgBox = findViewById(R.id.msgText);
        return msgBox.getText().toString();
    }
}
