package net.mnio.sharknetnfc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton actionButton;

    enum NFC_MODE {
        P2P(R.id.action_settings_p2p), HCE(R.id.action_settings_hce);

        public final int menuItemId;

        NFC_MODE(int menuItemId) {
            this.menuItemId = menuItemId;
        }
    }

    private NFC_MODE mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mode == null) {
            mode = NFC_MODE.P2P;
        }
    }

//    @Override
//    public void onNewIntent(Intent intent) {
//        System.out.println("onNewIntent start");
//        setIntent(intent);
//        System.out.println("onNewIntent done");
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        for (NFC_MODE availableMode : NFC_MODE.values()) {
            MenuItem item = menu.findItem(availableMode.menuItemId);
            if (mode == availableMode) {
                item.setChecked(true);
                continue;
            }

            if (item.isCheckable()) {
                item.setChecked(false);
            }
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        for (NFC_MODE availableMode : NFC_MODE.values()) {
            if (availableMode.menuItemId == item.getItemId()) {
                mode = availableMode;
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
