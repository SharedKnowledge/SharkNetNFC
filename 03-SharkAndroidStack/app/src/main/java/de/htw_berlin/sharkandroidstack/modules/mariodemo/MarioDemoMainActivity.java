package de.htw_berlin.sharkandroidstack.modules.mariodemo;

import android.os.Bundle;
import android.view.MenuItem;

import de.htw_berlin.sharkandroidstack.R;
import de.htw_berlin.sharkandroidstack.android.ParentActivity;

public class MarioDemoMainActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayoutResource(R.layout.module_mariodemo_activity);
        setOptionsMenu(R.menu.module_mariodemo_menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id...) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
