package de.htw_berlin.sharkandroidstack.android;

import android.os.Bundle;

import de.htw_berlin.sharkandroidstack.R;


public class MainIntroActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setLayoutResource(R.layout.system_intro_activity);
    }
}
