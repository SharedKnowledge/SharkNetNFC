package net.mnio.sharknetnfc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.os.Build;
import android.os.Parcelable;
import android.widget.TextView;

import static android.nfc.NdefRecord.createMime;

public class AndroidBeamHelper {

    static NfcAdapter mNfcAdapter;
    static TextView input;

    static final NfcAdapter.CreateNdefMessageCallback MESSAGE_CALLBACK = new NfcAdapter.CreateNdefMessageCallback() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public NdefMessage createNdefMessage(NfcEvent event) {
            String inputString = input.getText().toString();
            NdefRecord mime = createMime("application/net.mnio.sharknetnfc", inputString.getBytes());
            NdefRecord[] ndefRecords = {mime};
            return new NdefMessage(ndefRecords);
        }
    };

    public static boolean register(Activity activity, TextView input) {
        AndroidBeamHelper.input = input;
        mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        if (mNfcAdapter == null) return false;

        mNfcAdapter.setNdefPushMessageCallback(MESSAGE_CALLBACK, activity);
        return true;
    }

    public static void readData(Intent intent, TextView output) {
        if (intent == null || !NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) return;

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < rawMsgs.length; i++) {
            NdefMessage rawMsg = (NdefMessage) rawMsgs[i];

//            byte[] data = rawMsg.toByteArray();
//            System.out.println(i + " : " + Arrays.toString(data));
//            System.out.println(i + " : " + new String(data));

//          record 0 contains the MIME type, record 1 is the AAR, if present
            NdefRecord[] records = rawMsg.getRecords();
            for (int j = 0; j < records.length; j++) {
                String s = new String(records[j].getPayload());
                System.out.println("NdefMessage[" + i + "] -> NdefRecord[" + j + "] = " + s);
                builder.append(s);
            }
        }

        output.setText(builder);
    }
}
