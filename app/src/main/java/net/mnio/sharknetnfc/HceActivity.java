package net.mnio.sharknetnfc;

import android.annotation.TargetApi;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import net.mnio.sharknetnfc.hce.IsoDepTransceiver;
import net.mnio.sharknetnfc.hce.OnMessageReceived;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class HceActivity extends NfcActivity {

    StringBuilder outputStringBuilder = new StringBuilder();

    public static String inputString;

    private final TextWatcher onInputChangeListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            inputString = s.toString();
        }
    };

    @Override
    void prepareSending(EditText input, NfcAdapter nfcAdapter) {
        onInputChangeListener.afterTextChanged(input.getText());
        input.addTextChangedListener(onInputChangeListener);

        nfcAdapter.disableReaderMode(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        final NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter != null) nfcAdapter.disableReaderMode(this);
    }

    @Override
    void prepareReceiving(final TextView output, NfcAdapter nfcAdapter) {
        // http://stackoverflow.com/questions/27939030/alternative-way-for-enablereadermode-to-work-with-android-apis-lesser-than-19
        final OnMessageReceived onMessageReceived = new OnMessageReceived() {
            @Override
            public void onMessage(final byte[] message) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        outputStringBuilder.append(new String(message) + "\n");
                        output.setText(outputStringBuilder.toString());
                    }
                });
            }

            @Override
            public void onError(Exception exception) {
                onMessage(("Finished with error: " + exception.getMessage()).getBytes());
            }
        };

        final NfcAdapter.ReaderCallback readerCallback = new NfcAdapter.ReaderCallback() {
            @Override
            public void onTagDiscovered(Tag tag) {
                IsoDep isoDep = IsoDep.get(tag);
                if (isoDep == null) {
                    return;
                }
                IsoDepTransceiver transceiver = new IsoDepTransceiver(isoDep, onMessageReceived);
                Thread thread = new Thread(transceiver);
                thread.start();
            }
        };

        final int flags = NfcAdapter.FLAG_READER_NFC_A | NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK;
        nfcAdapter.enableReaderMode(this, readerCallback, flags, null);
    }
}
