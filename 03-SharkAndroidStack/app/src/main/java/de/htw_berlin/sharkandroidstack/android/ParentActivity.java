package de.htw_berlin.sharkandroidstack.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import de.htw_berlin.sharkandroidstack.R;
import de.htw_berlin.sharkandroidstack.system_modules.SettingsActivity;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class ParentActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_parent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.sidenav_drawer_open, R.string.sidenav_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.sidenav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void includeLayout(int resource) {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.include);
        ViewGroup rootView = (ViewGroup) findViewById(R.id.include).getRootView();
        View inflate = getLayoutInflater().inflate(resource, rootView, false);
        rl.addView(inflate);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.topbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if (id == R.id...) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        Intent intent = null;

        switch (id) {
            case R.id.sidenav_menu_item_settings:
                intent = new Intent(this, SettingsActivity.class);
                break;
            case R.id.sidenav_menu_item_mariodemo:
                intent = new Intent(this, de.htw_berlin.sharkandroidstack.modules.mariodemo.MainActivity.class);
        }

        if (intent != null) {
            this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
