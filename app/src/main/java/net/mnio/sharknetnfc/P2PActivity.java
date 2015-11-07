package net.mnio.sharknetnfc;

import android.nfc.NfcAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class P2PActivity extends NfcActivity {

    @Override
    void prepareSending(EditText input, NfcAdapter nfcAdapter) {
        AndroidBeamHelper.registerCreateNdefCallback(this, input, nfcAdapter);
    }

    @Override
    void prepareReceiving(TextView output, NfcAdapter nfcAdapter) {
        AndroidBeamHelper.tryToReadDataOnIntent(getIntent(), output);
    }

//    @Override
//    public void onNewIntent(Intent intent) {
//        setIntent(intent);
//    }
}
