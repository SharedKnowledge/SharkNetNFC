package net.mnio.sharknetnfc;

import android.app.Activity;
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
            final boolean success = prepareSending(input);
            if (!success) {
                Toast.makeText(NfcActivity.this, "NFC not available", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    };

    private EditText input;
    private TextView output;

    private Button exitButton;
    private Button sendButton;

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

        receive(output);
    }

    abstract boolean prepareSending(EditText input);

    abstract void receive(TextView output);
}
