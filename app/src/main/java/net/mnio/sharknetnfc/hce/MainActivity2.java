package net.mnio.sharknetnfc.hce;

import android.annotation.TargetApi;
import android.app.Activity;
import android.nfc.NfcAdapter;
import android.nfc.NfcAdapter.ReaderCallback;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;

import net.mnio.sharknetnfc.R;

import java.util.Arrays;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class MainActivity2 extends Activity implements OnMessageReceived, ReaderCallback {

    private NfcAdapter nfcAdapter;
    private ListView listView;
    private IsoDepAdapter isoDepAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        listView = (ListView) findViewById(R.id.listView);
        isoDepAdapter = new IsoDepAdapter(getLayoutInflater());
        listView.setAdapter(isoDepAdapter);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        // http://stackoverflow.com/questions/27939030/alternative-way-for-enablereadermode-to-work-with-android-apis-lesser-than-19
        if (nfcAdapter == null) {
            //No nfc!
            return;
        }
        nfcAdapter.enableReaderMode(this, this, NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                null);
    }

    @Override
    public void onPause() {
        super.onPause();
        nfcAdapter.disableReaderMode(this);
    }

    @Override
    public void onTagDiscovered(Tag tag) {
        IsoDep isoDep = IsoDep.get(tag);
        if (isoDep == null) {
            return;
        }
        IsoDepTransceiver transceiver = new IsoDepTransceiver(isoDep, this);
        Thread thread = new Thread(transceiver);
        thread.start();
    }

    @Override
    public void onMessage(final byte[] message) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                isoDepAdapter.addMessage(Arrays.toString(message) + " - " + new String(message));
            }
        });
    }

    @Override
    public void onError(Exception exception) {
        onMessage(exception.getMessage().getBytes());
    }
}
