package com.example.eridhobufferyrollian.beispielsql;



/**
 * Created by eridhobufferyrollian on 15.06.17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
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

import android.graphics.Color;
import android.graphics.Paint;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.eridhobufferyrollian.beispielsql.model.DateiMemo;
import com.example.eridhobufferyrollian.beispielsql.model.ForeignData;
import com.example.eridhobufferyrollian.beispielsql.model.NeighborMemo;
import com.example.eridhobufferyrollian.beispielsql.model.OwnDataMemo;
import com.example.eridhobufferyrollian.beispielsql.model.PeerMemo;
import com.example.eridhobufferyrollian.beispielsql.source.DateiMemoDbSource;
import com.example.eridhobufferyrollian.beispielsql.source.ForeignDataDbSource;
import com.example.eridhobufferyrollian.beispielsql.source.NeighborDbSource;
import com.example.eridhobufferyrollian.beispielsql.source.OwnDataDbSource;
import com.example.eridhobufferyrollian.beispielsql.source.PeerDbSource;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnData, btnOwnData, btnForeignData, btnNeighbor, btnPeer, btnIpNode, btnUpdate;

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private DateiMemoDbSource dateiMemoDbSource;
    private ForeignDataDbSource foreignDataDbSource;
    private NeighborDbSource neighborDbSource;
    private OwnDataDbSource ownDataDbSource;
    private PeerDbSource peerDbSource;

    private DateiMemo dateiMemo;
    private ForeignData foreignData;
    private NeighborMemo neighborMemo;
    private OwnDataMemo ownDataMemo;
    private PeerMemo peerMemo;

    private ListView mDateiMemosListView;
    //----------------  Man verbindet von hier zu der DBSource  -----------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "Das Datenquellen-Objekt wird angelegt.");


        btnData= (Button)findViewById(R.id.btnData);
        btnData.setOnClickListener(this);

        btnOwnData= (Button) findViewById(R.id.btnOwnData);
        btnOwnData.setOnClickListener(this);

        btnForeignData= (Button) findViewById(R.id.btnForeignData);
        btnForeignData.setOnClickListener(this);


        btnNeighbor= (Button) findViewById(R.id.btnNeighbor);
        btnNeighbor.setOnClickListener(this);

        btnPeer= (Button) findViewById(R.id.btnPeer);
        btnPeer.setOnClickListener(this);

        btnIpNode= (Button)findViewById(R.id.btnIpNode);
        btnIpNode.setOnClickListener(this);

        btnUpdate= (Button)findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        insertSampleData();

        //initializeDateiMemosListView();

        //activateAddButton();
        //initializeContextualActionBar();

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


    /*
    *
    *
    *                       Insert Dummies
    *
    *
    *
    * */
    private void insertSampleData(){
        dateiMemoDbSource = new DateiMemoDbSource();
        foreignDataDbSource = new ForeignDataDbSource();
        neighborDbSource = new NeighborDbSource();
        ownDataDbSource = new OwnDataDbSource();
        peerDbSource = new PeerDbSource();

//        dateiMemoDbSource.deleteDateiMemo();
//        neighborDbSource.deleteNeighbormemo();
//        ownDataDbSource.deleteOwnData();
//        peerDbSource.deletePeerMemo();
//        foreignDataDbSource.deleteForeignData();



        //insert DateiMemo
        // String username, String password, int uid, boolean checked,
        // double cornerTopRightX und Y, double cornerTopLeftx und Y, double cornerBottomRightX und Y,
        // double cornerBottomLeftX und Y, double punktX, double punktY, double IP, int countPeers
        DateiMemo dateiMemo = new DateiMemo();
        dateiMemo.setUid(7872);
        //dateiMemo.setChecked(true);
        dateiMemo.setCornerTopRightX(0.5);
        dateiMemo.setCornerTopRightY(0.6);
        dateiMemo.setCornerTopLeftX(0.2);
        dateiMemo.setCornerTopLeftY(0.2);
        dateiMemo.setCornerBottomLeftX(0.4);
        dateiMemo.setCornerBottomLeftY(0.6);
        dateiMemo.setCornerBottomRightX(0.5);
        dateiMemo.setCornerBottomRightY(1);
        dateiMemo.setPunktX(0.2);
        dateiMemo.setPunktY(0.4);
        dateiMemo.setIP("277.0.0.0/8");
        dateiMemo.setCountPeers(2);
        dateiMemoDbSource.createDateiMemo(dateiMemo);

        //insert Foreign Data
        //private long uid;
        //private boolean checked;
        //private int fotoId;
        //private double punktX;
        //private double punktY;
        //private String foreignIp;
        ForeignData foreignData = new ForeignData();
        foreignData.setUid(dateiMemo.getUid());
        //foreignData.setChecked(true);
        foreignData.setFotoId(2);
        foreignData.setPunktX(0.5);
        foreignData.setPunktY(0.5);
        foreignData.setForeignIp("277.0.0.1");
        foreignDataDbSource.createForeignData(foreignData);

        //insert neighbor memo
        //private double cornerTopRightX;
        //private double cornerTopRightY;
        //private double cornerTopLeftX;
        //private double cornerTopLeftY;
        //private double cornerBottomRightX;
        //private double cornerBottomRightY;
        //private double cornerBottomLeftX;
        //private double cornerBottomLeftY;
        //private double punktX;
        //private double punktY;
        //private String UIP;
        //private double RTT;
        //private boolean checked;
        //private long uid;
        NeighborMemo neighborMemo = new NeighborMemo();
        neighborMemo.setUid(dateiMemo.getUid());
        //neighborMemo.setChecked(true);
        neighborMemo.setCornerTopRightX(0.5);
        neighborMemo.setCornerTopRightY(0.6);
        neighborMemo.setCornerTopLeftX(0.2);
        neighborMemo.setCornerTopLeftY(0.2);
        neighborMemo.setCornerBottomLeftX(0.4);
        neighborMemo.setCornerBottomLeftY(0.6);
        neighborMemo.setCornerBottomRightX(0.5);
        neighborMemo.setCornerBottomRightY(0.8);
        neighborMemo.setPunktX(0.2);
        neighborMemo.setPunktY(0.4);
        neighborMemo.setUIP("277.0.0.0/8");
        neighborMemo.setRTT(25.89);
        neighborDbSource.createNeighborMemo(neighborMemo);

        //insert Own Data
        //    public long uid;
        //    public boolean checked;
        //    public int fileId
        OwnDataMemo ownDataMemo = new OwnDataMemo();
        ownDataMemo.setUid(dateiMemo.getUid());
        //ownDataMemo.setChecked(true);
        ownDataMemo.setFileId(3);
        ownDataDbSource.createOwnData(ownDataMemo);

        //insert Peer
        //    private long uid;
        //    public int peerId;
        //    public double peerIp;
        //    private boolean checked;
        PeerMemo peerMemo = new PeerMemo();
        peerMemo.setUid(dateiMemo.getUid());
        peerMemo.setPeerId(1);
        //peerMemo.setChecked(true);
        peerMemo.setPeerIp("277.0.0.1");
        peerDbSource.createPeerMemo(peerMemo);

    }

    //Zeig Table Datei Memo
    private void ListDatei () {
        List<DateiMemo> dateiMemoList= dateiMemoDbSource.getAllDateiMemos();
        Log.d(LOG_TAG,"=============================================================");

        for (int j = 0; j < dateiMemoList.size(); j++){
            String output = "Node_ID: "+ dateiMemoList.get(j).getUid() +
                    //"\n Status: "+ dateiMemoList.get(j).isChecked() +
                    "\n Corner Top Right X: "+ dateiMemoList.get(j).getCornerTopRightX() +
                    "\n Corner Top Right Y: "+ dateiMemoList.get(j).getCornerTopRightY() +
                    "\n Corner Top Left X: "+ dateiMemoList.get(j).getCornerTopLeftX() +
                    "\n Corner Top Left Y: "+ dateiMemoList.get(j).getCornerTopLeftY() +
                    "\n Corner Bottom Right X: "+ dateiMemoList.get(j).getCornerBottomRightX() +
                    "\n Corner Bottom Right Y: "+ dateiMemoList.get(j).getCornerBottomRightY() +
                    "\n Corner Bottom Left X: "+ dateiMemoList.get(j).getCornerBottomLeftX() +
                    "\n Corner Bottom Left Y: "+ dateiMemoList.get(j).getCornerBottomLeftY() +
                    "\n Punkt X: "+ dateiMemoList.get(j).getPunktX() +
                    "\n Punkt Y: "+ dateiMemoList.get(j).getPunktY() +
                    "\n IP: "+ dateiMemoList.get(j).getIP() +
                    "\n Count Peers: "+ dateiMemoList.get(j).getCountPeers() ;


            Log.d(LOG_TAG, output);


        }
        Log.d(LOG_TAG,"=============================================================");
    }

    //Zeig Table Foreign Datei
    private void ListForeignDatei () {
        List<ForeignData> foreignDataList= foreignDataDbSource.getAllForeignData();
        Log.d(LOG_TAG,"=============================================================");

        for (int i= 0; i < foreignDataList.size(); i++){
            String output = "Foreign_ID: "+ foreignDataList.get(i).getUid() +
                    //"\n Status: "+ foreignDataList.get(i).isChecked() +
                    "\n Foto ID: "+ foreignDataList.get(i).getFotoId() +
                    "\n Punkt X: "+ foreignDataList.get(i).getPunktX() +
                    "\n Punkt Y: "+ foreignDataList.get(i).getPunktY() +
                    "\n IP: "+ foreignDataList.get(i).getForeignIp() ;

            Log.d(LOG_TAG, output);
        }
        Log.d(LOG_TAG,"=============================================================");
    }

    private void ListNeighborData() {
        List<NeighborMemo> neighborMemoList= neighborDbSource.getAllNeighborMemo();
        Log.d(LOG_TAG,"=============================================================");

        for (int i= 0; i < neighborMemoList.size(); i++) {
            String output = "Neighbor_ID: "+ neighborMemoList.get(i).getUid() +
                    //"\n Status: "+ neighborMemoList.get(i).isChecked() +
                    "\n Corner Top Right X: "+ neighborMemoList.get(i).getCornerTopRightX() +
                    "\n Corner Top Right Y: "+ neighborMemoList.get(i).getCornerTopRightY() +
                    "\n Corner Top Left X: "+ neighborMemoList.get(i).getCornerTopLeftX() +
                    "\n Corner Top Left Y: "+ neighborMemoList.get(i).getCornerTopLeftY() +
                    "\n Corner Bottom Right X: "+ neighborMemoList.get(i).getCornerBottomRightX() +
                    "\n Corner Bottom Right Y: "+ neighborMemoList.get(i).getCornerBottomRightY() +
                    "\n Corner Bottom Left X: "+ neighborMemoList.get(i).getCornerBottomLeftX() +
                    "\n Corner Bottom Left Y: "+ neighborMemoList.get(i).getCornerBottomLeftY() +
                    "\n Punkt X: "+ neighborMemoList.get(i).getPunktX() +
                    "\n Punkt Y: "+ neighborMemoList.get(i).getPunktY() +
                    "\n IP: "+ neighborMemoList.get(i).getUIP() +
                    "\n RTT: "+ neighborMemoList.get(i).getRTT();

            Log.d(LOG_TAG, output);
        }
        Log.d(LOG_TAG,"=============================================================");

    }

    private void ListOwnData (){
        List<OwnDataMemo> ownDataMemoList= ownDataDbSource.getAllOwnData();
        Log.d(LOG_TAG,"=============================================================");

        for (int i = 0; i < ownDataMemoList.size(); i++){
            String output = "OwnData_ID: "+ ownDataMemoList.get(i).getUid() +
                    //"\n Status: "+ ownDataMemoList.get(i).isChecked() +
                    "\n File ID: "+ ownDataMemoList.get(i).getFileId();

            Log.d(LOG_TAG, output);
        }

        Log.d(LOG_TAG,"=============================================================");
    }

    private void ListPeer (){
        List<PeerMemo> peerMemoList= peerDbSource.getAllPeer();
        Log.d(LOG_TAG,"=============================================================");

        for(int i= 0; i < peerMemoList.size(); i++){
            String output = "Node_Peer_ID: "+ peerMemoList.get(i).getUid() +
                    //"\n Status: "+ peerMemoList.get(i).isChecked() +
                    "\nPeer ID: "+ peerMemoList.get(i).getPeerId() +
                    "\n IP: "+ peerMemoList.get(i).getPeerIp();

            Log.d(LOG_TAG, output);
        }
        Log.d(LOG_TAG,"=============================================================");
    }

    private void IP_Node () {
        String value = dateiMemoDbSource.getIp(dateiMemoDbSource.getUid());
        Log.d(LOG_TAG,"=============================================================");
        String output = "Value = " + value;
        Log.d(LOG_TAG, output);
        Log.d(LOG_TAG,"=============================================================");
    }

    private void UpdateCornerTopLeftX_Node (){
        Log.d(LOG_TAG,"=============================================================");
        String alteWert = "Alte Wert = " + dateiMemoDbSource.getCornerTopLeftX();
        Log.d(LOG_TAG, alteWert);
        dateiMemoDbSource.updateCornerTopLeftX(dateiMemoDbSource.getUid(), 0.87);
        String neuWert = "Neue Wert = " + dateiMemoDbSource.getCornerTopLeftX();
        Log.d(LOG_TAG, neuWert);
        Log.d(LOG_TAG,"=============================================================");
    }


