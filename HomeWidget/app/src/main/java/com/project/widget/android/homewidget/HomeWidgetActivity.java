package com.project.widget.android.homewidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * The configuration screen for the {@link HomeWidget HomeWidget} AppWidget.
 */
public class HomeWidgetActivity extends Activity {
    DictionaryDatabase myDb;
    SearchView searchView;
    ListView listView;
    ArrayList<DictionaryItem> words;
    ListViewAdapter adapter;
    Spinner sp;
    String language;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_widget_activity);
        myDb = new DictionaryDatabase(this);
        myDb.insertData("alma", "apple", "mere");
        myDb.insertData("körte", "pear", "pere");
        myDb.insertData("játék", "game", "joc");
        myDb.insertData("barna", "brown", "maro");
        myDb.insertData("akarat", "will", "vointa");

        listView = (ListView) findViewById(R.id.listView);
        searchView = (SearchView) findViewById(R.id.searchView);
        sp = (Spinner) findViewById(R.id.languages);


        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.languages_array, android.R.layout.simple_spinner_item);

        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerAdapter);
        language = sp.getSelectedItem().toString();

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter.setLanguage(sp.getSelectedItem().toString());
                listView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        words = myDb.getAllWords();
        adapter = new ListViewAdapter(this, words);
        adapter.setLanguage(language);
        listView.setAdapter(adapter);
        listView.setTextFilterEnabled(true);




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    listView.clearTextFilter();

                } else {
                    listView.setFilterText(newText);
                }
                return true;
            }
        });
    }
}

