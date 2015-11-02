package net.mnio.sharknetnfc;

import android.annotation.TargetApi;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import static android.nfc.NdefRecord.createMime;

public class MainActivity extends AppCompatActivity {

    public static final String TEXT_MESSAGE = "010101010101";

    private NfcAdapter mNfcAdapter;
    private FloatingActionButton actionButton;

    private final NfcAdapter.CreateNdefMessageCallback createNdefMessageCallback = new NfcAdapter.CreateNdefMessageCallback() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public NdefMessage createNdefMessage(NfcEvent event) {
            Snackbar.make(actionButton, "Sending: " + TEXT_MESSAGE, Snackbar.LENGTH_LONG).show();

            NdefRecord mime = createMime("application/net.mnio.sharknetnfc", TEXT_MESSAGE.getBytes());
            NdefRecord[] ndefRecords = {mime};
            return new NdefMessage(ndefRecords);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionButton = (FloatingActionButton) findViewById(R.id.fab);

        mNfcAdapter = NfcAdapter.getDefaultAdapter(getApplication());
        if (mNfcAdapter == null) {
            Snackbar.make(actionButton, "NFC not available", Snackbar.LENGTH_LONG).show();
        } else {
            mNfcAdapter.setNdefPushMessageCallback(createNdefMessageCallback, MainActivity.this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to see that the Activity started due to an Android Beam
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
            processIntent(getIntent());
        }
    }

    private void processIntent(Intent intent) {
        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

//        Snackbar.make(actionButton, "Received:" + msg, Snackbar.LENGTH_LONG).show();

        for (int i = 0; i < rawMsgs.length; i++) {
            NdefMessage rawMsg = (NdefMessage) rawMsgs[i];

//            byte[] data = rawMsg.toByteArray();
//            System.out.println(i + " : " + Arrays.toString(data));
//            System.out.println(i + " : " + new String(data));

//        record 0 contains the MIME type, record 1 is the AAR, if present
            NdefRecord[] records = rawMsg.getRecords();
            for (int j = 0; j < records.length; j++) {
                System.out.println(i + " : " + j + " : " + new String(records[j].getPayload()));
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        // onResume gets called after this to handle the intent
        setIntent(intent);
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
