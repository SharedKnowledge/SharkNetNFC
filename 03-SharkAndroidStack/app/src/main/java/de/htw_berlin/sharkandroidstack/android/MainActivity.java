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

    TextView outputHeader;
    boolean isShowingLogOutput = false;
    View.OnClickListener toggleClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            isShowingLogOutput = !isShowingLogOutput;

            Button button = (Button) view;

            if (!isShowingLogOutput) {
                button.setText("show Log");
                outputHeader.setText("KB");

                kbTextViewWriter.showKbText();
            } else {
                button.setText("show KB");
                outputHeader.setText("Log:");

                kbTextViewWriter.showLogText();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nameView = (TextView) findViewById(R.id.nameTextView);
        nameView.setText(getDeviceId());

    }

    @Override
    protected void onResume() {
        super.onResume();

        outputHeader = (TextView) findViewById(R.id.outputHeader);

        if (sharkStack == null) {
            kbTextViewWriter = KbTextViewWriter.getInstance();

            sharkStack = new SharkStack(this, getDeviceId()).setTextViewWriter(kbTextViewWriter).start();

            kbTextViewWriter.setOutputTextView((TextView) findViewById(R.id.outputTextView));
            View toggleLogView = findViewById(R.id.toogleLog);
            toggleLogView.setOnClickListener(toggleClickListener);
            toggleClickListener.onClick(toggleLogView);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (sharkStack != null) {
            sharkStack.stop();
            sharkStack = null;
        }
    }

    private String getDeviceId() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
