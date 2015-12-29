package de.htw_berlin.sharkandroidstack.modules.mariodemo;

import android.os.Bundle;

import de.htw_berlin.sharkandroidstack.R;
import de.htw_berlin.sharkandroidstack.android.ParentActivity;

public class MainActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        includeLayout(R.layout.module_mariodemo);
    }
}
