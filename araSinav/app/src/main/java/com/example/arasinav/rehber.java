package com.example.arasinav;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class rehber extends AppCompatActivity {
    private ArrayList<Person> persons;
    private RecyclerView recyclerView;
    private PersonRecyclerAdapter personRecyclerAdapter;
    RadioButton radioButton4,radioButton5,radioButton6;
    Button buttonekle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber);
        persons=new ArrayList<>();
        tanimla();
        getContactList();
    }
    public void tanimla()
    {

        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        personRecyclerAdapter=new PersonRecyclerAdapter(persons);
        recyclerView.setAdapter(personRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        buttonekle=(Button)findViewById(R.id.buttonekle);
        radioButton4=(RadioButton)findViewById(R.id.radioButton4);
        radioButton5=(RadioButton)findViewById(R.id.radioButton5);
        radioButton6=(RadioButton)findViewById(R.id.radioButton6);


    }

    @SuppressLint("Range")
    private void getContactList() {
        Context deneme;
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if ((cur != null ? cur.getCount() : 0) > 0) {
            while (cur != null && cur.moveToNext()) {
                @SuppressLint("Range") String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                @SuppressLint("Range") String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                    Bitmap bp = BitmapFactory.decodeResource(getResources(),
                            R.drawable.ic_launcher_foreground);




                        @SuppressLint("Range") String image_uri = cur.getString(cur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.PHOTO_URI));

                        if (image_uri != null) {

                            try {
                                bp = MediaStore.Images.Media
                                        .getBitmap(getContentResolver(),
                                                Uri.parse(image_uri));

                            } catch (FileNotFoundException e) {

                                e.printStackTrace();
                            } catch (IOException e) {

                                e.printStackTrace();
                            }
                        }





                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                        persons.add(new Person(bp,name,phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        if(cur!=null){
            cur.close();
        }

    }

    public void kayit(String numara)
    {
        SharedPreferences ayarSP= getSharedPreferences("Ayarlar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = ayarSP.edit();
        editor.putString("numara",numara);
        editor.commit();

    }


}