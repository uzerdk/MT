package com.vs.masstext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class recipients extends AppCompatActivity {

    ListView lv;
    TagsModel[] contactsPeoples;
    int activityLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipients);

        lv = (ListView) findViewById(R.id.listView1);
        lv.setItemsCanFocus(true);

        //TODO change taglist and allTagsList to get pulled from contacts


        ArrayList tagsList = this.getIntent().getStringArrayListExtra("tagList");
        ArrayList allTagsList = this.getIntent().getStringArrayListExtra("allTagsList");



        contactsPeoples = new TagsModel[allTagsList.size()];

        for (int i = 0; i<allTagsList.size(); i++){
            if (tagsList.contains(allTagsList.get(i))){
                activityLevel = 1;
            } else { activityLevel = 0;}

            contactsPeoples[i] = new TagsModel(allTagsList.get(i).toString(), activityLevel);
        }

        CustomAdapter adapter = new CustomAdapter(this, contactsPeoples);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tags_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.done_tags:
                // make tags list from selected model items
                //TODO change this to array of contact info
                ArrayList<String> modifiedTagsList = new ArrayList<>();
                for (TagsModel tag : contactsPeoples){
                    if (tag.getValue() == 1){ // its selected
                        modifiedTagsList.add(tag.getName());
                    }
                }

                // send tags to previous activity
                // make new intent for tags
                Intent announcementListIntent = new Intent();

                // send tags back over to list
                //announcementListIntent.putExtra("modifiedTagList", modifiedTagsList);
                Bundle extras = new Bundle();
                extras.putSerializable("modifiedTagList",modifiedTagsList);
                announcementListIntent.putExtras(extras);

                // TODO send response to main activity

                setResult(RESULT_OK, announcementListIntent);
                finish();

                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }



    protected class CustomAdapter extends ArrayAdapter<TagsModel> {
        TagsModel[] modelItems = null;
        Context context;

        private CustomAdapter(Context context, TagsModel[] resource) {
            super(context, R.layout.row_da_tags, resource);
            this.context = context;
            this.modelItems = resource;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_da_tags, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.textView1);
            CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);

            name.setText(modelItems[position].getName());
            if (modelItems[position].getValue() == 1)
                cb.setChecked(true);
            else
                cb.setChecked(false);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    //System.out.println("clicked position " + position + " ******** and has value " +contactsPeoples[position].getValue() + "************************************");
                    modelItems[position].changeValue();
                    //System.out.println(" ******** now has value " +contactsPeoples[position].getValue() + "************************************");
                }
            });




            return convertView;
        }
    }
}


/*
Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
while (phones.moveToNext())
{
  String name=phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
  String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
  Toast.makeText(getApplicationContext(),name, Toast.LENGTH_LONG).show();

}
phones.close();
 */


/*
private void getContactList() {
    ContentResolver cr = getContentResolver();
    Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, null);

    if ((cur != null ? cur.getCount() : 0) > 0) {
        while (cur != null && cur.moveToNext()) {
            String id = cur.getString(
                    cur.getColumnIndex(ContactsContract.Contacts._ID));
            String name = cur.getString(cur.getColumnIndex(
                    ContactsContract.Contacts.DISPLAY_NAME));

            if (cur.getInt(cur.getColumnIndex(
                    ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                Cursor pCur = cr.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                while (pCur.moveToNext()) {
                    String phoneNo = pCur.getString(pCur.getColumnIndex(
                            ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Log.i(TAG, "Name: " + name);
                    Log.i(TAG, "Phone Number: " + phoneNo);
                }
                pCur.close();
            }
        }
    }
    if(cur!=null){
        cur.close();
    }
}
 */