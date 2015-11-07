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

    protected EditText input;
    protected TextView output;
    private Button exitButton;

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

        clearCursor();
        final boolean register = register();
        if (!register) {
            Toast.makeText(this, "NFC not available", Toast.LENGTH_LONG).show();
            finish();
        }
        receive();
    }

    private void clearCursor() {
        input.clearFocus();
        input.setCursorVisible(false);
        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditText) v).setCursorVisible(true);
                v.setOnClickListener(null);
            }
        });
    }

    abstract boolean register();

    abstract void receive();
}
