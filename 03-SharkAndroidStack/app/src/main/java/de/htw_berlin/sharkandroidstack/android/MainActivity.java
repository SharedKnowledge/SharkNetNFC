package de.htw_berlin.sharkandroidstack.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.htw_berlin.sharkandroidstack.R;
import de.htw_berlin.sharkandroidstack.setup.SharkStack;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SharkStack(this);
    }
}
