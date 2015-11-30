package de.htw_berlin.sharkandroidstack.android;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import de.htw_berlin.sharkandroidstack.R;
import de.htw_berlin.sharkandroidstack.setup.SharkStack;


public class MainActivity extends AppCompatActivity {

    private static SharkStack sharkStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        TextView nameView = (TextView) findViewById(R.id.nameTextView);
        nameView.setText("Name: " + deviceId);

        if (sharkStack == null) {
            sharkStack = new SharkStack(this, deviceId);
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
