package com.example.sandy.kotlin_content_provider

import android.content.ContentResolver
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ArrayAdapter
import android.widget.SimpleCursorAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var resolver=contentResolver

        var cursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

       var adpater= SimpleCursorAdapter(this@MainActivity,R.layout.indiview,cursor,
                arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER),
                intArrayOf(R.id.name,R.id.number),0)

        lv1.adapter=adpater

        var list= mutableListOf<String>()

        while (cursor.moveToNext()){

            var index=cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

            list.add(cursor.getString(index))

            var array_adapter  =  ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list)
            actv1.setAdapter(array_adapter)
            actv1.threshold = 1

        }


        //particular contact search
        srch.setOnClickListener({

            var resolver = contentResolver

            var cursor  =     resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"=?",
                    arrayOf(actv1.text.toString()), ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)

            var adapter = SimpleCursorAdapter(this, R.layout.indiview,cursor,
                    arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                            ContactsContract.CommonDataKinds.Phone.NUMBER),
                    intArrayOf(R.id.name,R.id.number),0)

            lv1.adapter = adapter


        })


    }
}
