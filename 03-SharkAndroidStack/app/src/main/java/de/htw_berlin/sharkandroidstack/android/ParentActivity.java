package de.htw_berlin.sharkandroidstack.android;

import android.app.Fragment;
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
import de.htw_berlin.sharkandroidstack.modules.mariodemo.MarioDemoMainActivity;
import de.htw_berlin.sharkandroidstack.system_modules.settings.SettingsActivity;

import static android.support.design.widget.NavigationView.OnNavigationItemSelectedListener;

public class ParentActivity extends AppCompatActivity implements OnNavigationItemSelectedListener {

    public static final int LAYOUT_OPTION_RESOURCE = 1;
    public static final int LAYOUT_OPTION_FRAGMENT = 2;
    public static final int LAYOUT_OPTION_NULL = -1;

    private int layoutInUse = LAYOUT_OPTION_NULL;
    private int optionsMenuResource = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.system_parent_activity);

        overridePendingTransition(R.anim.in_left_to_right, R.anim.out_right_to_left);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.sidenav_drawer_open, R.string.sidenav_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.sidenav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void setLayoutResource(int resource) {
        checkIfLayoutIsUsed(LAYOUT_OPTION_RESOURCE);
        View includeContainer = findViewById(R.id.include);

        RelativeLayout rl = (RelativeLayout) includeContainer;
        ViewGroup rootView = (ViewGroup) includeContainer.getRootView();
        View inflate = getLayoutInflater().inflate(resource, rootView, false);
        rl.addView(inflate);
    }

    protected void setFragment(Fragment fragment) {
        checkIfLayoutIsUsed(LAYOUT_OPTION_FRAGMENT);

        getFragmentManager().beginTransaction().replace(R.id.include, fragment).commit();
    }

    protected void setOptionsMenu(int resource) {
        optionsMenuResource = resource;
    }

    private void checkIfLayoutIsUsed(int layoutOption) {
        if (layoutInUse != LAYOUT_OPTION_NULL) {
            throw new IllegalStateException("Layout already set.");
        }
        layoutInUse = layoutOption;
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
        if (optionsMenuResource <= 0) {
            return false;
        }
        getMenuInflater().inflate(optionsMenuResource, menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        Class activity = null;

        switch (id) {
            case R.id.sidenav_menu_item_settings:
                activity = SettingsActivity.class;
                break;
            case R.id.sidenav_menu_item_mariodemo:
                activity = MarioDemoMainActivity.class;
        }

        if (activity != null) {
            item.setChecked(true);
            this.startActivity(new Intent(this, activity));
            return true;
        }

        return false;
    }
}
