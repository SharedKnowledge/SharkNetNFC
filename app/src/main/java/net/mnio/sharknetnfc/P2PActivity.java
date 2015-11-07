package net.mnio.sharknetnfc;

import android.widget.EditText;
import android.widget.TextView;

public class P2PActivity extends NfcActivity {

    @Override
    boolean prepareSending(EditText input) {
        return AndroidBeamHelper.register(this, input);
    }

    @Override
    void receive(TextView output) {
        AndroidBeamHelper.readData(getIntent(), output);
    }

//    @Override
//    public void onNewIntent(Intent intent) {
//        setIntent(intent);
//    }
}
