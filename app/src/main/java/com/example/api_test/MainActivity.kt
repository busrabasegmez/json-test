package com.example.api_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val jsonData = readJsonFile(R.raw.data)

        val gson = Gson()
        val persons = gson.fromJson(jsonData, Array<Person>::class.java)

        val listView: ListView = findViewById(R.id.listView)

        if (persons != null) {
            val adapter = ArrayAdapter(
                this,
                R.layout.list_item_person,
                R.id.textName,
                persons.map { it.name }
            )

            listView.adapter = adapter
        }
    }

    private fun readJsonFile(resourceId: Int): String {
        return resources.openRawResource(resourceId).bufferedReader().use { it.readText() }
    }
}
