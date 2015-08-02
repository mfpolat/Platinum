package com.cetur.platinum;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.PersistableBundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;


public class DemandListActivity extends ActionBarActivity implements View.OnClickListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private TextView leftMenuDemandsTV, leftMenuDriverStatusTV, leftMenuNotificationsTV, leftMenuSettingsTV, leftMenuInfoTV, leftMenuProfileTV,leftMenuUserNameTV,leftMenuUserMailTV;
    private CircleImageView leftMenuUserPicIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_list);
        setToolbar();
        initLeftMenu();
    }


    private void setToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        toolbar.setTitle("Platinum");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    private void initLeftMenu() {
        leftMenuDemandsTV = (TextView) findViewById(R.id.leftMenuDemandsTV);
        leftMenuDemandsTV.setOnClickListener(this);
        leftMenuDriverStatusTV = (TextView) findViewById(R.id.leftMenuDriverStatusTV);
        leftMenuDriverStatusTV.setOnClickListener(this);
        leftMenuNotificationsTV = (TextView) findViewById(R.id.leftMenuNotificationsTV);
        leftMenuNotificationsTV.setOnClickListener(this);
        leftMenuSettingsTV = (TextView) findViewById(R.id.leftMenuSettingsTV);
        leftMenuSettingsTV.setOnClickListener(this);
        leftMenuInfoTV = (TextView) findViewById(R.id.leftMenuInfoTV);
        leftMenuInfoTV.setOnClickListener(this);
        leftMenuProfileTV = (TextView) findViewById(R.id.leftMenuProfileTV);
        leftMenuProfileTV.setOnClickListener(this);

        leftMenuUserNameTV = (TextView)findViewById(R.id.leftMenuUserNameTV);
        leftMenuUserMailTV =(TextView)findViewById(R.id.leftMenuUserMailTV);
        leftMenuUserPicIV =(CircleImageView)findViewById(R.id.leftMenuUserPicIV);
        Typeface face = Typeface.createFromAsset(getAssets(), "fonts/Helvetica.ttf");
        leftMenuDemandsTV.setTypeface(face);
        leftMenuDriverStatusTV.setTypeface(face);
        leftMenuNotificationsTV.setTypeface(face);
        leftMenuSettingsTV.setTypeface(face);
        leftMenuInfoTV.setTypeface(face);
        leftMenuProfileTV.setTypeface(face);
        leftMenuUserMailTV.setTypeface(face);
        leftMenuUserNameTV.setTypeface(face);

        leftMenuUserMailTV.setText(AppController.getInstance().getUser().getMail());
        leftMenuUserNameTV.setText(AppController.getInstance().getUser().getName() + " "+AppController.getInstance().getUser().getSurname());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_demand_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leftMenuDemandsTV:
                openDemandsList();
                break;
            case R.id.leftMenuDriverStatusTV:
                startDriverStatusActivity();
                break;
            case R.id.leftMenuNotificationsTV:
                startNotificationsActivity();
                break;
            case R.id.leftMenuSettingsTV:
                startSettingsActivity();
                break;
            case R.id.leftMenuInfoTV:
                startInfoActivity();
                break;
            case R.id.leftMenuProfileTV:
                startProfileActivity();
                break;
        }
    }

    private void startProfileActivity() {
        drawerLayout.closeDrawers();
        startActivity(new Intent(this, ProfileActivity.class));
    }

    private void startInfoActivity() {
        drawerLayout.closeDrawers();
        startActivity(new Intent(this, InfoActivity.class));
    }

    private void startSettingsActivity() {
        drawerLayout.closeDrawers();
        startActivity(new Intent(this, SettingsActivity.class));
    }

    private void startNotificationsActivity() {
        drawerLayout.closeDrawers();
        startActivity(new Intent(this, NotificationsActivity.class));
    }

    private void startDriverStatusActivity() {
        drawerLayout.closeDrawers();
        startActivity(new Intent(this, DriverStatusActivity.class));
    }

    private void openDemandsList() {
        drawerLayout.closeDrawers();
    }
}