//    private void initializeDateiMemosListView() {
//        List<DateiMemo> emptyListForInitialization = new ArrayList<>();
//
//        mDateiMemosListView = (ListView) findViewById(R.id.textView);
//
//        // Erstellen des ArrayAdapters für unseren ListView
//        ArrayAdapter<DateiMemo> DateiMemoArrayAdapter = new ArrayAdapter<DateiMemo> (
//                this,
//                android.R.layout.simple_list_item_multiple_choice,
//                emptyListForInitialization) {
//
//            // Wird immer dann aufgerufen, wenn der übergeordnete ListView die Zeile neu zeichnen muss
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
//                View view =  super.getView(position, convertView, parent);
//                TextView textView = (TextView) view;
//
//                DateiMemo memo = (DateiMemo) mDateiMemosListView.getItemAtPosition(position);
//
//                // Hier prüfen, ob Eintrag abgehakt ist. Falls ja, Text durchstreichen
//                if (memo.isChecked()) {
//                    textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
//                    textView.setTextColor(Color.rgb(175,175,175));
//                }
//                else {
//                    textView.setPaintFlags( textView.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
//                    textView.setTextColor(Color.DKGRAY);
//                }
//
//                return view;
//            }
//        };
//
//        mDateiMemosListView.setAdapter(DateiMemoArrayAdapter);
//
//        mDateiMemosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                DateiMemo memo = (DateiMemo) adapterView.getItemAtPosition(position);
//
//                // Hier den checked-Wert des Memo-Objekts umkehren, bspw. von true auf false
//                // Dann ListView neu zeichnen mit showAllListEntries()
//                DateiMemo updatedDateiMemo = dataSource.updateDateiMemo(memo.getUid(), memo.getUsername(), memo.getPassword(), (!memo.isChecked()));
//                Log.d(LOG_TAG, "Checked-Status von Eintrag: " + updatedDateiMemo.toString() + " ist: " + updatedDateiMemo.isChecked());
//                showAllListEntries();
//            }
//        });
//
//    }
//
//
//    private void initializeContextualActionBar() {
//        final ListView dateiMemosListView = (ListView) findViewById(R.id.listview_datei_memos);
//        dateiMemosListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
//
//        dateiMemosListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
//
//            int selCount = 0;
//
//            // In dieser Callback-Methode zählen wir die ausgewählen Listeneinträge mit
//            // und fordern ein Aktualisieren der Contextual Action Bar mit invalidate() an
//            @Override
//            public void onItemCheckedStateChanged(ActionMode mode, int position, long uid, boolean checked) {
//                if (checked) {
//                    selCount++;
//                } else {
//                    selCount--;
//                }
//                String cabTitle = selCount + " " + getString(R.string.cab_checked_string);
//                mode.setTitle(cabTitle);
//                mode.invalidate();
//            }
//
//            // In dieser Callback-Methode legen wir die CAB-Menüeinträge an
//            @Override
//            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                getMenuInflater().inflate(R.menu.menu_contextual_action_bar, menu);
//                return true;
//            }
//
//            // In dieser Callback-Methode reagieren wir auf den invalidate() Aufruf
//            // Wir lassen das Edit-Symbol verschwinden, wenn mehr als 1 Eintrag ausgewählt ist
//            @Override
//            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                MenuItem item = menu.findItem(R.id.cab_change);
//                if (selCount == 1) {
//                    item.setVisible(true);
//                } else {
//                    item.setVisible(false);
//                }
//
//                return true;
//            }
//
//            // In dieser Callback-Methode reagieren wir auf Action Item-Klicks
//            // Je nachdem ob das Löschen- oder Ändern-Symbol angeklickt wurde
//            @Override
//            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                boolean returnValue = true;
//                SparseBooleanArray touchedDateiMemosPositions = dateiMemosListView.getCheckedItemPositions();
//
//                switch (item.getItemId()) {
//                    case R.id.cab_delete:
//                        for (int i=0; i < touchedDateiMemosPositions.size(); i++) {
//                            boolean isChecked = touchedDateiMemosPositions.valueAt(i);
//                            if(isChecked) {
//                                int postitionInListView = touchedDateiMemosPositions.keyAt(i);
//                                DateiMemo dateiMemo = (DateiMemo) dateiMemosListView.getItemAtPosition(postitionInListView);
//                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + dateiMemo.toString());
//                                dataSource.deleteDateiMemo(dateiMemo);
//                            }
//                        }
//                        showAllListEntries();
//                        mode.finish();
//                        break;
//
//                    case R.id.cab_change:
//                        Log.d(LOG_TAG, "Eintrag ändern");
//                        for (int i = 0; i < touchedDateiMemosPositions.size(); i++) {
//                            boolean isChecked = touchedDateiMemosPositions.valueAt(i);
//                            if (isChecked) {
//                                int postitionInListView = touchedDateiMemosPositions.keyAt(i);
//                                DateiMemo dateiMemo = (DateiMemo) dateiMemosListView.getItemAtPosition(postitionInListView);
//                                Log.d(LOG_TAG, "Position im ListView: " + postitionInListView + " Inhalt: " + dateiMemo.toString());
//
//                                AlertDialog editDateiMemoDialog = createEditDateiMemoDialog(dateiMemo);
//                                editDateiMemoDialog.show();
//                            }
//                        }
//
//                        mode.finish();
//                        break;
//
//                    default:
//                        returnValue = false;
//                        break;
//                }
//                return returnValue;
//            }
//
//            // In dieser Callback-Methode reagieren wir auf das Schließen der CAB
//            // Wir setzen den Zähler auf 0 zurück
//            @Override
//            public void onDestroyActionMode(ActionMode mode) {
//                selCount = 0;
//            }
//        });
//    }
//
//    private AlertDialog createEditDateiMemoDialog(final DateiMemo dateiMemo) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//
//        View dialogsView = inflater.inflate(R.layout.dialog_edit_datei_memo, null);
//
//        final EditText editTextNewPassword = (EditText) dialogsView.findViewById(R.id.editText_new_password);
//        editTextNewPassword.setText(String.valueOf(dateiMemo.getPassword()));
//
//        final EditText editTextNewName = (EditText) dialogsView.findViewById(R.id.editText_new_name);
//        editTextNewName.setText(dateiMemo.getUsername());
//
//        //final EditText editTextNewUip = (EditText) dialogsView.findViewById(R.id.editText_new_uip);
//        //editTextNewUip.setText(dateiMemo.getUip());
//
//        //final EditText editTextNewPeerId = (EditText) dialogsView.findViewById(R.id.editText_new_peerid);
//        //editTextNewPeerId.setText(dateiMemo.getPeerId());
//
//        //final EditText editTextNewFileId = (EditText) dialogsView.findViewById(R.id.editText_new_fileid);
//        //editTextNewFileId.setText(dateiMemo.getFileId());
//
//        //final EditText editTextNewFotoId = (EditText) dialogsView.findViewById(R.id.editText_new_fotoid);
//        //editTextNewFotoId.setText(dateiMemo.getFotoId());
//
//        builder.setView(dialogsView)
//                .setTitle(R.string.dialog_title)
//                .setPositiveButton(R.string.dialog_button_positive, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int uid) {
//                        String password = editTextNewPassword.getText().toString();
//                        String username = editTextNewName.getText().toString();
//                        //long uip = editTextNewUip.getText().toString();
//                        //long peerId = editTextNewPeerId.getText().toString();
//                        //long fileId = editTextNewFileId.getText().toString();
//                        //long fotoId = editTextNewFotoId.getText().toString();
//
//                        if ((TextUtils.isEmpty(password)) || (TextUtils.isEmpty(username))) {
//                            Log.d(LOG_TAG, "Ein Eintrag enthielt keinen Text. Daher Abbruch der Änderung.");
//                            return;
//                        }
//
//                        // An dieser Stelle schreiben wir die geänderten Daten in die SQLite Datenbank
//                        DateiMemo updatedDateiMemo = dataSource.updateDateiMemo(dateiMemo.getUid(), username, password/*, uip, peerId, fileId, fotoId*/, dateiMemo.isChecked());
//
//                        Log.d(LOG_TAG, "Alter Eintrag - ID: " + dateiMemo.getUid() + " Inhalt: " + dateiMemo.toString());
//                        Log.d(LOG_TAG, "Neuer Eintrag - ID: " + updatedDateiMemo.getUid() + " Inhalt: " + updatedDateiMemo.toString());
//
//                        showAllListEntries();
//                        dialog.dismiss();
//                    }
//                })
//                .setNegativeButton(R.string.dialog_button_negative, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int uid) {
//                        dialog.cancel();
//                    }
//                });
//
//        return builder.create();
//    }


