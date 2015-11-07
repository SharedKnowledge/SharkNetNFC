package net.mnio.sharknetnfc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static net.mnio.sharknetnfc.MainActivity.NFC_MODE.*;

public class MainActivity extends Activity {

    private final View.OnClickListener modeToggleListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            final NFC_MODE mode = getMode();
            switch (mode) {
                case P2P:
                    setMode(HCE);
                    break;
                case HCE:
                    setMode(P2P);
                    break;
            }

        }
    };
    private EditText input;
    private TextView output;

    enum NFC_MODE {
        P2P, HCE;
    }

    Button modeButton;
    NFC_MODE mode;

    public NFC_MODE getMode() {
        if (mode == null) {
            setMode(P2P);
        }
        return mode;
    }

    private void setMode(NFC_MODE mode) {
        this.mode = mode;
        modeButton.setText("active: " + mode.name());

        boolean success = AndroidBeamHelper.register(this, input);
        if (!success) {
            Toast.makeText(this, "NFC not available", Toast.LENGTH_LONG).show();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onNewIntent(Intent intent) {
        setIntent(intent);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (input == null || output == null) {
            input = (EditText) findViewById(R.id.inputTextView);
            output = (TextView) findViewById(R.id.outputTextView);
        }

        if (modeButton == null) {
            modeButton = (Button) findViewById(R.id.modeButton);
            modeButton.setOnClickListener(modeToggleListener);
            setMode(P2P);
        }

        clearCursor();
        AndroidBeamHelper.readData(getIntent(), output);
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
}
