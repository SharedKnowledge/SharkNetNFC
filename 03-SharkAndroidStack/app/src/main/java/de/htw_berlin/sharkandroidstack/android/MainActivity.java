package de.htw_berlin.sharkandroidstack.android;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.htw_berlin.sharkandroidstack.R;
import de.htw_berlin.sharkandroidstack.setup.SharkStack;


public class MainActivity extends AppCompatActivity {

    static SharkStack sharkStack;
    KbTextViewWriter kbTextViewWriter;
    View.OnClickListener toggleClickListener = new View.OnClickListener() {

        boolean showLog = false;

        @Override
        public void onClick(View view) {
            showLog = !showLog;

            Button button = (Button) view;

            if (showLog) {
                button.setText("show log");
                kbTextViewWriter.showKbText();
            } else {
                button.setText("show kb");
                kbTextViewWriter.showLogText();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        TextView nameView = (TextView) findViewById(R.id.nameTextView);
        nameView.setText("id:" + deviceId);

        if (sharkStack == null) {
            kbTextViewWriter = KbTextViewWriter.getInstance();

            sharkStack = new SharkStack(this, deviceId).setTextViewWriter(kbTextViewWriter).start();

            kbTextViewWriter.setOutputTextView((TextView) findViewById(R.id.outputTextView));
            findViewById(R.id.toogleLog).setOnClickListener(toggleClickListener);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sharkStack != null) {
            sharkStack.stop();
            sharkStack = null;
        }
    }
}
