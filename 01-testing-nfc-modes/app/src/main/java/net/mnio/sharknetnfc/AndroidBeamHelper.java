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
import android.text.TextUtils;
import android.widget.TextView;

import static android.nfc.NdefRecord.createMime;
import static android.nfc.NfcAdapter.ACTION_NDEF_DISCOVERED;
import static android.nfc.NfcAdapter.CreateNdefMessageCallback;
import static android.nfc.NfcAdapter.EXTRA_NDEF_MESSAGES;

public class AndroidBeamHelper {

    static TextView input;

    static final String INTENT_FILTER_DATA_MIME_TYPE = "application/net.mnio.sharknetnfc";

    static final CreateNdefMessageCallback MESSAGE_CALLBACK = new CreateNdefMessageCallback() {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public NdefMessage createNdefMessage(NfcEvent event) {
            final byte[] bytes = getBytesFromInput();
            NdefMessage msg = new NdefMessage(new NdefRecord[]{createMime(INTENT_FILTER_DATA_MIME_TYPE, bytes)});
            return msg;
        }
    };

    public static void registerCreateNdefCallback(Activity activity, TextView input, NfcAdapter nfcAdapter) {
        AndroidBeamHelper.input = input;
        nfcAdapter.setNdefPushMessageCallback(MESSAGE_CALLBACK, activity);
    }

    public static void tryToReadDataOnIntent(Intent intent, TextView output) {
        if (intent == null || !ACTION_NDEF_DISCOVERED.equals(intent.getAction())) return;

        Parcelable[] rawMsgs = intent.getParcelableArrayExtra(EXTRA_NDEF_MESSAGES);

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

    private static byte[] getBytesFromInput() {
        if (input != null) {
            String tmp = input.getText().toString().trim();
            if (!TextUtils.isEmpty(tmp)) {
                return tmp.getBytes();
            }
        }

        return ("Beam Time: " + System.currentTimeMillis()).getBytes();
    }
}