//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Log.d(LOG_TAG, "Die Datenquelle wird geöffnet.");
//        dataSource.open();
//
//        Log.d(LOG_TAG, "Folgende Einträge sind in der Datenbank vorhanden:");
//        showAllListEntries();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//
//        Log.d(LOG_TAG, "Die Datenquelle wird geschlossen.");
//        dataSource.close();
//    }
//
//    private void activateAddButton() {
//        Button buttonAddUsername = (Button) findViewById(R.id.button_add_user);
//        final EditText editTextUsername = (EditText) findViewById(R.id.editText_username);
//        final EditText editTextPassword = (EditText) findViewById(R.id.editText_password);
//
//        buttonAddUsername.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String username = editTextUsername.getText().toString();
//                String password = editTextUsername.getText().toString();
//
//                if(TextUtils.isEmpty(username)) {
//                    editTextUsername.setError(getString(R.string.editText_errorMessage));
//                    return;
//                }
//                if(TextUtils.isEmpty(password)) {
//                    editTextPassword.setError(getString(R.string.editText_errorMessage));
//                    return;
//                }
//
//
//                editTextUsername.setText("");
//                editTextPassword.setText("");
//
//                dataSource.createDateiMemo(username, password);
//
//                InputMethodManager inputMethodManager;
//                inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//                if(getCurrentFocus() != null) {
//                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                }
//
//                showAllListEntries();
//            }
//        });
//
//    }
    //----------------------------------------------------------------------------------------------
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }


    private void showAllListEntries () {
        List<DateiMemo> DateiMemoList = dateiMemoDbSource.getAllDateiMemos();

        ArrayAdapter<DateiMemo> adapter = (ArrayAdapter<DateiMemo>) mDateiMemosListView.getAdapter();

        adapter.clear();
        adapter.addAll(DateiMemoList);
        adapter.notifyDataSetChanged();
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


    @Override
    public void onClick(View view) {
        if (view ==findViewById(R.id.btnData)){
            ListDatei();
        }else if (view ==findViewById(R.id.btnOwnData)){
            ListOwnData();
        }else if (view ==findViewById(R.id.btnForeignData)){
            ListForeignDatei();
        }else if (view ==findViewById(R.id.btnNeighbor)){
            ListNeighborData();
        }else if (view ==findViewById(R.id.btnPeer)) {
            ListPeer();
        }else if (view ==findViewById(R.id.btnIpNode)) {
            IP_Node();
        }else if (view ==findViewById(R.id.btnUpdate)) {
            UpdateCornerTopLeftX_Node();
        }
    }
}