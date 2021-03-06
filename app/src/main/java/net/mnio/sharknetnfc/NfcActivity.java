package net.mnio.sharknetnfc;

import android.app.Activity;
import android.graphics.Color;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public abstract class NfcActivity extends Activity {

    private final View.OnClickListener exitOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };

    private final View.OnClickListener sendOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(NfcActivity.this);
            if (nfcAdapter == null) {
                Toast.makeText(NfcActivity.this, "Send preparation failed", Toast.LENGTH_LONG).show();
            } else {
                view.setEnabled(false);
                Button button = (Button) view;
                button.setTextColor(Color.DKGRAY);
                button.setText(button.getText() + "...");
                prepareSending(input, nfcAdapter);
            }
        }
    };

    private EditText input;
    private TextView output;

    private Button exitButton;
    private Button sendButton;
    private TextView modeView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nfc_main);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (input == null || output == null) {
            input = (EditText) findViewById(R.id.inputTextView);
            output = (TextView) findViewById(R.id.outputTextView);
        }

        if (exitButton == null) {
            exitButton = (Button) findViewById(R.id.exitButton);
            exitButton.setOnClickListener(exitOnClickListener);
        }

        if (sendButton == null) {
            sendButton = (Button) findViewById(R.id.sendButton);
            sendButton.setOnClickListener(sendOnClickListener);
        }

        modeView = (TextView) findViewById(R.id.modeTextView);
        modeView.setText(getIntent().getIntExtra(MainActivity.INTENT_EXTRA_ACTIVITY_MODE_NAME, -1));

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Toast.makeText(NfcActivity.this, "Receive preparation failed", Toast.LENGTH_LONG).show();
        } else {
            prepareReceiving(output, nfcAdapter);
        }
    }

    abstract void prepareSending(EditText input, NfcAdapter nfcAdapter);

    abstract void prepareReceiving(TextView output, NfcAdapter nfcAdapter);
}
