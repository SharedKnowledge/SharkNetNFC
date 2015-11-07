package net.mnio.sharknetnfc;

public class P2PActivity extends NfcActivity {
    @Override
    boolean register() {
        return AndroidBeamHelper.register(this, input);
    }

    @Override
    void receive() {
        AndroidBeamHelper.readData(getIntent(), output);
    }

//    @Override
//    public void onNewIntent(Intent intent) {
//        setIntent(intent);
//    }
}
