package com.example.eridhobufferyrollian.beispielsql;



/**
 * Created by eridhobufferyrollian on 15.06.17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;



import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;



import android.view.inputmethod.InputMethodManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.widget.AbsListView;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;


public class MainActivity extends AppCompatActivity {



    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DateiMemoDbSource dataSource;





    //----------------  Man verbindet von hier zu der DBSource  -----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");
        dataSource = new DateiMemoDbSource(this);

        activateAddButton();
        initializeContextualActionBar();

//        //------------------------------------Test Dummies------------------------------------
//
//        DateiMemo testMemo = new DateiMemo("Wittman", "muster123", 1);
//        Log.d(LOG_TAG, "Inhalt der Testmemo: " + testMemo.toString());
//
//
//        //------------------------------------Test Dummies------------------------------------
//
//
//
//        dataSource = new DateiMemoDbSource(this);
//
//        //anfangen, um zu verbinden
//        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
//        dataSource.open();
//
//
//        DateiMemo DateiMemo = dataSource.createDateiMemo("TestUser", "123123");
//        Log.d(LOG_TAG, "Es wurde der folgende Eintrag in die Datenbank geschrieben:");
//        Log.d(LOG_TAG, "ID: " + DateiMemo.getNid() + ", Inhalt: " + DateiMemo.toString());
//
//        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
//        showAllListEntries();
//
//        //schliessen
//        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
//        dataSource.close();
    }

    private void initializeContextualActionBar() {
        final ListView dateiMemosListView = (ListView) findViewById(R.id.listview_datei_memos);
        dateiMemosListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        dateiMemosListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            int selCount = 0;

            // In dieser Callback-Methode zählen wir die ausgewählen Listeneinträge mit
            // und fordern ein Aktualisieren der Contextual Action Bar mit invalidate() an
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long nid, boolean checked) {
                if (checked) {
                    selCount++;
                } else {
                    selCount--;
                }
                String cabTitle = selCount + " " + getString(R.string.cab_checked_string);
                mode.setTitle(cabTitle);
                mode.invalidate();
            }

            // In dieser Callback-Methode legen wir die CAB-Menüeinträge an
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.menu_contextual_action_bar, menu);
                return true;
            }

            // In dieser Callback-Methode reagieren wir auf den invalidate() Aufruf
            // Wir lassen das Edit-Symbol verschwinden, wenn mehr als 1 Eintrag ausgewählt ist
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                MenuItem item = menu.findItem(R.id.cab_change);
                if (selCount == 1) {
                    item.setVisible(true);
                } else {
                    item.setVisible(false);
                }

                return true;
            }

            // In dieser Callback-Methode reagieren wir auf Action Item-Klicks
            // Je nachdem ob das Löschen- oder Ändern-Symbol angeklickt wurde
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                boolean returnValue = true;
                SparseBooleanArray touchedDateiMemosPositions = dateiMemosListView.getCheckedItemPositions();

                switch (item.getItemId()) {
                    case R.id.cab_delete:
                        for (int i=0; i < touchedDateiMemosPositions.size(); i++) {
                            boolean isChecked = touchedDateiMemosPositions.valueAt(i);
                            if(isChecked) {
                                int postitionInListView = touchedDateiMemosPositions.keyAt(i);
                                DateiMemo dateiMemo = (DateiMemo) dateiMemosListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + dateiMemo.toString());
                                dataSource.deleteDateiMemo(dateiMemo);
                            }
                        }
                        showAllListEntries();
                        mode.finish();
                        break;

                    case R.id.cab_change:
                        Log.d(LOG_TAG, "Eintrag ändern");
                        for (int i = 0; i < touchedDateiMemosPositions.size(); i++) {
                            boolean isChecked = touchedDateiMemosPositions.valueAt(i);
                            if (isChecked) {
                                int postitionInListView = touchedDateiMemosPositions.keyAt(i);
                                DateiMemo dateiMemo = (DateiMemo) dateiMemosListView.getItemAtPosition(postitionInListView);
                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + dateiMemo.toString());

                                AlertDialog editShoppingMemoDialog = createEditDateiMemoDialog(dateiMemo);
                                editShoppingMemoDialog.show();
                            }
                        }

                        mode.finish();
                        break;

                    default:
                        returnValue = false;
                        break;
                }
                return returnValue;
            }

            // In dieser Callback-Methode reagieren wir auf das Schließen der CAB
            // Wir setzen den Zähler auf 0 zurück
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selCount = 0;
            }
        });
    }

    private AlertDialog createEditDateiMemoDialog(final DateiMemo dateiMemo) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();

        View dialogsView = inflater.inflate(R.layout.dialog_edit_datei_memo, null);

        final EditText editTextNewPassword = (EditText) dialogsView.findViewById(R.id.editText_new_password);
        editTextNewPassword.setText(String.valueOf(dateiMemo.getPassword()));

        final EditText editTextNewName = (EditText) dialogsView.findViewById(R.id.editText_new_name);
        editTextNewName.setText(dateiMemo.getUsername());

        builder.setView(dialogsView)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int nid) {
                        String password = editTextNewPassword.getText().toString();
                        String name = editTextNewName.getText().toString();

                        if ((TextUtils.isEmpty(password)) || (TextUtils.isEmpty(name))) {
                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
                            return;
                        }

                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
                        DateiMemo updatedDateiMemo = dataSource.updateDateiMemo(dateiMemo.getNid(), name, password);

                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + dateiMemo.getNid() + " Inhalt: " + dateiMemo.toString());
                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedDateiMemo.getNid() + " Inhalt: " + updatedDateiMemo.toString());

                        showAllListEntries();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.dialog_button_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int nid) {
                        dialog.cancel();
                    }
                });

        return builder.create();
    }


    @Override
    protected void onResume() {
        super.onResume();

        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
        dataSource.open();

        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
        showAllListEntries();
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
        dataSource.close();
    }

    private void activateAddButton() {
        Button buttonAddUsername = (Button) findViewById(R.id.button_add_user);
        final EditText editTextUsername = (EditText) findViewById(R.id.editText_username);
        final EditText editTextPassword = (EditText) findViewById(R.id.editText_password);

        buttonAddUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextUsername.getText().toString();
                String password = editTextUsername.getText().toString();

                if(TextUtils.isEmpty(username)) {
                    editTextUsername.setError(getString(R.string.editText_errorMessage));
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    editTextPassword.setError(getString(R.string.editText_errorMessage));
                    return;
                }


                editTextUsername.setText("");
                editTextPassword.setText("");

                dataSource.createDateiMemo(username, password);

                InputMethodManager inputMethodManager;
                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if(getCurrentFocus() != null) {
                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

                showAllListEntries();
            }
        });

    }
    //----------------------------------------------------------------------------------------------
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }


    private void showAllListEntries () {
        List<DateiMemo> DateiMemoList = dataSource.getAllDateiMemos();

        ArrayAdapter<DateiMemo> DateiMemoArrayAdapter = new ArrayAdapter<> (
                this,
                android.R.layout.simple_list_item_multiple_choice,
                DateiMemoList);

        ListView DateiMemosListView = (ListView) findViewById(R.id.listview_datei_memos);
        DateiMemosListView.setAdapter(DateiMemoArrayAdapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}