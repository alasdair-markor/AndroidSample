package com.markor.contactslist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import com.markor.contactslist.csvReader.CSVReader
import com.markor.contactslist.csvReader.CSVReaderImpl

class MainActivity : AppCompatActivity() {

    private val contacts = Array<String>(4) { "John Appleseed" }
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.contacts_list)
        listView.adapter = ContactAdapter(this, contacts)


        val reader: CSVReader = CSVReaderImpl(this)
        var line: String? = reader.readNextLine()

        while (line != null) {
            Log.i(null, line)
            line = reader.readNextLine()
        }
    }
}

private class ContactAdapter(context: Context, var items: Array<String>) :
    ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false)

        val nameTextView = rowView.findViewById<TextView>(android.R.id.text1)
        val contactPrefTextView = rowView.findViewById<TextView>(android.R.id.text2)

        val contact = getItem(position)

        nameTextView.text = contact
        contactPrefTextView.text = "POST"

        return rowView
    }

}